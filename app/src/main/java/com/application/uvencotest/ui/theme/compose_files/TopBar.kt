package com.application.uvencotest.ui.theme.compose_files

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.uvencotest.viewmodels.MainViewModel
import com.application.uvencotest.R
import com.application.uvencotest.navigation.NavRoute

@Composable
fun TopBar(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    val currentTemperature by mainViewModel.currentTemperature.observeAsState()
    val currentTime by mainViewModel.currentTime.observeAsState()

    Surface(
        color = colorResource(id = R.color.black),
    ) {
        Row {
            // Название и Иконка
            Row(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 26.dp)
                    .clickable {
                        if (navController.currentDestination?.route != NavRoute.Detail.route) {
                            navController.navigate(NavRoute.Detail.route)
                        } else {
                            navController.popBackStack()
                        }
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_union),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = stringResource(R.string.icon_description),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = stringResource(id = R.string.title),
                    color = colorResource(id = R.color.brown),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            // Время
            Text(
                text = currentTime ?: "",
                color = colorResource(id = R.color.brown),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )

            // Температура
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "${currentTemperature ?: "87"}°",
                    color = colorResource(id = R.color.brown),
                    fontSize = 16.sp
                )
                Image(
                    painter = painterResource(R.drawable.icon_a_drop),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = stringResource(R.string.icon_description),
                    modifier = Modifier
                        .height(11.dp)
                        .width(8.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            //Язык
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_russia),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = stringResource(R.string.icon_description),
                    modifier = Modifier
                        .padding(2.dp)
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = stringResource(id = R.string.language),
                    color = colorResource(id = R.color.brown),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }
        }
    }
}