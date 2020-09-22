package com.androiddevs.runningappyt.db

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tbl_running")
data class Run(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    var img: Bitmap? = null,

    var timestamp: Long = 0L,

    var avgSpeedInKMH: Float = 0f,

    var distanceInMeters: Int = 0,

    var timeInMillis: Long = 0L,

    var caloriesBurned: Int = 0
) : Parcelable