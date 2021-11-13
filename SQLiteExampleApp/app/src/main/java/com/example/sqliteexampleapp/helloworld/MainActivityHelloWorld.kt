package com.example.sqliteexampleapp.helloworld

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sqliteexampleapp.R

class MainActivityHelloWorld : AppCompatActivity() {
    private val CHANNEL_ID="com.example.SQLiteExample.my_app_id"
    private val notificationId=123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hello_world)
        createNotification()
        val btn:Button=findViewById<Button>(R.id.button)
        btn.setOnClickListener{
            sendNotification()
        }
    }

    fun createNotification()
    {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            val name="Notification Channel"
            val desc="This is the notification Cahnnel"
            val imp=NotificationManager.IMPORTANCE_DEFAULT
            val channel:NotificationChannel=NotificationChannel(CHANNEL_ID,name,imp).apply {
                desc
            }
            val notifyManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyManager.createNotificationChannel(channel)
        }

    }
    fun sendNotification(){
        val intent= Intent(this,MainActivityHelloWorld::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent=PendingIntent.getActivity(this,0,intent,0)
        val builder=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("A Notification")
            .setContentText("A large text that will show on drag down of notificationA large text that will show on drag down of notificationA large text that will show on drag down of notificationA large text that will sh")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId,builder.build())
        }
    }
    /*fun sendNotification()
    {
        val intent= Intent(this,MainActivityHelloWorld::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val bmp =BitmapFactory.decodeResource(this.resources,R.drawable.ic_icons8_delete)
        val pendingIntent=PendingIntent.getActivity(this,0,intent,0)
        val builder=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Example Notification")
            .setContentText("This is description")
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bmp).bigLargeIcon(null))

            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId,builder.build())
        }


    }
*/
    /*fun sendNotification(view: android.view.View) {
        val intent= Intent(this,MainActivityHelloWorld::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent= PendingIntent.getActivity(this,0,intent,0)
        val builder=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Example Title")
            .setContentText("Description")
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText("This is a really large text that cannot fit in one line"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId,builder.build())
        }
    }*/
}