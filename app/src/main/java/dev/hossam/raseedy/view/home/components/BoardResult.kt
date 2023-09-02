package dev.hossam.raseedy.view.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.R

@Composable
fun BoardResult(
    title: String,
    @StringRes titleMessage: Int,
    subTitle: String,
    @StringRes subTitleMessage: Int,
    difference: String,
    @StringRes differenceMessage: Int = R.string.amountـdeducted,
    fontSize: Int = 19,
    fontWeight: FontWeight = FontWeight.SemiBold
){

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = titleMessage).plus(" = $title").plus(" ج"),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.cairo_medium))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = subTitleMessage).plus(" = $subTitle").plus(" ج"),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.cairo_medium))
        )



        Spacer(modifier = Modifier.height(4.dp))


        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = differenceMessage).plus(" = $difference").plus(" ج"),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.cairo_medium))
        )
    }
}