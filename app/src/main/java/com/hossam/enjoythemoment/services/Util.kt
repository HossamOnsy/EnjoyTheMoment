package com.hossam.enjoythemoment.services

import android.app.job.JobScheduler
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Context
import android.os.Build


class Util {

    // schedule the start of the service every 10 - 30 seconds
      fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, TestJobService::class.java)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency((1 * 1000 * 5).toLong()) // wait at least
        builder.setOverrideDeadline((3 * 1000 * 10).toLong()) // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        val jobScheduler = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getSystemService(JobScheduler::class.java)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        jobScheduler.schedule(builder.build())
    }

}

