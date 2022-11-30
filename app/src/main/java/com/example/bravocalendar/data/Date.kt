package com.example.bravocalendar.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    val day: Int,
    val month: Int,
    val year: Int,
) : Parcelable
