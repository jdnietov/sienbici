package co.edu.unal.sienbici.activities.QRReader

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.activities.ReaderResult.ReaderResultActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class QRReaderActivity : AppCompatActivity() {
    // UI components
    private lateinit var greeting: TextView
    private lateinit var message: TextView
    private lateinit var scanButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_reader_activity)

        // turn back button
        val actionbar = supportActionBar
        actionbar!!.title = "Escanear código"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayShowHomeEnabled(true)

        // initialize UI components
        greeting = findViewById(R.id.qr_reader_textview_greeting)
        message = findViewById(R.id.qr_reader_textview_message)
        scanButton = findViewById(R.id.qr_button_scan)

        scanButton.setOnClickListener {
            run {
                IntentIntegrator(this@QRReaderActivity).initiateScan();
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if(result != null){
            if(result.contents != null){
                if(result.contents.substring(0, 8) == "sienbici") {
                    val intent = Intent(this, ReaderResultActivity::class.java)
                    Log.e("QRReaderActivity", result.contents.substring(8))
                    intent.putExtra("EXTRA_QR_BIKEID", result.contents.substring(8))
                    startActivity(intent)
                } else {
                    greeting.text = "¡Lo sentimos!"
                    message.text = "El identificador que leíste no fue válido. Por favor, intenta de nuevo."
                    greeting.setTypeface(null, Typeface.BOLD)
                    greeting.setTextColor(ContextCompat.getColor(this, R.color.redError))
                    message.setTextColor(ContextCompat.getColor(this, R.color.redError))
                }
            } // TODO: handle error
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
