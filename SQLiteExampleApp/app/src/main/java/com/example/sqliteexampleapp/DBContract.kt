package com.example.sqliteexampleapp

import android.provider.BaseColumns

class DBContract {
    /*inner class*/
    class EmpEntry:BaseColumns
    {
        companion object {
            val TABLE_Name = "EmployeeTable"
            val KEY_ID = "_id"
            val KEY_NAME = "name"
            val KEY_EMAIL = "email"
        }
    }
}