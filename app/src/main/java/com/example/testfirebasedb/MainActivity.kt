package com.example.testfirebasedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    //lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        insertButton.setOnClickListener {
            val user = User(idEditText.text.toString().toInt(), nameeditText.text.toString(),
                ageeditText.text.toString().toInt(), LocalDate.parse(dateEditText.text.toString()))
            insertUser(user)
            /*user.id = idEditText.text.toString().toInt()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            user.dateOfBirth = LocalDate.parse(dateEditText.text.toString())*/

        }
        deleteButton.setOnClickListener {




        }
        updateButton.setOnClickListener {

        }


    }
    val db = Firebase.firestore
    fun insertUser(user: User) {
        db.collection("Users").document(user.id.toString())
            .set(user)
    }
}
