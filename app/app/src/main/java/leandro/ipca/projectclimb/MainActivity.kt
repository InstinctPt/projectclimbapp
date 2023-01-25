package leandro.ipca.projectclimb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import leandro.ipca.projectclimb.databinding.ActivityMainBinding
import leandro.ipca.projectclimb.models.AppDatabase
import leandro.ipca.projectclimb.models.Event

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var event: Event
        var auth = Firebase.auth
        //event = AppDatabase.getDatabase(this)?.EventDao()?.getAll()!
        lifecycleScope.launch(Dispatchers.IO) {
            event = AppDatabase.getDatabase(this@MainActivity)?.EventDao()
                ?.getById(1)!!
            binding.textViewTitleMain.text = event.title
        }

        //binding.textViewTitleAccount.text =




        /* intent = intent
        val title = intent.getStringExtra("title")
        binding.textViewTitleMain.text = event[event.size+1].title
        binding.testDescription.text = event[event.size+1].description
        binding.testid.text = event[event.size+1].id
        */



        binding.imageViewSettings.setOnClickListener {
            startActivity(
                Intent(this@MainActivity,
                    activitySettings::class.java))
        }

        binding.buttonTesting.setOnClickListener {
            startActivity(
                Intent(this@MainActivity,
                    Test_Symptoms::class.java))
        }



    }
}