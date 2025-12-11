package com.example.pacematesapplication

import com.google.firebase.Timestamp

data class UserModel(
    var username: String = "",
    var createdTimeStamp: Timestamp? = null,
    var name: String = ""

)