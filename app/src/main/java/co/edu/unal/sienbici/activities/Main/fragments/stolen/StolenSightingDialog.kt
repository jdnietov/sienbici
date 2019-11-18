package co.edu.unal.sienbici.ui.stolen

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import co.edu.unal.sienbici.models.Robbery
import java.lang.IllegalStateException

class StolenSightingDialog(bike: Robbery) : DialogFragment() {
    var bike = bike
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)

            builder.setMessage("¿Desea reportar que vio esta bicicleta cerca a donde se encuentra actualmente?")
                .setPositiveButton("SI", DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(this.activity, "Bicicleta Reportada", Toast.LENGTH_LONG).show()
                })
                .setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
                })
            builder.create()
        } ?: throw  IllegalStateException("Activity cannot be null")
    }
}