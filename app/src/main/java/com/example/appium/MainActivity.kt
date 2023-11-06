package com.example.appium

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.appium.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var locations = arrayListOf<String>()
    private val calendar = Calendar.getInstance()

    private lateinit var fromLocation: String
    private lateinit var toLocation: String

    private lateinit var fromDate: String
    private lateinit var toDate: String

    private lateinit var firstName: String
    private lateinit var lastName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initData()

        initFields()
        addClickListeners()
    }

    private fun initData() {
        locations.add("Lisbon")
        locations.add("Porto")
        locations.add("Faro")
        locations.add("Coimbra")
        locations.add("Braga")
        locations.add("Aveiro")
        locations.add("Setúbal")
        locations.add("Funchal")
        locations.add("Évora")
        locations.add("Viseu")
    }

    private fun initFields() {
        val fromLocationAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, locations)
        binding.spFromLocation.adapter = fromLocationAdapter

        val toLocationAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, locations.reversed())
        binding.spToLocation.adapter = toLocationAdapter
    }

    private fun addClickListeners() {
        binding.spFromLocation.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                fromLocation = locations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.spToLocation.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                toLocation = locations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.btSubmit.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        v.let {
            when (it?.id) {
                binding.btSubmit.id -> {
                    firstName = binding.inputFirstName.text.toString()
                    lastName = binding.inputLastName.text.toString()

                    fromDate = binding.inputFromDate.text.toString()
                    toDate = binding.inputToDate.text.toString()

                    if (fromLocation.isEmpty()) {
                        Toast.makeText(this, "From location cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (toLocation.isEmpty()) {
                        Toast.makeText(this, "To location cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (fromDate.isEmpty()) {
                        Toast.makeText(this, "From date cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (toDate.isEmpty()) {
                        Toast.makeText(this, "To date cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }

                    if (firstName.isEmpty()) {
                        Toast.makeText(this, "First name cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }
                    if (lastName.isEmpty()) {
                        Toast.makeText(this, "From location cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                        return
                    }


                    Toast.makeText(this, "Data submitted successfully", Toast.LENGTH_SHORT)
                        .show()
                }


            }
        }
    }

}