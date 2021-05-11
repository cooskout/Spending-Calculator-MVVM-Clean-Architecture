package com.cooskout.spendingcalculator.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(val number: String, val body: String, val date: Date, val id: String) :
    Parcelable
