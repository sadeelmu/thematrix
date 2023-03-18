package com.example.sadeelassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sadeelassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //reference
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.question -> replaceFragment(Question())
                R.id.red_door -> replaceFragment(RedDoor())
                R.id.blue_door -> replaceFragment(BlueDoor())
                else->{
                }
            }
            true
        }

        val theMatrix = Matrix()
        val fakeWorld = Fake()
        val redPill:Button = findViewById(R.id.redPill)
        val bluePill:Button = findViewById(R.id.bluePill)

        redPill.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame_layout,theMatrix)
                commit()
            }
        }
        bluePill.setOnClickListener(){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame_layout, fakeWorld)
                commit()
            }
        }

        val nameText:TextView = findViewById(R.id.helloName)
        val nameSpinner:Spinner = findViewById(R.id.nameSpinner)
        val options = arrayOf("Neo", "Morpheus", "Trinity", "The Oracle", "Agents")
        nameSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        nameSpinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               nameText.text ="Hello "+ options.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    //display fragments in FrameLayout
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}