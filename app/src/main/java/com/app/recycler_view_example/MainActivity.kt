package com.app.recycler_view_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ViewAdapter()

    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image_view)
        val planetName = itemView.findViewById<TextView>(R.id.text_view)
    }

    private inner class ViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
            val viewHolder = ViewHolder(view)
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                viewHolder.planetName.setOnClickListener {
                    Toast.makeText(
                        parent.context,
                        viewHolder.bindingAdapterPosition + 1,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val listItem = planetList[holder.bindingAdapterPosition]
            holder.planetName.text = listItem.desc
            Glide
                .with(holder.image.context)
                .load(listItem.imageSrc)
                .centerCrop()
                .into(holder.image)
        }

        override fun getItemCount(): Int {
            return planetList.size
        }

    }
}

data class ListItem(val imageSrc: String, val desc: String)

private val planetList = listOf(
    ListItem(
        "https://cdn.mos.cms.futurecdn.net/3upZx2gxxLpW7MBbnKYQLH-1200-80.jpg",
        "Earth"
    ),
    ListItem(
        "https://space-facts.com/wp-content/uploads/mercury-v2.jpg",
        "Mercury"
    ),
    ListItem(
        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/600px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg",
        "Jupiter"
    ),
    ListItem(
        "https://cdnuploads.aa.com.tr/uploads/Contents/2021/06/24/thumbs_b_c_893677372fae34770e746c014acb4684.jpg?v=193356",
        "Venus"
    ),
    ListItem(
        "https://www.medianauka.pl/fizyka/grafika/big/saturn.jpg",
        "Saturn"
    ),
    ListItem(
        "https://www.urania.edu.pl/sites/default/files/2020-10/OSIRIS_Mars_true_color.jpg",
        "Mars"
    ),
    ListItem(
        "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Uranus2_%28cropped%29-1.jpg/1200px-Uranus2_%28cropped%29-1.jpg",
        "Uranus"
    ),
    ListItem(
        "https://cdn.mos.cms.futurecdn.net/eNTJrysq4A6DqXncBtsRrB-320-80.jpg",
        "Neptune"
    )
)