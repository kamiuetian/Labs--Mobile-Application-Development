package com.example.sqliteexampleapp.emailsecnder

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.sqliteexampleapp.R

class SendEmailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_email)
        var btnSendEmail=findViewById<TextView>(R.id.tresp)
        btnSendEmail.setOnClickListener({sendEmail()})

    }

    private fun sendEmail() {
        var email= arrayOf("test@gmail.com")
        var subject="BSE-6A"
        var body="Test Message"
        var emailIntent= Intent()
        emailIntent.setAction(Intent.ACTION_SEND)
       emailIntent.setType("message/rfc822")
       emailIntent.setData(Uri.parse(""))
        emailIntent.putExtra(Intent.EXTRA_EMAIL,email)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT,body)
        startActivity(emailIntent)

    }

}