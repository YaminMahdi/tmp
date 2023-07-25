package com.diu.mlab.foodie.firestoretest

data class User(
    val name: String = "",
    val address: Address = Address(),
    val email: String = ""
)