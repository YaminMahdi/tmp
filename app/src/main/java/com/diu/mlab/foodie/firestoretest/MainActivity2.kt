package com.diu.mlab.foodie.firestoretest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diu.mlab.foodie.firestoretest.model.Day
import com.diu.mlab.foodie.firestoretest.ui.theme.FireStoreTestTheme

class MainActivity2 : ComponentActivity() {
    val list = listOf<Day>(
        Day(
            listOf(
                com.diu.mlab.foodie.firestoretest.model.Class("Bangla", "CSE113"),
                com.diu.mlab.foodie.firestoretest.model.Class("English", "CSE114"),
                com.diu.mlab.foodie.firestoretest.model.Class("Hindi", "CSE115")

            )
        ),
        Day(
            listOf(
                com.diu.mlab.foodie.firestoretest.model.Class("Bangla1", "CSE113"),
                com.diu.mlab.foodie.firestoretest.model.Class("English1", "CSE114"),
                com.diu.mlab.foodie.firestoretest.model.Class("Hindi1", "CSE115")

            )
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FireStoreTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(list)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(list: List<Day>) {
    val state = rememberPagerState()

    HorizontalPager(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize(),
        pageCount = list.size,
        contentPadding = PaddingValues(horizontal = 20.dp),
        pageSpacing = 10.dp,
        state = state
    ){day ->
        LazyColumn{
            items(list[day].clas.size) {
                ClassItem(list[day].clas[it])
            }
        }
    }
}

@Composable
fun ClassItem(cls : com.diu.mlab.foodie.firestoretest.model.Class){
    Column{
        Text(text = cls.name)
        Spacer(Modifier.size(4.dp))
        Text(text = cls.courseCode)
    }
}

