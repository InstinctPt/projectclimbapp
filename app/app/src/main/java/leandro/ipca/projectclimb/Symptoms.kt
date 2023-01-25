package leandro.ipca.projectclimb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import leandro.ipca.projectclimb.databinding.ActivitySymptomsBinding
import leandro.ipca.projectclimb.models.AppDatabase
import leandro.ipca.projectclimb.models.User
import leandro.ipca.projectclimb.models.symptoms
import java.util.Date

class Symptoms : AppCompatActivity() {

    private lateinit var binding: ActivitySymptomsBinding
    lateinit var symptoms : symptoms
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySymptomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView2.text = intent.getStringExtra("id") //TODO Figure out a way to get the id and check who's id it is and put name instead of id


        binding.button5.setOnClickListener {

            //Toast.makeText(this@Symptoms, binding.ratingBarEffect.rating.toString(), Toast.LENGTH_LONG).show()
            lifecycleScope.launch(Dispatchers.IO) {
                symptoms.effect = binding.ratingBarEffect.rating.toString()
                symptoms.sleeping = binding.ratingBarSleeping.rating.toString()
                symptoms.fatigue = binding.ratingBarFatigue.rating.toString()
                symptoms.gastro = binding.ratingBarGastro.rating.toString()
                symptoms.diziness = binding.ratingBarDiziness.rating.toString()
                symptoms.headache = binding.ratingBarHeadache.rating.toString()
                //symptoms.timestamp = Timestamp(Date())
                //AppDatabase.getDatabase(this@Symptoms)?.SymptomsDao()!!.insert(symptoms)
            }
        }
    }
}