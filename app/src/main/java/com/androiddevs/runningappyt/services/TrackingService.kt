package com.androiddevs.runningappyt.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.other.Constants.ACTION_NAVIGATE_TO_TRACKING_FRAGMENT
import com.androiddevs.runningappyt.other.Constants.ACTION_PAUSE_SERVICE
import com.androiddevs.runningappyt.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.androiddevs.runningappyt.other.Constants.ACTION_STOP_SERVICE
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_CHANNEL_ID
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.androiddevs.runningappyt.other.Constants.NOTIFICATION_ID
import com.androiddevs.runningappyt.ui.MainActivity
import timber.log.Timber

class TrackingService : LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if (isFirstRun) {

                        startForegroundService()
                        isFirstRun = false

                    } else {
                        Timber.d("Service resumed")
                    }
                }

                ACTION_PAUSE_SERVICE -> {
                    Timber.d("Service paused")
                }

                ACTION_STOP_SERVICE -> {
                    Timber.d("Service stopped")
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Tracking App")
            .setContentText("00:00:00")
            .setContentIntent(PendingIntent.getActivity(
                this,
                0,
                Intent(this, MainActivity::class.java).also {
                    it.action = ACTION_NAVIGATE_TO_TRACKING_FRAGMENT
                },
                FLAG_UPDATE_CURRENT
            ))
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }

}