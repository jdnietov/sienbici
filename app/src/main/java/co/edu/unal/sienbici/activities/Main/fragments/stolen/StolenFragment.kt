package co.edu.unal.sienbici.ui.stolen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.activities.Main.fragments.stolen.StolenSightingDialog
import co.edu.unal.sienbici.models.Robbery
import kotlin.collections.ArrayList

class StolenFragment : Fragment(), OnBikeClickListener {
    override fun onBikeClicked(robbery: Robbery) {
        val dialog = StolenSightingDialog(robbery)
        activity?.supportFragmentManager?.let { dialog.show(it, "confirmation") }
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
        val robberies = ArrayList<Robbery>()

        var robbery = Robbery("1", "Trek", "123", "Rojo", "Usaquén", R.drawable.bike1)
        robberies.add(robbery)
        robbery = Robbery("2", "Specialized", "789", "Negra", "La Candelaria", R.drawable.bike2)
        robberies.add(robbery)
        return robberies
    }
}
