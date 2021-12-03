package com.example.sqliteexampleapp.smsapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.sqliteexampleapp.R
import java.util.*
import android.content.IntentFilter

import android.app.PendingIntent
import android.util.Log


class SMS_MainActivity : AppCompatActivity() {
    lateinit var tNumb:TextView
    lateinit var tMessage:TextView
    lateinit var etReply:EditText
    lateinit var btnReply:Button
    val SENT_ACTION="org.example.smsapp.SENT_ACTION"
    val DELIVERY_ACTION="org.example.DELIVERY_ACTION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_main)
        initwidgets()
        /*PendinIntents for Sent and Delivery*/
        val sentIntent = PendingIntent.getBroadcast(this,
            100,
            Intent(SENT_ACTION), 0)

        val deliveryIntent = PendingIntent.getBroadcast(this, 200, Intent(DELIVERY_ACTION), 0)
val sentRecvr=object:BroadcastReceiver()
{
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("SMS ", "sent")
    }

}
        val it=IntentFilter(SENT_ACTION)
        registerReceiver(sentRecvr, it)

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("SMS ", "delivered")
            }
        }, IntentFilter(DELIVERY_ACTION))
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)==PackageManager.PERMISSION_GRANTED)

        {
            recv_MSG()
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS),111)       }
        /*Send Reply
        **/
        btnReply.setOnClickListener({
            var sms=SmsManager.getDefault()
            sms.sendTextMessage(tNumb.text.toString(),
                "CR",
                etReply.text.toString(),
                sentIntent,deliveryIntent)
        })
    }


    private fun initwidgets() {
        tNumb=findViewById(R.id.phone)
        tMessage=findViewById(R.id.message)
        etReply=findViewById(R.id.etReply)
        btnReply=findViewById(R.id.btn_reply)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if(requestCode==111)
    {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            recv_MSG()
        }
        else
        {
            Toast.makeText(applicationContext,"No permissions",Toast.LENGTH_LONG).show()
        }
    }
    }
    private fun recv_MSG() {
        val recvBroadcast:BroadcastReceiver=object:BroadcastReceiver(){
            override fun onReceive(p0: Context?, p1: Intent?) {
                for (sms in Telephony.Sms.Intents.getMessagesFromIntent(p1))
                {
                    tNumb.setText(sms.originatingAddress)
                    tMessage.setText(sms.displayMessageBody)
                }
            }

        }
        /*register receiver for Received SMS*/
        val it:IntentFilter= IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        registerReceiver(recvBroadcast,it)

    }
}