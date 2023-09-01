package dev.hossam.raseedy

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.hossam.raseedy.models.MethodCharge
import dev.hossam.raseedy.models.MethodPay

class RaseedyViewModel : ViewModel() {

    companion object {
        private const val PERCENTAGE = 70
        private const val FORMAT = "%.2f"
    }
    private val _methodPayState = mutableStateOf(MethodPay("", "", ""))
    val methodPayState: State<MethodPay> = _methodPayState

    private val _methodChargeState = mutableStateOf(MethodCharge("", "", ""))
    val methodChargeState: State<MethodCharge> = _methodChargeState


    fun calculate(amount: Double){
        calculateByPay(amount)
        calculateByBalance(amount)
    }

    private fun calculateByPay(paid: Double){
        val balance =  (paid * PERCENTAGE) / 100
        val differenceAmount = paid - balance
        _methodPayState.value = methodPayState.value.copy(
            paidAmount = paid.toString(),
            balanceToGet = String.format(FORMAT, (balance)),
            differenceAmount = String.format(FORMAT, (differenceAmount))
        )
    }
    private fun calculateByBalance(requiredBalance: Double){
        val amountToPay = (requiredBalance * 10) / 7
        val differenceAmount = amountToPay - requiredBalance

        _methodChargeState.value = methodChargeState.value.copy(
            balance = String.format(FORMAT, (requiredBalance)),
            amountToPay = String.format(FORMAT, (amountToPay)),
            differenceAmount = String.format(FORMAT, (differenceAmount))
        )
    }
}