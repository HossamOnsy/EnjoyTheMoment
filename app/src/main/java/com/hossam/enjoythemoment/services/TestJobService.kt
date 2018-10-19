package com.hossam.enjoythemoment.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent


class TestJobService : JobService() {
    override fun onStopJob(params: JobParameters?): Boolean {

        return true
    }


    override fun onStartJob(params: JobParameters?): Boolean {
//        Toast.makeText(applicationContext, "entered", Toast.LENGTH_SHORT).show()

        val service = Intent(getApplicationContext(), ServiceTryout::class.java)
        getApplicationContext().startService(service)
        Util().scheduleJob(getApplicationContext())

        return true
    }


}