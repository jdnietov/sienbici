package co.edu.unal.sienbici.activities.MyBikes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.activities.AddBike.AddBikeActivity

import co.edu.unal.sienbici.activities.Main.fragments.notifications.EXTRA_USERID
import co.edu.unal.sienbici.models.Bike

class MyBikesActivity : AppCompatActivity() {

    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_bikes_activity)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Mis bicicletas"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        // fab
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { _ -> goToAddBikeActivity() }

        // BikeList
        val bike1 = Bike(
            "Todoterreno",
            "blanca",
            "Trek",
            "modelo 1234",
            "123456",
            12
        )

        val bike2 = Bike(
            "BMX",
            "Negra",
            "Specialized",
            "modelo 1234",
            "123456",
            12
        )

        listView = findViewById<ListView>(R.id.my_bikes_listview)
        val bikeList = arrayListOf(bike1, bike2)
        val adapter = MyBikesAdapter(this, bikeList)
        listView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun goToAddBikeActivity() {
        val intent = Intent(this, AddBikeActivity::class.java)
        intent.putExtra(EXTRA_USERID, "12345")
        startActivity(intent)
    }
}
