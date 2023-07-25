package com.diu.mlab.foodie.firestoretest

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val userList = savedStateHandle.getStateFlow("userList", emptyList<User>())

    val user = savedStateHandle.getStateFlow("user", User())

    private val dbRef = Firebase.firestore.collection("users")

    init {
        setUser(user = User(
            name = "YK",
            address = Address(
                geo = Geo(lat = "345", long = "4565"), street = "uahg", zip = "1239"
            ),
            email = "yk@x.com"
        ), Success = {}, Failed = {})

        setUser(user = User(
            name = "YK3",
            address = Address(
                geo = Geo(lat = "345", long = "4565"), street = "fhfhf", zip = "125459"
            ),
            email = "yk3@x.com"
        ), Success = {}, Failed = {})

        setUser(user = User(
            name = "YK2",
            address = Address(
                geo = Geo(lat = "345", long = "4565"), street = "hhfh", zip = "45657"
            ),
            email = "yk2@x.com"
        ), Success = {}, Failed = {})
    }



    fun getUserList(Success :() -> Unit, Failed : (msg: String) -> Unit){
        viewModelScope.launch(Dispatchers.IO){

            dbRef.get()
                .addOnSuccessListener { documents ->
                    val tmpList = mutableListOf<User>()
                    for (document in documents) {
                        Log.d("TAG", "${document.id} => ${document.data}")
                        val user = document.toObject(User::class.java)
                        tmpList.add(user)
                    }
                    savedStateHandle["userList"] = tmpList
                    Success.invoke()
                }
                .addOnFailureListener { exception ->
                    Log.w("TAG", "Error getting documents: ", exception)
                    Failed.invoke(exception.message.toString())
                }

        }

    }

    fun setUser(user: User, Success :() -> Unit, Failed : (msg: String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRef.document(user.name)
                .set(user)
                .addOnSuccessListener {
                    Success.invoke()
                }
                .addOnFailureListener{
                    Failed.invoke(it.message.toString())
                }
        }
    }
}