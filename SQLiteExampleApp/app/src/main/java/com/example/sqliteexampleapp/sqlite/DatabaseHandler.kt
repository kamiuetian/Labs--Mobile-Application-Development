package com.example.sqliteexampleapp.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqliteexampleapp.sqlite.DBContract.EmpEntry.Companion.KEY_EMAIL
import com.example.sqliteexampleapp.sqlite.DBContract.EmpEntry.Companion.KEY_ID
import com.example.sqliteexampleapp.sqlite.DBContract.EmpEntry.Companion.KEY_NAME

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "EmployeeDatabase"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + DBContract.EmpEntry.TABLE_Name + "("
                + DBContract.EmpEntry.KEY_ID + " INTEGER PRIMARY KEY," + DBContract.EmpEntry.KEY_NAME + " TEXT,"
                + DBContract.EmpEntry.KEY_EMAIL + " TEXT" + ")")

        db?.execSQL(CREATE_CONTACTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${DBContract.EmpEntry.TABLE_Name}")
        onCreate(db)
    }
    /**
     * Function to insert data
     */
    fun addEmployee(emp: EmpModelClass): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        //contentValues.put(DBContract.EmpEntry.KEY_NAME, emp.name) // com.example.sqliteexampleapp.sqlite.EmpModelClass Name
        contentValues.put(DBContract.EmpEntry.KEY_EMAIL, emp.email) // com.example.sqliteexampleapp.sqlite.EmpModelClass Email

        // Inserting employee details using insert query.
        val success = db.insert(DBContract.EmpEntry.TABLE_Name,DBContract.EmpEntry.KEY_NAME , contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }
    //Method to read the records from database in form of ArrayList
    fun viewEmployee(): ArrayList<EmpModelClass> {

        val empList: ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()

        // Query to select all the records from the table.
        val selectQuery = "SELECT  * FROM ${DBContract.EmpEntry.TABLE_Name}"

        val db = this.readableDatabase
        // Cursor is used to read the record one by one. Add them to data model class.
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))

                val emp = EmpModelClass(id = id, name = name, email = email)
                empList.add(emp)

            } while (cursor.moveToNext())
        }
        return empList
    }
    /**
     * Function to update record
     */
    fun updateEmployee(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DBContract.EmpEntry.KEY_NAME, emp.name) // com.example.sqliteexampleapp.sqlite.EmpModelClass Name
        contentValues.put(DBContract.EmpEntry.KEY_EMAIL, emp.email) // com.example.sqliteexampleapp.sqlite.EmpModelClass Email

        // Updating Row
        val success = db.update(DBContract.EmpEntry.TABLE_Name, contentValues, DBContract.EmpEntry.KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }
    /**
     * Function to delete record
     */
    fun deleteEmployee(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.id) // com.example.sqliteexampleapp.sqlite.EmpModelClass id
        // Deleting Row
        val success = db.delete(DBContract.EmpEntry.TABLE_Name, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }
}