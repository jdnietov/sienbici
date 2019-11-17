package co.edu.unal.sienbici.ui.stolen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import kotlinx.android.synthetic.main.card_stolen.view.*

class StolenBikeAdapter (val bikes: ArrayList<StolenBikeModel>, val context: Context) : RecyclerView.Adapter<ViewHolder> (){
    override fun getItemCount(): Int {
        return bikes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_stolen, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.typeView.text = bikes[position].type
        holder.colorView.text = bikes[position].color
        holder.placeView.text = bikes[position].place
        holder.timeView.text = bikes[position].time
        holder.imgView.setImageResource(bikes[position].img)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder (view) {
    val typeView = view.stolen_type_text
    val colorView = view.stolen_color_text
    val placeView = view.stolen_place_text
    val timeView = view.stolen_time_text
    val imgView = view.stolen_image
}