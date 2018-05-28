package com.pedromassango.alarmmanagersample

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent to start the Broadcast Receiver
        val broadcastIntent = Intent(this, AlarmBroadcastReceiver::class.java)

        // The Pending Intent to pass in AlarmManager
        val pIntent = PendingIntent.getBroadcast(
                this,
                0,
                broadcastIntent,
                0
        )

        // Setting up AlarmManager
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        // schedule the alarm whn button is pressed
        // Button click to set the alarm
        btn_set_alarm.setOnClickListener {

            // Set an alarm to trigger 5 second after the button is pressed
            alarmMgr.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + 5000,
                    pIntent)

            // show a message that the alarm was set
            Toast.makeText(
                    this@MainActivity,
                    "Alarm was set, please wait.",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Setup the date to trigger the alarm.
     * @return the date in milliseconds to trigger the alarm
     */
    private fun dateForAlarm(): Long{
        val calendar = Calendar.getInstance()
        //calendar.timeInMillis = System.currentTimeMillis()
        //calendar.set(Calendar.HOUR_OF_DAY, 21)
        //calendar.set(Calendar.MINUTE, 13)
        calendar.set(Calendar.SECOND, 30)

        return calendar.timeInMillis
    }
}
