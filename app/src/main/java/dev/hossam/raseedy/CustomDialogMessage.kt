package dev.hossam.raseedy

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.core.SharedPref
import dev.hossam.raseedy.core.isTablet

@Composable
fun CustomDialogMessage() {
    val isFirstVisit = rememberSaveable { mutableStateOf(SharedPref.getAppVisit()) }
    if (isFirstVisit.value) {
        AlertDialog(
            modifier = Modifier,
            onDismissRequest = {
                isFirstVisit.value = !isFirstVisit.value
                SharedPref.setFirstVisit(false)
            },
            confirmButton = {
                ElevatedButton(onClick = {
                    isFirstVisit.value = !isFirstVisit.value
                    SharedPref.setFirstVisit(false)
                }) {
                    Text(
                        text = "حسنا",
                        fontSize = if (isTablet()) 28.sp else 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            text = {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.alert_message),
                    fontSize = if (isTablet()) 28.sp else 19.sp,
                    lineHeight = 1.7.em,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.cairo_medium))
                )
            }
        )
    }

}