package com.example.fragmentslab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val f1=Fragment1()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainerView,f1.apply {
                arguments=Bundle().apply { putString("message","Hello") }
            })
        }
        /*Button1 click*/
        val btn1=findViewById<Button>(R.id.button)
        val btn2=findViewById<Button>(R.id.button2)
        btn2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,Fragemnt2())
                .addToBackStack(null)
                .commit()

        }
        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,Fragment1().apply {
                    arguments=Bundle().apply { putString("message","Hello from Fragment 2") }
                })
                .addToBackStack(null)
                .commit()

        }
    /*Bottom Navigation*/
      val bottomNav=findViewById<BottomNavigationView>(R.id.bottomnav)
        bottomNav.setOnItemSelectedListener {menuItem->
            return@setOnItemSelectedListener when(menuItem.itemId) {
                R.id.home->{supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,Fragment1())
                    .addToBackStack(null)
                    .commit()
                    return@setOnItemSelectedListener true
                }
                else->{supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,Fragemnt2())
                    .addToBackStack(null)
                    .commit()
                    return@setOnItemSelectedListener true}

            }
        }

    }

}
