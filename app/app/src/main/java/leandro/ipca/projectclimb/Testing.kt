package leandro.ipca.projectclimb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.ipca.projectclimb.databinding.ActivityTestingBinding

class Testing : AppCompatActivity() {
    private lateinit var binding: ActivityTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}