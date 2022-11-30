package com.example.bravocalendar.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Time(
    val hours: Int,
    val minutes: Int,
) : Parcelable
