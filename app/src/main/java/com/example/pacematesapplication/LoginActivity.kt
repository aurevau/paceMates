package com.example.pacematesapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.pacematesapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

lateinit var binding: ActivityLoginBinding
val firebaseUtil = FirebaseUtil()
lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(firebaseUtil.isLoggedIn()){
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
        setInProgress(false)

        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            login()
            setInProgress(true)
        }

        binding.btnRegister.setOnClickListener {
            register()
            setInProgress(true)
        }

    }

    fun register(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.register(email, password){
            if (it.isSuccessful){
                clearFields()
                val intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this,it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.login(email, password, {
            clearFields()
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }, {
            Toast.makeText(this,it.message.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    fun clearFields(){
        binding.etEmail.text.clear()
        binding.etPassword.text.clear()
    }



    fun checkValidInput(): Boolean{
        var check = true
        if(binding.etPassword.text.isBlank()){
            check = false
            Toast.makeText(this, "password cannot be blank", Toast.LENGTH_SHORT).show()
        }
        if(binding.etEmail.text.isBlank()){
            check = false
            Toast.makeText(this, "email cannot be blank", Toast.LENGTH_SHORT).show()
        }

        if(binding.etPassword.text.length < 6){
            check = false
            binding.etPassword.text.clear()
            Toast.makeText(this, "password cannot be less than 6 characters", Toast.LENGTH_SHORT).show()
        }
        return check
    }


    fun setInProgress(inProgress: Boolean){
        val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
        if(inProgress){
            progressbar.visibility = View.VISIBLE
        }  else {
            progressbar.visibility = View.GONE
        }
    }
}