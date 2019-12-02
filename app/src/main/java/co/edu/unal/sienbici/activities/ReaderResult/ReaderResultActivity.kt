package co.edu.unal.sienbici.activities.ReaderResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Bike
import com.google.firebase.database.*

class ReaderResultActivity : AppCompatActivity() {
    private lateinit var brand: TextView
    private lateinit var ref: TextView
    private lateinit var alert: TextView
    private lateinit var message: TextView
    private lateinit var button: Button

    private lateinit var bikeId: String
    private var bike: Bike? = null

    // Firebase variables
    private lateinit var bikeReference : DatabaseReference
    private var bikeListener : ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reader_result_activity)

        // turn back button
        val actionbar = supportActionBar
        actionbar!!.title = "Resultado de la búsqueda"
        actionbar.setDisplayHomeAsUpEnabled(true)

        bikeId = intent.getStringExtra("EXTRA_QR_BIKEID")

        brand = findViewById(R.id.bike_property_textview_brand)
        ref = findViewById(R.id.bike_property_textview_ref)
        alert = findViewById(R.id.readerresult_textview_alert)
        message = findViewById(R.id.readerresult_textview_message)
        button = findViewById(R.id.readerresult_button)

        bikeReference = FirebaseDatabase.getInstance().reference
            .child("bikes").child(bikeId)
    }

    override fun onStart() {
        super.onStart()
        val context = this

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                bike = dataSnapshot.getValue(Bike::class.java)
                bike?.let { bike ->
                    brand.text = bike.brand
                    ref.text = bike.ref

                    if(bike.reported) {
                        alert.setTextColor(ContextCompat.getColor(context, R.color.redError))
                        message.setTextColor(ContextCompat.getColor(context, R.color.redError))

                        alert.text = "¡Cuidado!"
                        message.text = "Esta bicicleta ha sido reportada como robada."

                        button.setOnClickListener() {
                            Toast.makeText(context, "Reported", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        alert.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        message.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))

                        alert.text = "¡Todo en orden!"
                        message.text = "Esta bicicleta no ha sido reportada."

                        button.setOnClickListener() {
                            finish()
                        }
                    }

                    Log.e("ReaderResultActivity", bike.brand)
                }
            }
        }

        bikeReference.addValueEventListener(valueEventListener)
        bikeListener = valueEventListener
    }

    override fun onStop() {
        super.onStop()

        bikeListener?.let { bikeReference.removeEventListener(bikeListener!!) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }
}
