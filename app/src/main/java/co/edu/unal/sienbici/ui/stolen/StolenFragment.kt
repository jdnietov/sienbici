package co.edu.unal.sienbici.ui.stolen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import kotlinx.android.synthetic.main.fragment_stolen.*

class StolenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_stolen, container, false)

        val mRecyclerView = root.findViewById<RecyclerView>(R.id.stolen_list)

        mRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        mRecyclerView.adapter = this.activity?.let { StolenBikeAdapter(getBikes(), it) }
        return root
    }

    fun getBikes(): ArrayList<StolenBikeModel> {
        val bikes = ArrayList<StolenBikeModel>()

        var bike = StolenBikeModel("BMX", "Negro", "Bosa", "11-12-2019", R.drawable.bike1 )
        bikes.add(bike)
        bike = StolenBikeModel("Todo-Terreno", "Blanco", "La Candelaria", "13-12-2019", R.drawable.bike2 )
        bikes.add(bike)
        bike = StolenBikeModel("Urbana", "Azul", "Bosa", "5-12-2019", R.drawable.bike3 )
        bikes.add(bike)
        bike = StolenBikeModel("Hibrida", "Rojo", "Suba", "11-12-2019", R.drawable.bike4 )
        bikes.add(bike)
        bike = StolenBikeModel("Plegable", "Negro", "Los Martires", "01-12-2019", R.drawable.bike1 )
        bikes.add(bike)
        bike = StolenBikeModel("BMX", "Negro", "Bosa", "11-12-2019", R.drawable.bike4 )
        bikes.add(bike)
        return bikes
    }
}
