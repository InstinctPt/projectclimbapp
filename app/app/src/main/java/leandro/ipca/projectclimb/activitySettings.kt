package leandro.ipca.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import leandro.ipca.projectclimb.databinding.ActivityLoginBinding
import leandro.ipca.projectclimb.databinding.ActivityMainBinding
import leandro.ipca.projectclimb.databinding.ActivitySettingsBinding

class activitySettings : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.textViewName.text = auth.currentUser?.email

        binding.button3.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(
                Intent(this@activitySettings,
                    LoginActivity::class.java)
            )
        }


    }
}