package leandro.ipca.projectclimb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import leandro.ipca.projectclimb.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)


        auth = Firebase.auth
        var email = binding.textInputEmail
        var password = binding.textInputPassword
        var button = binding.button
        var register = binding.registerButton

        val currentUser = auth.currentUser
        if(currentUser != null){
            //Firebase.auth.signOut()

            startActivity(
                Intent(this@LoginActivity,
                    MainActivity::class.java)
            )
        }

        button.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")
                        val user = auth.currentUser
                        startActivity(
                            Intent(this@LoginActivity,
                                MainActivity::class.java)
                        )
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }

        register.setOnClickListener {
            startActivity(
                Intent(this@LoginActivity,
                    RegisterUser::class.java)
            )
        }





    }
}