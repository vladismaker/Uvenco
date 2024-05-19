package com.application.uvencotest.ui.theme.compose_files

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.uvencotest.viewmodels.MainViewModel
import com.application.uvencotest.R
import com.application.uvencotest.data.DataItemCard


@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    Surface(
        color = colorResource(id = R.color.black)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()) {
            TopBar(navController, mainViewModel)
            Box {
                MyRecyclerView(mainViewModel)
                SetTextEmpty(mainViewModel)
            }
        }
    }
}
@Composable
fun MyRecyclerView(mainViewModel: MainViewModel) {

    val arrayCups by mainViewModel.liveDataArrayCups.observeAsState()
    val array: List<DataItemCard> = arrayCups ?: listOf()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 9.5.dp),

        )
    {
        items(array) {
            CupListItem(cup = it)
        }
    }
}

@Composable
fun SetTextEmpty(mainViewModel: MainViewModel) {
    val dataEmpty by mainViewModel.liveDataEmpty.observeAsState()
    if (dataEmpty != null) {
        if (dataEmpty!!) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Список пустой",
                    color = colorResource(id = R.color.text_color_title),
                    fontSize = 16.sp
                )
            }
        }
    }
}

