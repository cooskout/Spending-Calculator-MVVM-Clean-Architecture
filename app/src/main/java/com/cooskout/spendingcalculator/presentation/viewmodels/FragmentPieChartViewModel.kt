package com.cooskout.spendingcalculator.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cooskout.spendingcalculator.data.repository.SmsRepository
import java.text.DecimalFormat
import java.text.NumberFormat

class FragmentPieChartViewModel  @ViewModelInject constructor(private val smsRepository: SmsRepository) : ViewModel() {


    val sms = smsRepository.getSmsLiveData()

    fun getformatedAmount(amount: Double) = formatCurrency(amount)

    private fun formatCurrency(number: Double): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(number)

        return "$formattedNumber.00"
    }
}