package com.example.testfirebasedb

import java.time.LocalDate

/*
class User {
    var id: Int = 0
    var name: String = "Kirill"
    var age:Int = 16
    var dateOfBirth: LocalDate = LocalDate.of(1934,1, 2)
}*/
class User(var id:Int, var name:String, var age: Int){
    constructor() : this(0,"",0)
}

