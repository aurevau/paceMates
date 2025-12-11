package com.example.pacematesapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pacematesapplication.databinding.ActivityEditProfileBinding
import com.google.firebase.Timestamp

class EditProfileActivity : AppCompatActivity() {
    val firebaseUtil = FirebaseUtil()
    lateinit var usernameInput: EditText
    lateinit var nameInput: EditText
    var userModel: UserModel? = null

    lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root )


        usernameInput = binding.etUsername
        nameInput = binding.etName

        getUsername()
        getName()
        binding.btnSave.setOnClickListener {
            setUsername()
            setName()
            usernameInput.text.clear()
            nameInput.text.clear()

        }

    }

    fun setName(){
        val name = nameInput.text.toString()
        if(name.isEmpty()) {

            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            return
        }
        userModel = userModel?.apply {
            this.name = name
        } ?: UserModel(name, Timestamp.now())

        firebaseUtil.currentUserDetails().set(userModel!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "saved!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun setUsername(){
        val username = usernameInput.text.toString()
        if (username.isEmpty() || username.length < 3){
            usernameInput.setError("Username length should be at least 2 chars")
            return
        }

        userModel = userModel?.apply {
            this.username = username
        } ?: UserModel(username, Timestamp.now())


        firebaseUtil.currentUserDetails().set(userModel!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "saved!", Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun getName(){
        firebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                userModel = task.result.toObject(UserModel::class.java)
                userModel?.let {
                    nameInput.setText(it.name)
                }
            } else {
                val exception = task.exception
                Toast.makeText(this, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun getUsername() {
        firebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                userModel = task.result.toObject(UserModel::class.java)
                userModel?.let {
                    usernameInput.setText(it.username)
                }
            } else {
                val exception = task.exception
                Toast.makeText(this, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}