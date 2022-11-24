package leandro.personal.projectclimb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import leandro.personal.projectclimb.databinding.ActivityListUsersBinding

class ListUsers : AppCompatActivity() {
    private lateinit var binding: ActivityListUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}