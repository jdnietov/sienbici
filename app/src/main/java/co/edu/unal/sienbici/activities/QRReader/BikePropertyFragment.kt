package co.edu.unal.sienbici.activities.QRReader

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import co.edu.unal.sienbici.R

class BikePropertyFragment(val readResult: String) : Fragment() {

    private lateinit var viewModel: BikePropertyViewModel
    private lateinit var message: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("BikePropertyFragment", readResult.substring(0, 8))
        val root = inflater.inflate(R.layout.bike_property_fragment, container, false)

        message = root.findViewById(R.id.bike_property_textview_message)
        if(readResult.substring(0, 8) == "sienbici")
            message.text = readResult.substring(8)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BikePropertyViewModel::class.java)
        // TODO: Use the ViewModel
    }
    
}
