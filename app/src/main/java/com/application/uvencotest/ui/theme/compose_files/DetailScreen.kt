package com.application.uvencotest.ui.theme.compose_files

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.uvencotest.viewmodels.MainViewModel
import com.application.uvencotest.R
import com.application.uvencotest.data.DataItemCard

@Composable
fun DetailScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    Surface(
        color = colorResource(id = R.color.black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(navController, mainViewModel)
            DetailView(navController, mainViewModel)
        }
    }
}

private fun getChecked(textPrice: String): Boolean {
    return textPrice == "" || textPrice == "0" || textPrice == "0.0"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(navController: NavHostController, mainViewModel: MainViewModel) {
    val dataItemCard by mainViewModel.liveDataItemCard.observeAsState()
    var textName by remember { mutableStateOf(dataItemCard?.title) }
    var textPrice by remember { mutableStateOf(dataItemCard?.price) }
    var imageSelected by remember { mutableStateOf(if (dataItemCard?.imageId == R.drawable.cup_cuppuccino) true else false) }
    var showButton by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(getChecked(textPrice ?: "")) }
    var imageRes = dataItemCard?.imageId

    LaunchedEffect(textPrice) {
        checked = getChecked(textPrice ?: "")
    }

    LaunchedEffect(textPrice, textName, imageSelected, checked) {
        imageRes = if (imageSelected) R.drawable.cup_cuppuccino else R.drawable.cup_mokkachino
        showButton = if (dataItemCard != null) {
            dataItemCard!!.isDifferentFrom(
                DataItemCard(
                    1,
                    textName ?: "",
                    imageRes ?: R.drawable.background_card_cup,
                    "0.33",
                    textPrice ?: ""
                )
            )
        } else {
            false
        }

    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            //Список элементов редактирования
            Column(
                modifier = Modifier
                    .padding(end = 30.dp)
                    .width(300.dp)
            ) {
                Text(
                    text = "Наименование",
                    color = colorResource(id = R.color.text_color_title),
                    fontSize = 16.sp,
                )
                OutlinedTextField(
                    value = textName ?: "",
                    onValueChange = { textName = it },
                    label = { Text("") },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.text_color_cream),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.light_brown),
                        unfocusedBorderColor = colorResource(id = R.color.brown),
                        cursorColor = colorResource(id = R.color.light_brown)
                    ),

                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Цена",
                    color = colorResource(id = R.color.text_color_title),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 32.dp)
                )
                OutlinedTextField(

                    value = textPrice ?: "",
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            textPrice = it
                        }
                    },
                    label = { Text("") },
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.text_color_cream),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.light_brown),
                        unfocusedBorderColor = colorResource(id = R.color.brown),
                        cursorColor = colorResource(id = R.color.light_brown)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        Image(
                            painter = painterResource(R.drawable.rub),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = stringResource(R.string.icon_description),
                            modifier = Modifier
                                .height(16.dp)
                                .width(14.dp)
                        )
                    },
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                )
                //Продавать бесплатно
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .align(Alignment.CenterHorizontally)
                ) {

                    Image(
                        painter = painterResource(R.drawable.background_card_cup),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = stringResource(R.string.icon_description),
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Продавать бесплатно",
                            color = colorResource(id = R.color.text_color_title),
                            fontSize = 14.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                                if (it) {
                                    textPrice = ""
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = colorResource(id = R.color.white),
                                checkedTrackColor = colorResource(id = R.color.orange),
                                uncheckedThumbColor = colorResource(id = R.color.white),
                                uncheckedTrackColor = colorResource(id = R.color.grey),
                            ),
                            modifier = Modifier

                                .size(width = 40.dp, height = 24.dp)
                        )
                    }
                }

                if (showButton) {
                    imageRes =
                        if (imageSelected) R.drawable.cup_cuppuccino else R.drawable.cup_mokkachino

                    MyButtonSave(
                        onClick = {
                            mainViewModel.saveDataChange(
                                DataItemCard(
                                    1,
                                    textName ?: "",
                                    imageRes ?: R.drawable.background_card_cup,
                                    "0.33",
                                    textPrice ?: ""
                                )
                            )
                            navController.popBackStack()
                        }
                    )
                }
            }

            //Выбор напитка 1
            Box {

                Image(
                    painter = painterResource(R.drawable.cup_cuppuccino),
                    contentDescription = stringResource(id = R.string.icon_cup),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(280.dp)
                        .padding(start = 70.dp)
                        .clickable {
                            imageSelected = true
                            textName = "Капучино"
                        }
                )

                if (imageSelected) {
                    Image(
                        painter = painterResource(R.drawable.cyrcle_select),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = stringResource(R.string.icon_description),
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomCenter)
                            .offset(35.dp)
                    )
                }

            }
            //Выбор напитка 2
            Box {
                Image(
                    painter = painterResource(R.drawable.cup_mokkachino),
                    contentDescription = stringResource(id = R.string.icon_cup),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(315.dp)
                        .padding(top = 40.dp, end = 70.dp)
                        .clickable {
                            imageSelected = false
                            textName = "Моккачино"
                        }
                )
                if (!imageSelected) {
                    Image(
                        painter = painterResource(R.drawable.cyrcle_select),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = stringResource(R.string.icon_description),
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomCenter)
                            .offset(x = (-35).dp, y = (-20).dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MyButtonSave(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 40.dp)
            .width(120.dp)
            .height(40.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.button_save),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Сохранить"
        )
    }
}
