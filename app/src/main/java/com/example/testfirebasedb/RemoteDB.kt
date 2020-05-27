package com.example.testfirebasedb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

/*class RemoteDB {
    val db = Firebase.firestore
    val users = MutableLiveData<List<User>>()
    fun insertUser(user: User) {
        db.collection("Users").document(user.id.toString())
            .set(user)
    }
}*/
//class User(var id:Int, var name:String, var age: Int, var dateOfBirth:LocalDate)
class User(var id:Int, var name:String, var age: Int)

/*
class User {
    var id: Int = 0
    var name: String = "Kirill"
    var age:Int = 16
    var dateOfBirth: LocalDate = LocalDate.of(1934,1, 2)
}*/
