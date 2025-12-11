package com.example.pacematesapplication

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class FirebaseManager {

    private val db = Firebase.firestore

    lateinit var currentUser: FirebaseUser

    fun addSnapshotListener(){
        currentUser = Firebase.auth.currentUser ?: return

        currentUser.email

        db.collection("users").document(currentUser.uid).addSnapshotListener { snapshot, error ->
            if (snapshot != null) {

            }
        }
    }
}