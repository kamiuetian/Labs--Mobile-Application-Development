package com.example.sqliteexampleapp.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sqliteexampleapp.R

class MainActivity : AppCompatActivity() {
    lateinit var  rvItemsList:RecyclerView
    lateinit var tvNoRecordsAvailable:TextView
    lateinit var etUpdateName:EditText
    lateinit var etName:EditText
    lateinit var etEmailId:EditText
    lateinit var etUpdateId:EditText
    lateinit var tvUpdate:TextView
    lateinit var tvCancel:TextView
    lateinit var etUpdateEmailId:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAdd=findViewById<Button>(R.id.btnAdd)
        rvItemsList=findViewById(R.id.rvItemsList)
        tvNoRecordsAvailable=findViewById(R.id.tvNoRecordsAvailable)
        etName=findViewById(R.id.etName)
        etEmailId=findViewById(R.id.etEmailId)
        // Click even of the add button.
        btnAdd.setOnClickListener { view ->

            addRecord()
        }
        setupListofDataIntoRecyclerView()
    }
    /**
     * Function is used to show the list of inserted data.
     */
    private fun setupListofDataIntoRecyclerView() {

        if (getItemsList().size > 0) {

            rvItemsList.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsList.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsList.adapter = itemAdapter
        } else {

            rvItemsList.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    /**
     * Function is used to get the Items List from the database table.
     */
    private fun getItemsList(): ArrayList<EmpModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = databaseHandler.viewEmployee()

        return empList
    }

    //Method for saving the employee records in database
    private fun addRecord() {
        val name = etName.text.toString()
        val email = etEmailId.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty()) {
            val status =
                databaseHandler.addEmployee(EmpModelClass(0, name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                etName.text.clear()
                etEmailId.text.clear()

                setupListofDataIntoRecyclerView()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Name or Email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Method is used to show the Custom Dialog.
     */
    fun updateRecordDialog(empModelClass: EmpModelClass) {

        val dialog=AlertDialog.Builder(this)
        val inflator=layoutInflater
        val updateDialog=dialog.setTitle("Update Record")
            .setView(inflator.inflate(R.layout.dialog_update,null))
            .setPositiveButton("Update",){di,i->

                val name = etUpdateName.text.toString()
                val email = etUpdateEmailId.text.toString()

                val databaseHandler: DatabaseHandler = DatabaseHandler(this)

                if (!name.isEmpty() && !email.isEmpty()) {
                    val status =
                        databaseHandler.updateEmployee(EmpModelClass(empModelClass.id, name, email))
                    if (status > -1) {
                        Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                        setupListofDataIntoRecyclerView()
                        di.dismiss()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Name or Email cannot be blank",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .setNegativeButton("Cancel"){di,i->
                di.dismiss()
            }
            .create()

        updateDialog.show()
        etUpdateName= updateDialog.findViewById(R.id.etUpdateName)!!
        etUpdateEmailId= updateDialog.findViewById(R.id.etUpdateEmailId)!!
        etUpdateName.setText(empModelClass.name)
        etUpdateEmailId.setText(empModelClass.email)


    }

    /**
     * Method is used to show the Alert Dialog.
     */
    fun deleteRecordAlertDialog(empModelClass: EmpModelClass) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Record")
        builder.setMessage("Are you sure you wants to delete ${empModelClass.name}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = databaseHandler.deleteEmployee(EmpModelClass(empModelClass.id, "", ""))
            if (status > -1) {
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()

                setupListofDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }
}