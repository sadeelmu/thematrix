package com.example.sadeelassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
    }

    //display fragments in FrameLayout
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}