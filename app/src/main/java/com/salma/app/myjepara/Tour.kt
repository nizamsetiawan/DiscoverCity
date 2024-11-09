package com.salma.app.myjepara

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tour(
    val name: String,
    val placeName: String,
    val location: String,
    val facilities: String,
    val description: String,
    val highlight: String,
    val photo: Int
) : Parcelable
