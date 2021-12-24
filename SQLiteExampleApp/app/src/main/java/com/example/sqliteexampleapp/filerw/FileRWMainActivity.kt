package com.example.sqliteexampleapp.filerw

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.sqliteexampleapp.R
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FileRWMainActivity : AppCompatActivity() {
    var FILE_NAME="myfile.txt"
    lateinit var btnSave:Button
    lateinit var btnRead:Button
    lateinit var etMsg:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_rwmain)
        initwidgets()
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ANSWER_PHONE_CALLS)== PackageManager.PERMISSION_GRANTED
        )

        {

        }
        else
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ANSWER_PHONE_CALLS),111)       }
        /*Button SAVE*/
        btnSave.setOnClickListener({
            var msg:String=etMsg.text.toString()
            var appFile=checkandcreateDir() // /sdcard/SqlExampleApp/myfie.txt
            var fos:FileOutputStream= FileOutputStream(appFile)
            fos.write(msg.encodeToByteArray())
            fos.close()
        })

        /*Read Button*/
        btnRead.setOnClickListener({
            var appFile=checkandcreateDir() //sdcard/SqlExampleApp/myfile.txt
            var fis=FileInputStream(appFile)
            val b= ByteArray(fis.available())
            fis.read(b)
            fis.close()
            etMsg.setText(String(b))
        })
    }

    private fun checkandcreateDir(): File{
        var appFile:File //null
        appFile =getExternalFilesDir("")!! // /sdcard
        Log.d("filePath",appFile.toString())
        appFile= File(appFile,getString(R.string.app_name))// /sdcard/SqlExampleApp
        if(!appFile.exists()) // check if exists
        {
            if(appFile.mkdir()) // /sdcard/sqlExampleApp
            {
                Toast.makeText(this,"File Created",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"File not Created",Toast.LENGTH_LONG).show()
        }
        /*file Already Exists*/
        appFile=File(appFile,FILE_NAME)
        return appFile // /sdcard/SqlExampleApp/myfile.txt
    }

    private fun initwidgets() {
        btnSave=findViewById(R.id.btnSave)
        btnRead=findViewById(R.id.btnRead)
        etMsg=findViewById(R.id.etMsg)
    }
    /*REquuest Permission Callback*/
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

            }
            else
            {
                Toast.makeText(applicationContext,"No permissions",Toast.LENGTH_LONG).show()
            }
        }
    }
}