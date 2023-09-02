package dev.hossam.raseedy.view.splash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.R

@Composable
fun Splash(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF0085FF),
                    Color(0xFF001DB2),
                )
            )
        ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier,
            text = "رصيدي",
            fontSize = 48.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.cairo_medium))
        )
    }

}