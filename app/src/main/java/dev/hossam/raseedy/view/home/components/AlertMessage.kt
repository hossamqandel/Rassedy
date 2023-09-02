package dev.hossam.raseedy.view.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.R

@Composable
fun AlertMessage(){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        lineHeight = 2.0.em,
        text = stringResource(id = R.string.alert_message),
        color = Color.Black.copy(alpha = 0.8f),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 19.sp,
    )

}