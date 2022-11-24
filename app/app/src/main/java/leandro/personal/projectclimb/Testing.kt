package leandro.personal.projectclimb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.personal.projectclimb.databinding.ActivitySplashBinding
import leandro.personal.projectclimb.databinding.ActivityTestingBinding

class Testing : AppCompatActivity() {
    private lateinit var binding: ActivityTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}