package co.edu.unal.sienbici.ui.stolen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Robbery

class StolenFragment : Fragment(), OnBikeClickListener {

    override fun onBikeClicked(bike: Robbery) {
        val dialog = StolenSightingDialog(bike)
        activity?.supportFragmentManager?.let { dialog.show(it, "confirmation") }
        //Toast.makeText(this.activity, "Cicla de tipo: ${bike.type}", Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_stolen_fragment, container, false)

        val mRecyclerView = root.findViewById<RecyclerView>(R.id.stolen_list)

        mRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        mRecyclerView.adapter = StolenBikeAdapter(getBikes(), this)
        return root
    }

    private fun getBikes(): ArrayList<Robbery> {
        val bikes = ArrayList<Robbery>()

        var bike = Robbery("BMX", "Negro", "Bosa", "11-12-2019", R.drawable.bike1 )
        bikes.add(bike)
        bike = Robbery("Todo-Terreno", "Blanco", "La Candelaria", "13-12-2019", R.drawable.bike2 )
        bikes.add(bike)
        bike = Robbery("Urbana", "Azul", "Bosa", "5-12-2019", R.drawable.bike3 )
        bikes.add(bike)
        bike = Robbery("Hibrida", "Rojo", "Suba", "11-12-2019", R.drawable.bike4 )
        bikes.add(bike)
        bike = Robbery("Plegable", "Negro", "Los Martires", "01-12-2019", R.drawable.bike1 )
        bikes.add(bike)
        bike = Robbery("BMX", "Negro", "Bosa", "11-12-2019", R.drawable.bike4 )
        bikes.add(bike)
        return bikes
    }
}
