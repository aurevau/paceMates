package com.example.pacematesapplication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtil {

    public fun currentUserId(): String? {
        return FirebaseAuth.getInstance().uid
    }

    fun isLoggedIn(): Boolean {
        if(currentUserId() != null){
            return true
        }
        return false
    }

    fun currentUserDetails(): DocumentReference {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: throw Exception("User not logged in")
        return FirebaseFirestore.getInstance().collection("users").document(uid)
    }
}