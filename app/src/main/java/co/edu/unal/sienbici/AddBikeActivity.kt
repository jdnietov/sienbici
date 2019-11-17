package co.edu.unal.sienbici

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class AddBikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bike)

        val colorSpinner: Spinner = findViewById(R.id.add_bike_spinner_color)
        ArrayAdapter.createFromResource(
            this,
            R.array.colors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colorSpinner.adapter = adapter
        }

        val bikeTypeSpinner: Spinner = findViewById(R.id.add_bike_spinner_bike_type)
        ArrayAdapter.createFromResource(
            this,
            R.array.bike_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bikeTypeSpinner.adapter = adapter
        }

        val btn = findViewById<Button>(R.id.add_bike_button)
        val editTextName = findViewById<EditText>(R.id.add_bike_edittext_name)
        val editTextReference = findViewById<EditText>(R.id.add_bike_edittext_brand)

        btn.setOnClickListener {
            println(editTextName.text)
            println(editTextReference.text)
        }

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Agregar una nueva bicicleta"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
