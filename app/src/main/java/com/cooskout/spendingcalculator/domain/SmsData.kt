package com.cooskout.spendingcalculator.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SmsData(
    val creditSmsList: List<Message>,
    val DebitSmsList: List<Message>,
    val TotalCreditedAmount: Double,
    val totalDebitedAmount: Double
) : Parcelable