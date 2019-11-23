package co.edu.unal.sienbici.ui.stolen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.sienbici.R
import co.edu.unal.sienbici.models.Robbery
import kotlinx.android.synthetic.main.main_stolen_card.view.*
import java.time.format.DateTimeFormatter

class StolenBikeAdapter (val bikes: ArrayList<Robbery>, val bikeClickListener: OnBikeClickListener) : RecyclerView.Adapter<ViewHolder> (){
    override fun getItemCount(): Int {
        return bikes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_stolen_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bikes[position], bikeClickListener)
    }

}

class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
    val brandView = itemView.stolen_card_textview_brand
    val colorView = itemView.stolen_color_text
    val placeView = itemView.stolen_place_text
    val timeView = itemView.stolen_time_text
    val imgView = itemView.stolen_image
    val buttonView = itemView.stolen_button

    fun bind(robbery: Robbery, clickListener: OnBikeClickListener){
        brandView.text = "Marca: ${robbery.brand} ${robbery.ref}"
        colorView.text = robbery.color
        placeView.text = robbery.place

        val formatter = DateTimeFormatter.ofPattern("d-M-y HH:mm:ss")
        timeView.text = robbery.date.format(formatter)
        imgView.setImageResource(robbery.img)

        buttonView.setOnClickListener{
            clickListener.onBikeClicked(robbery)
        }
    }
}

interface OnBikeClickListener {
    fun onBikeClicked(robbery: Robbery)
}