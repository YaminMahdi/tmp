package com.diu.mlab.foodie.firestoretest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diu.mlab.foodie.firestoretest.ui.theme.FireStoreTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            FireStoreTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage(viewModel: MainViewModel) {
    val list = viewModel.userList.collectAsState()
    val context = LocalContext.current

    
    val state = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        viewModel.getUserList({
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
        },{
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        HorizontalPager(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize(),
            pageCount = list.value.size,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 10.dp,
            state = state
        ){
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = list.value[it].address.street)
                Text(text = list.value[it].address.geo.lat)
                Text(text = list.value[it].address.geo.long)
                Text(text = list.value[it].email)
            }

        }
    }
}
