package dev.hossam.raseedy.view.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.R
import dev.hossam.raseedy.core.isTablet


@Composable
fun Title(
    title: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 14.dp),
        text = ":".plus(title),
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
        fontSize = if (isTablet()) 28.sp else 19.sp,
        fontFamily = FontFamily(Font(R.font.cairo_medium))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextInput(
    onValueChange: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { value ->
            text = if (value.isBlank()) {
                value
            } else {
                when (value.toDoubleOrNull()) {
                    null -> text //old value
                    else -> value   //new value
                }
            }
            onValueChange.invoke(text)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    text = ""
                    onValueChange.invoke(text)
                },
                imageVector = Icons.Default.Clear,
                contentDescription = null
            )
        },
        singleLine = true,
        textStyle = TextStyle(
            textAlign = TextAlign.End,
            fontSize = 20.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}