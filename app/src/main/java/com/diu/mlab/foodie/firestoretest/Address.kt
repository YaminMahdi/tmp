package com.diu.mlab.foodie.firestoretest

data class Address(
    val geo: Geo = Geo(),
    val street: String = "",
    val zip: String = ""
)