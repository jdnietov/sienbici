package co.edu.unal.sienbici.ui.stolen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import kotlinx.android.synthetic.main.card_stolen.view.*

class StolenBikeAdapter (val bikes: ArrayList<StolenBikeModel>, val bikeClickListener: OnBikeClickListener) : RecyclerView.Adapter<ViewHolder> (){
    override fun getItemCount(): Int {
        return bikes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_stolen, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bikes[position], bikeClickListener)
    }

}

class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
    val typeView = itemView.stolen_type_text
    val colorView = itemView.stolen_color_text
    val placeView = itemView.stolen_place_text
    val timeView = itemView.stolen_time_text
    val imgView = itemView.stolen_image
    val buttonView = itemView.stolen_button

    fun bind(bike: StolenBikeModel, clickListener: OnBikeClickListener){
        typeView.text = bike.type
        colorView.text = bike.color
        placeView.text = bike.place
        timeView.text = bike.time
        imgView.setImageResource(bike.img)

        buttonView.setOnClickListener{
            clickListener.onBikeClicked(bike)
        }
    }
}

interface OnBikeClickListener {
    fun onBikeClicked(bike: StolenBikeModel)
}