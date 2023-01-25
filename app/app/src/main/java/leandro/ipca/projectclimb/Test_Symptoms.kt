package leandro.ipca.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.ipca.projectclimb.databinding.ActivityTestSymptomsBinding

class Test_Symptoms : AppCompatActivity() {

    private lateinit var binding: ActivityTestSymptomsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestSymptomsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonSymptoms.setOnClickListener {
            startActivity(
                Intent(this,
                    ListUsers::class.java)
            )
        }
        binding.buttonTest.setOnClickListener {
            startActivity(
                Intent(this,
                    Testing::class.java)
            )
        }
    }
}