package leandro.personal.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.personal.projectclimb.databinding.ActivityMainBinding
import leandro.personal.projectclimb.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            startActivity(
                Intent(this@MainActivity,
                    MainActivity::class.java)
        }



    }
}