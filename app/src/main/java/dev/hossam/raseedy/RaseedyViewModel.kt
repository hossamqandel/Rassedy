package dev.hossam.raseedy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hossam.raseedy.models.MethodBalance
import dev.hossam.raseedy.models.MethodPay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class RaseedyViewModel : ViewModel() {


    private var payJob: Job? = null
    private var balanceJob: Job? = null
    private var parentJob: Job = Job()

    companion object {
        private const val PERCENTAGE = 70
        private const val FORMAT = "%.2f"
    }

    private val _methodPayState = mutableStateOf(MethodPay("", "", ""))
    val methodPayState: State<MethodPay> = _methodPayState

    private val _methodBalanceState = mutableStateOf(MethodBalance("", "", ""))
    val methodBalanceState: State<MethodBalance> = _methodBalanceState


    fun calculate(amount: Double) {
        calculateByPay(amount)
        calculateByBalance(amount)
    }

    private fun calculateByPay(paid: Double) {
        payJob?.cancel()
        payJob = viewModelScope.launch(
            parentJob + Dispatchers.IO
        ) {
            val balance = (paid * PERCENTAGE) / 100
            val differenceAmount = paid - balance
            _methodPayState.value = methodPayState.value.copy(
                paidAmount = paid.toString(),
                balanceToGet = String.format(FORMAT, (balance)),
                differenceAmount = String.format(FORMAT, (differenceAmount))
            )
        }

    }

    private fun calculateByBalance(requiredBalance: Double) {
        balanceJob?.cancel()
        balanceJob = viewModelScope.launch(
            parentJob + Dispatchers.IO
        ) {
            val amountToPay = (requiredBalance * 10) / 7
            val differenceAmount = amountToPay - requiredBalance
            _methodBalanceState.value = methodBalanceState.value.copy(
                balance = String.format(FORMAT, (requiredBalance)),
                amountToPay = String.format(FORMAT, (amountToPay)),
                differenceAmount = String.format(FORMAT, (differenceAmount))
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancelChildren()
        parentJob.cancel()
    }
}