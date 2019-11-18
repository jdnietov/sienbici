package co.edu.unal.sienbici.activities.QRReader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import co.edu.unal.sienbici.R
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class QRReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_reader_activity)

        // turn back button
        val actionbar = supportActionBar
        actionbar!!.title = "Escanear código"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val btnScan : Button = findViewById(R.id.qr_button_scan)
        btnScan.setOnClickListener {
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
        val txtValue : TextView = findViewById(R.id.qr_text_value)

        if(result != null){
            if(result.contents != null){
                txtValue.text = result.contents
            } else {
                txtValue.text = "scan failed"
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
