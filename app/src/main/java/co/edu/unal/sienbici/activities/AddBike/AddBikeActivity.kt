package co.edu.unal.sienbici.activities.AddBike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Bike
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddBikeActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference

    private lateinit var btn : Button
    private lateinit var editTextBrand : EditText
    private lateinit var editTextReference : EditText
    private lateinit var editTextSerial : EditText
    private lateinit var editTextDiameter : EditText
    private lateinit var spinnerColor : Spinner
    private lateinit var spinnerType : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_bike_activity)

        // add adapter to Colors spinner
        val colorSpinner: Spinner = findViewById(R.id.add_bike_spinner_color)
        ArrayAdapter.createFromResource(
            this,
            R.array.colors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colorSpinner.adapter = adapter
        }

        // add adapter to BikeType spinner
        val bikeTypeSpinner: Spinner = findViewById(R.id.add_bike_spinner_bike_type)
        ArrayAdapter.createFromResource(
            this,
            R.array.bike_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            bikeTypeSpinner.adapter = adapter
        }

        // create Firebase database reference
        database = FirebaseDatabase.getInstance().reference

        // initialize UI components
        btn = findViewById(R.id.add_bike_button)
        editTextBrand = findViewById(R.id.add_bike_edittext_brand)
        editTextDiameter = findViewById(R.id.add_bike_edittext_diameter)
        editTextReference = findViewById(R.id.add_bike_edittext_brand)
        editTextSerial = findViewById(R.id.add_bike_edittext_serial)

        spinnerColor = findViewById((R.id.add_bike_spinner_color))
        spinnerType = findViewById((R.id.add_bike_spinner_bike_type))


        btn.setOnClickListener {
            pushBike()
        }

        // set actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Agregar una nueva bicicleta"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun pushBike() {
        val bike = Bike(
            spinnerType.selectedItem.toString(),
            spinnerColor.selectedItem.toString(),
            editTextBrand.text.toString(),
            editTextReference.text.toString(),
            editTextSerial.text.toString(),
            editTextDiameter.text.toString().toInt()
        )
        val key = database.child("bikes").push().key
        key?.let { database.child("bikes").child(it).setValue(bike) }
    }
}
