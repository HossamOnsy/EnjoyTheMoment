package com.hossam.enjoythemoment

import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.app.job.JobInfo
import android.media.AudioManager
import com.hossam.enjoythemoment.services.TestJobService
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    lateinit private var serviceComponent: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions()

    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NOTIFICATION_POLICY)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_NOTIFICATION_POLICY), 2)
            // Permission is not granted
        } else {
            checkModifyAudio()
        }
    }


    fun locationPermissionChecking() {
        try {
            val mode = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            if (mode.ringerMode != AudioManager.RINGER_MODE_SILENT)
                mode.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        } catch (e: Exception) {

        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 0)
            // Permission is not granted
        } else {
            jobSchedule()
        }
    }

    fun jobSchedule() {
        val componentName = ComponentName(this, TestJobService::class.java)
        val jobInfo = JobInfo.Builder(12, componentName)
            .setRequiresCharging(true)
            .build()
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(jobInfo)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            0 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    jobSchedule()
                } else {
                    Toast.makeText(this, "You need to Activate Permission", Toast.LENGTH_SHORT).show()
                }
            }
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                    locationPermissionChecking()
                } else {
                    Toast.makeText(this, "You need to Activate Permission", Toast.LENGTH_SHORT).show()

                }
            }
            2 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    checkModifyAudio()
                } else {
                    Toast.makeText(this, "You need to Activate Permission", Toast.LENGTH_SHORT).show()

                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    private fun checkModifyAudio() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.MODIFY_AUDIO_SETTINGS)
            != PackageManager.PERMISSION_GRANTED

        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.MODIFY_AUDIO_SETTINGS), 1)
            // Permission is not granted
        } else {

            locationPermissionChecking()
        }
    }

    private fun cancelAllJobs() {
        (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).cancelAll()
    }

    override fun onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
//        stopService(Intent(this, TestJobService::class.java))
        super.onStop()
    }
}
