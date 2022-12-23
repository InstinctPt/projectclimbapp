package leandro.ipca.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import leandro.ipca.projectclimb.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch (Dispatchers.IO){
            delay(2000)
            lifecycleScope.launch (Dispatchers.Main) {
                    startActivity(
                        Intent(this@Splash,
                            LoginActivity::class.java)
                    )
            }

        }

    }
}