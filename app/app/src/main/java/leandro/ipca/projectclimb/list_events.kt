package leandro.ipca.projectclimb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import leandro.ipca.projectclimb.databinding.ActivityListEventsBinding
import leandro.ipca.projectclimb.databinding.RowPostBinding
import leandro.ipca.projectclimb.models.AppDatabase
import leandro.ipca.projectclimb.models.Event


class list_events : AppCompatActivity() {
    var events = arrayListOf<Event>()
    private lateinit var binding: ActivityListEventsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListEventsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var sharedPref = getSharedPreferences("preferences_climb", MODE_PRIVATE)
        val value = sharedPref.getString("id", null)
        val db = Firebase.firestore
        var recyclerView = binding.recyclerViewEvents

        if (value != null) {
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@list_events, MainActivity::class.java)
                intent.putExtra("title", events[value.toInt()].title)
                startActivity(intent)
            }, 500) // 500ms d

        }
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    events.add(Event.fromDoc(document))
                    recyclerView.adapter!!.notifyDataSetChanged()
                    //Log.d("GET", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                    Log.d("GET", "Error getting documents: ", exception)
            }




        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PostsAdapter()
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    inner class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>(){
        var sharedPref = getSharedPreferences("preferences_climb", MODE_PRIVATE)
        var editor = sharedPref.edit()

        inner class ViewHolder(binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root){
            val textViewTitle : TextView = binding.textViewTitle
            var textViewDescription : TextView = binding.textViewDescricao
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                RowPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val db = Firebase.firestore
            var title = events[position].title
            var description = events[position].description
            var id = events[position].id
            holder.apply {
                textViewTitle.text = title
                textViewDescription.text = description
            }
            holder.itemView.setOnClickListener {
                val builder = AlertDialog.Builder(this@list_events)
                builder.setTitle("Confirmar Ação")
                builder.setMessage("Tens a certeza que queres começar este evento?")
                builder.setPositiveButton("Sim") { dialog, which ->
                    editor.apply()  // or editor.commit();
                    editor.putString("id", id.toString())


                    val docRef = db.collection("events").document(id.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    AppDatabase.getDatabase(this@list_events)?.EventDao()
                                        ?.insert(Event.fromDoc(document))
                                }
                                Log.d("TAG", "DocumentSnapshot data: ${document.data}")
                            } else {
                                Log.d("TAG", "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d("TAG", "get failed with ", exception)
                        }

                    val handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this@list_events, MainActivity::class.java)
                        intent.putExtra("title", title)
                        startActivity(intent)
                    }, 500) // 500ms d


                }
                builder.setNegativeButton("Não") { dialog, which ->

                }

                // Create the alert dialog
                val alertDialog: AlertDialog = builder.create()

                // Show the alert dialog
                alertDialog.show()

            }
        }
        override fun getItemCount(): Int {
            return events.size
        }


    }

}

