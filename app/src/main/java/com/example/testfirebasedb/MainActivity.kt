package com.example.testfirebasedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    lateinit var userListAdapter: UserListAdapter
    val db = Firebase.firestore

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    init {
        createUserDBListener()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersList.layoutManager = LinearLayoutManager(this)
        users.value?.let {
            usersList.adapter = UserListAdapter(it)
        }
        users.observe(this, Observer {
            userListAdapter = UserListAdapter(it)
            usersList.adapter = userListAdapter
               // createUserDBListener()
        })
        /*users.observe(this, Observer {
            userListAdapter = UserListAdapter(it)
            usersList.adapter = userListAdapter
        }
        )*/

        insertButton.setOnClickListener {
         /* val user = User(idEditText.text.toString().toInt(), nameeditText.text.toString(),
                ageeditText.text.toString().toInt(), LocalDate.parse(dateEditText.text.toString()))
            insertUser(user)*/
            val user = User(idEditText.text.toString().toInt(), nameeditText.text.toString(),
                ageeditText.text.toString().toInt())
            insertUser(user)
            /*user.id = idEditText.text.toString().toInt()
            user.name = nameeditText.text.toString()
            user.age = ageeditText.text.toString().toInt()
            user.dateOfBirth = LocalDate.parse(dateEditText.text.toString())*/

        }
        deleteButton.setOnClickListener {
            val user = User(idEditText.text.toString().toInt(), nameeditText.text.toString(),
                ageeditText.text.toString().toInt())
            deleteUser(user)
        }

        updateButton.setOnClickListener {
            val user = User(idEditText.text.toString().toInt(), nameeditText.text.toString(),
                ageeditText.text.toString().toInt())
            insertUser(user)
        }


    }
    fun deleteUser(user: User){
        db.collection("Users").document(user.id.toString())
        .delete()
    }

    fun insertUser(user: User) {
        val data = hashMapOf(
            "name" to user.name,
            "age" to user.age
        )
        db.collection("Users").document(user.id.toString())
            .set(data)
    }
    fun createUserDBListener() {
        db.collection("Users").addSnapshotListener{querySnapshot, firebaseFirestoreException ->
            if (querySnapshot !=null) {
                val users = mutableListOf<User>()
                for (item  in querySnapshot) {
                    val userName = item.getField<String>("name")
                    val userAge = item.getField<Int>("age")
                    val userId = item.id.toString().toInt()
                    //val userDateOfBirth = item.getField<String>("dateOfBirth")
                    val user = User(userId!!, userName!!, userAge!!)

                    users.add(user)
                    _users.value = users

                }
            } else
                _users.value = emptyList()

        }
    }
}
