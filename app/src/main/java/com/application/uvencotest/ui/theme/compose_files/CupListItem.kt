package com.application.uvencotest.ui.theme.compose_files

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.uvencotest.R
import com.application.uvencotest.data.DataItemCard


@Composable
fun CupListItem(cup: DataItemCard) {
    Box(
        modifier = Modifier
            .height(313.dp)
            .width(227.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.background_card_cup),
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(R.string.icon_description),
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(cup.imageId),
                    contentDescription = stringResource(id = R.string.icon_cup),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = cup.title,
                    color = colorResource(id = R.color.light_brown),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
            BottomTab(cup)
        }
    }
}

@Composable
fun BottomTab(cup: DataItemCard) {
    Box {
        Image(
            painter = painterResource(R.drawable.bottom_background),
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(R.string.icon_description),
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(bottomEnd = 6.dp, bottomStart = 6.dp))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cup.volume,
                color = colorResource(id = R.color.grey),
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                textAlign = if (cup.price != "") TextAlign.Start else TextAlign.Center,
            )

            if (cup.price != "") {
                Text(
                    text = "${cup.price} ₽",
                    color = colorResource(id = R.color.orange),
                    fontSize = 16.sp
                )
            }

        }
    }
}
