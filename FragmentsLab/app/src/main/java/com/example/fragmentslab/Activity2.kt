package com.example.fragmentslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val btn3=findViewById<Button>(R.id.button3)
        val btn4=findViewById<Button>(R.id.button4)
        val f3=Fragent3()
        btn3.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView2,f3.apply {
                    arguments=Bundle().apply {
                        putString("message","Hello from fragment")
                    }
                })
                commit()
            }
        }
        btn4.setOnClickListener {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView2,Fragemnt2())
            commit()
        }
        }
        val bottomNav=findViewById<BottomNavigationView>(R.id.bottomnav2)
        bottomNav.setOnItemSelectedListener {menuItem->
            return@setOnItemSelectedListener when(menuItem.itemId) {
                R.id.home->{supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2,Fragment1())
                    .addToBackStack(null)
                    .commit()
                    return@setOnItemSelectedListener true
                }
                else->{supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2,Fragemnt2())
                    .addToBackStack(null)
                    .commit()
                    return@setOnItemSelectedListener true}

            }
        }
    }

}