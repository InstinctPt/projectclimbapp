package leandro.ipca.projectclimb

import android.hardware.SensorManager
import android.hardware.SensorManager.getAltitude
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.ipca.projectclimb.databinding.ActivitySymptomsBinding
import leandro.ipca.projectclimb.databinding.ActivityTestingBinding

class Testing : AppCompatActivity() {

    private lateinit var binding: ActivityTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewAltitude.text = Location.getAltitude()
    }
}