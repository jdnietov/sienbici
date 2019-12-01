package co.edu.unal.sienbici.activities.MyBikes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Bike

class MyBikesAdapter(private val context: Context,
                     private val dataSource: ArrayList<Bike>) : BaseAdapter() {

    private val inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.my_bikes_bike_listitem, parent, false)

        val brandTextView = rowView.findViewById(R.id.bike_listitem_brand) as TextView
        val refTextView = rowView.findViewById(R.id.bike_listitem_ref) as TextView
        val serialTextView = rowView.findViewById(R.id.bike_listitem_serial) as TextView

        val bike = getItem(position) as Bike

        brandTextView.text = bike.brand
        refTextView.text = bike.ref
        serialTextView.text = bike.serial

        return rowView
    }
}