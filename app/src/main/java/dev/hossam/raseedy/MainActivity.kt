package dev.hossam.raseedy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import dev.hossam.raseedy.core.SharedPref
import dev.hossam.raseedy.ui.theme.RaseedyTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<RaseedyViewModel>()
    private val _isFirstVisit by lazy { SharedPref.getAppVisit() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isMethodBalance = rememberSaveable { mutableStateOf(true) }
            val isFirstVisit = rememberSaveable { mutableStateOf(_isFirstVisit) }
            var hasValue by rememberSaveable { mutableStateOf(false) }
            val balanceState = viewModel.methodPayState.value
            val payState = viewModel.methodChargeState.value


            RaseedyTheme {
                // A surface container using the 'background' color from the theme

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
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        },
                        text = {
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.alert_message),
                                fontSize = 19.sp,
                                lineHeight = 1.7.em,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily(Font(R.font.cairo_medium))
                            )
                        }
                    )
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HomeWidget(
                            isMethodPay = { isMethodBalance.value = it },
                            amount = { value ->
                                hasValue = value.isNotBlank()

                                if (value.isNotBlank()) {
                                    viewModel.calculate(value.toDouble())
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (hasValue) {
                            if (isMethodBalance.value) {
                                BoardResult(
                                    title = balanceState.paidAmount,
                                    titleMessage = R.string.how_much_to_pay,
                                    subTitle = balanceState.balanceToGet,
                                    subTitleMessage = R.string.you_will_get_balance,
                                    difference = balanceState.differenceAmount,
                                    differenceMessage = R.string.amountـdeducted
                                )
                            } else {
                                BoardResult(
                                    title = payState.balance,
                                    titleMessage = R.string.to_reach_balance,
                                    subTitle = payState.amountToPay,
                                    subTitleMessage = R.string.you_will_pay,
                                    difference = payState.differenceAmount,
                                    differenceMessage = R.string.amountـdeducted
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun HomeWidget(
    amount: (String) -> Unit,
    isMethodPay: (Boolean) -> Unit,
) {
    Column(modifier = Modifier) {

//        AlertMessage()
        CustomTextInput(onValueChange = amount)
        Spacer(modifier = Modifier.height(16.dp))
        Title(title = stringResource(id = R.string.choose_the_way))
        Spacer(modifier = Modifier.height(6.dp))
        RadioGroupButtons(
            isMethodPay = {
                isMethodPay.invoke(it)
            }
        )
    }
}