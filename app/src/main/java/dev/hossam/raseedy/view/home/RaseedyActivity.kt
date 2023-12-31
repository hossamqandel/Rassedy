package dev.hossam.raseedy.view.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.hossam.raseedy.view.home.components.BoardResult
import dev.hossam.raseedy.view.home.components.CustomDialogMessage
import dev.hossam.raseedy.view.home.components.CustomTextInput
import dev.hossam.raseedy.R
import dev.hossam.raseedy.view.home.components.RadioGroupButtons
import dev.hossam.raseedy.view.home.components.RaseedyViewModel
import dev.hossam.raseedy.view.home.components.Title
import dev.hossam.raseedy.core.isTablet
import dev.hossam.raseedy.ui.theme.RaseedyTheme

class RaseedyActivity : ComponentActivity() {
    private val viewModel by viewModels<RaseedyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isMethodBalance = rememberSaveable { mutableStateOf(true) }
            var hasValue by rememberSaveable { mutableStateOf(false) }
            val balanceState = viewModel.methodPayState.value
            val payState = viewModel.methodBalanceState.value


            RaseedyTheme {

                CustomDialogMessage()

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
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

                        if (isTablet()) Spacer(modifier = Modifier.height(160.dp))
                         else Spacer(modifier = Modifier.height(16.dp))

                        if (hasValue) {
                            if (isMethodBalance.value) {
                                BoardResult(
                                    fontSize = if (isTablet()) 32 else 19,
                                    title = balanceState.paidAmount,
                                    titleMessage = R.string.how_much_to_pay,
                                    subTitle = balanceState.balanceToGet,
                                    subTitleMessage = R.string.you_will_get_balance,
                                    difference = balanceState.differenceAmount,
                                    differenceMessage = R.string.amountـdeducted
                                )
                            } else {
                                BoardResult(
                                    fontSize = if (isTablet()) 32 else 19,
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

        Spacer(modifier = Modifier.height(16.dp))
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