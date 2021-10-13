package com.example.lab3intents

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class secondActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        /*get Alert Buttons*/
        val simple=findViewById<Button>(R.id.SimpleAlert)
        val single=findViewById<Button>(R.id.singleOption)
        val multi=findViewById<Button>(R.id.multipleOption)
        val message =intent.getStringExtra("message")
        val text=findViewById<TextView>(R.id.textView2)
        text.text=message
        /*set onClickListener*/
        simple.setOnClickListener {

            val alert=AlertDialog.Builder(this)
            alert.setTitle("Example Alert Dialog")
                .setMessage("Do you want to accept this alert created by Simple Alert Dialog")
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton("yes"){dialoglinstener,i ->
                    Toast.makeText(this,"yes button clicked",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"
                ){dialoginteface,i->
                    Toast.makeText( this,"you clicked No",Toast.LENGTH_LONG).show()
                }
               val alert_dialog=alert.create()
                alert_dialog.show()

        }
        var options= arrayOf("First element", "second Element", "third element")
        var alert_single=AlertDialog.Builder(this)
        alert_single.setTitle("Single Choice ALert")
            .setSingleChoiceItems(options,0){_,i->
                Toast.makeText(this,"you checked ${options[i]}",Toast.LENGTH_LONG).show()
            }.create()

    single.setOnClickListener {
        alert_single.show()
    }

    var alert_multi=AlertDialog.Builder(this)
        alert_multi.setTitle("Multiple choice alert")
            .setMultiChoiceItems(options, booleanArrayOf(false,true,false)){dI,i,itemchecked->
                if(itemchecked)
                {
                    Toast.makeText(this,"You checked ${options[i]}",Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this,"You Un-checked ${options[i]}",Toast.LENGTH_LONG).show()

            }.create()

    multi.setOnClickListener {
        alert_multi.show()
    }














        /*single choice */
        /*var options= arrayOf("Frist item", "Second Item", "Third Item")
        var singlecoice=AlertDialog.Builder(this)
        singlecoice.setTitle("Single Choice")
        singlecoice.setSingleChoiceItems(options,0){dialoginterface,i->
            Toast.makeText(this,"you checked ${options[i]}",Toast.LENGTH_SHORT).show()
        }
            .setPositiveButton("yes"){d_i,i->
                Toast.makeText(this,"you accepted the single choice dialog",Toast.LENGTH_SHORT).show()

            }
        var single_dialog=singlecoice.create()
        single.setOnClickListener { single_dialog.show() }*/

    /*Multichoice Dialog
        var multichoice=AlertDialog.Builder(this)
        multichoice.setTitle("MultiChoice Dialog")
            .setMultiChoiceItems(options, booleanArrayOf(false,false,true)){d_i,i,itemchecked->
                if(itemchecked)
                Toast.makeText(this,"you checked ${options[i]}",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this,"you Un-checked ${options[i]}",Toast.LENGTH_SHORT).show()

            }
            .setPositiveButton("Accept"){d_i,i->
                Toast.makeText(this,"you accepted multi choice",Toast.LENGTH_SHORT).show()

            }
        var multi_dialog=multichoice.create()
        multi.setOnClickListener { multi_dialog.show() }
*/
            /*Alert Dialog with custom Layout*/
                var alert_custom=AlertDialog.Builder(this)
        var layout_custom=layoutInflater.inflate(R.layout.login_layout,null)
        alert_custom.setTitle("Login Dialog")
            .setView(layout_custom)
            .setPositiveButton("Login"){d_i,i->
              //  Toast.makeText(this,"You clicked login",Toast.LENGTH_LONG).show()
            }
        var c_dialog=alert_custom.create()
        c_dialog.show()
        c_dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
        val check=c_dialog.findViewById<TextView>(R.id.editTextTextPersonName)
            if(check!!.length()<5)
            {
                check.setBackgroundResource(R.color.red)
            }
            else
                c_dialog.dismiss()
        }




    }



}