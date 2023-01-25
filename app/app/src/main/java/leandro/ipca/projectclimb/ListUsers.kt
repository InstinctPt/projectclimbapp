package leandro.ipca.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import leandro.ipca.projectclimb.databinding.ActivityListUsersBinding
import leandro.ipca.projectclimb.databinding.RowPostBinding
import leandro.ipca.projectclimb.databinding.RowUserBinding
import leandro.ipca.projectclimb.models.AppDatabase
import leandro.ipca.projectclimb.models.Event
import leandro.ipca.projectclimb.models.User

class ListUsers : AppCompatActivity() {
    private lateinit var binding: ActivityListUsersBinding
    var users = arrayListOf<User>()
    //Create user model and initiate a list here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore
        var recyclerView = binding.recyclerView

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    users.add(User.fromDoc(document))
                    recyclerView.adapter!!.notifyDataSetChanged()
                    //Log.d("GET", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("GET", "Error getting documents: ", exception)
            }
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = UserAdapter()
        recyclerView.itemAnimator = DefaultItemAnimator()
    }


    inner class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>(){


        inner class ViewHolder(binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root){
            val textViewName : TextView = binding.textViewNameUser

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                RowUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false))

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.apply {
                textViewName.text = users[position].name
            }
            holder.itemView.setOnClickListener {
                val intent = Intent(this@ListUsers, Symptoms::class.java)
                intent.putExtra("id", users[position].id)
                startActivity(intent)
            }

        }
        override fun getItemCount(): Int {
            return users.size
        }


    }

}