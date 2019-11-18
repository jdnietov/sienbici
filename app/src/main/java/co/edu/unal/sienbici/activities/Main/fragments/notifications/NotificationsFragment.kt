package co.edu.unal.sienbici.activities.Main.fragments.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import co.edu.unal.sienbici.activities.MyBikes.MyBikesActivity
import co.edu.unal.sienbici.activities.QRReader.QRReaderActivity
import co.edu.unal.sienbici.R

const val EXTRA_USERID = "co.edu.unal.sienbici.EXTRA_USERID"

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var listView : ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.main_notifications_fragment, container, false)

        listView = root.findViewById(R.id.list_notifications)
        val listItems = arrayOf("Mis bicicletas", "Identificar bicicleta", "Mis talleres", "Configuración")
        val adapter = ArrayAdapter(activity as Context, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter


        listView.setOnItemClickListener { _, _, position, _ ->
            when(position) {
                0 -> goToMyBikesActivity()
                1 -> startActivity(Intent(activity, QRReaderActivity::class.java))
            }
        }

        return root
    }

    private fun goToMyBikesActivity() {
        val intent = Intent(activity, MyBikesActivity::class.java)
        intent.putExtra(EXTRA_USERID, "12345")
        startActivity(intent)
    }
}
