package dev.hossam.raseedy.view.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.R
import dev.hossam.raseedy.core.isTablet


@Composable
fun RadioGroupButtons(
    isMethodPay: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    var currentIdx by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.how_much_to_pay),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = if (isTablet()) 28.sp else 20.sp,
                fontFamily = FontFamily(Font(R.font.cairo_medium))
            )

            Spacer(modifier = Modifier.width(2.dp))
            RadioButton(
                selected = currentIdx == 0,
                onClick = {
                    currentIdx = 0
                    isMethodPay.invoke(true)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.secondary,
                    unselectedColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }


        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.how_much_to_charge_the_balance),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = if (isTablet()) 28.sp else 20.sp,
                fontFamily = FontFamily(Font(R.font.cairo_medium))
            )

            Spacer(modifier = Modifier.width(2.dp))
            RadioButton(
                selected = currentIdx == 1,
                onClick = {
                    currentIdx = 1
                    isMethodPay.invoke(false)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.secondary,
                    unselectedColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}