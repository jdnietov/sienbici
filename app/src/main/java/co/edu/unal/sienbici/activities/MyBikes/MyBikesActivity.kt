package co.edu.unal.sienbici.activities.MyBikes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.activities.AddBike.AddBikeActivity
import co.edu.unal.sienbici.activities.BikeInfo.BikeInfoActivity

import co.edu.unal.sienbici.activities.Main.fragments.notifications.EXTRA_USERID
import co.edu.unal.sienbici.models.Bike
import com.google.firebase.database.*

class MyBikesActivity : AppCompatActivity() {

    private lateinit var listView : ListView
    private lateinit var bikeList : ArrayList<Bike>
    private lateinit var idList : ArrayList<String>

    private lateinit var bikesReference : DatabaseReference
    private var bikesListener : ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_bikes_activity)

        // initialize data
        bikeList = ArrayList()
        idList = ArrayList()

        // setup actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Mis bicicletas"
        actionbar.setDisplayHomeAsUpEnabled(true)

        // fab
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { _ -> goToAddBikeActivity() }

        // create Firebase database reference
        bikesReference = FirebaseDatabase.getInstance().reference.child("bikes")
        listView = findViewById(R.id.my_bikes_listview)

        // add on item click listener
        listView.setOnItemClickListener{ _, _, position, _ ->
            val intent = Intent(this, BikeInfoActivity::class.java)
            intent.putExtra("EXTRA_BIKEID", idList[position])
            Log.e("Position", bikeList[position].toString())
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("The read failed: ", p0.message);
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("Count " ,""+snapshot.childrenCount);
                bikeList = ArrayList()
                idList = ArrayList()
                for (postSnapshot in snapshot.children) {
                    bikeList.add(postSnapshot.getValue(Bike::class.java) as Bike)
                    idList.add(postSnapshot.key!!)
                }
            }
        }

        Log.e("MyBikesActivity", bikeList.toString())
        listView.adapter = MyBikesAdapter(this, bikeList)
        bikesReference.addValueEventListener(valueEventListener)

        bikesListener = valueEventListener
    }

    override fun onStop() {
        super.onStop()

        bikesListener?.let { bikesReference.removeEventListener(bikesListener!!) }
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
