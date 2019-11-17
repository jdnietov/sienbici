package co.edu.unal.sienbici

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import co.edu.unal.sienbici.ui.notifications.EXTRA_USERID

class MyBikesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bikes)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "New Activity"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { _ -> goToAddBikeActivity() }
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
