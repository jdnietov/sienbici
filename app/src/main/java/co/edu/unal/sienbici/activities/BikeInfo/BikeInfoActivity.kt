package co.edu.unal.sienbici.activities.BikeInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Bike
import com.google.firebase.database.*

class BikeInfoActivity : AppCompatActivity() {

    // Intent extras
    private lateinit var bikeId : String

    // UI components
    private lateinit var editTextBrand : TextView
    private lateinit var editTextColor : TextView
    private lateinit var editTextSerial : TextView
    private lateinit var editTextDiameter : TextView

    // Firebase variables
    private lateinit var bikeReference : DatabaseReference
    private var bikeListener : ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_info)

        bikeId = intent.getStringExtra("EXTRA_BIKEID")
        bikeReference = FirebaseDatabase.getInstance().reference
            .child("bikes").child(bikeId)

        // fetch UI components
        editTextBrand = findViewById(R.id.bike_info_edittext_brand)
        editTextColor = findViewById(R.id.bike_info_edittext_color)
        editTextSerial = findViewById(R.id.bike_info_edittext_serial)
        editTextDiameter = findViewById(R.id.bike_info_edittext_diameter)

        // setup actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Mi bicicleta"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val bike = dataSnapshot.getValue(Bike::class.java)
                supportActionBar!!.title = "${bike?.brand} ${bike?.ref}"
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)

                editTextBrand.text = "Marca: ${bike?.brand} ${bike?.ref}"
                editTextColor.text = "Color: ${bike?.color}"
                editTextSerial.text = "Número de serie: ${bike?.serial}"
                editTextDiameter.text = "Diámetro del rin: ${bike?.diameter}"
            }

        }

        bikeReference.addValueEventListener(valueEventListener)
        bikeListener = valueEventListener
    }

    override fun onStop() {
        super.onStop()

        bikeListener?.let { bikeReference.removeEventListener(bikeListener!!) }
    }
}
