package com.hossam.enjoythemoment.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyStartServiceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Util().scheduleJob(context)
    }

}