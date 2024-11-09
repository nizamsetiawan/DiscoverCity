package com.salma.app.myjepara

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailTourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_tour)
        window.statusBarColor = resources.getColor(R.color.toolbar_color, theme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tour = intent.getParcelableExtra<Tour>("tour")

        val ivTourDetail: ImageView = findViewById(R.id.iv_tour_detail)
        val tvItemName: TextView = findViewById(R.id.tv_item_name)
        val tvItemLocation: TextView = findViewById(R.id.tv_item_location)
        val tvItemFacilities: TextView = findViewById(R.id.tv_item_facilities)
        val tvItemHighlight: TextView = findViewById(R.id.tv_item_heighlight)
        val tvItemDescription: TextView = findViewById(R.id.tv_item_description)

        // Memastikan data tidak null
        tvItemName.text = tour?.name ?: ""
        tvItemLocation.text = tour?.location ?: ""
        tvItemFacilities.text = tour?.facilities ?: ""
        tvItemHighlight.text = tour?.highlight ?: ""
        tvItemDescription.text = tour?.description ?: ""
        ivTourDetail.setImageResource(tour?.photo ?: 0)

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        val shareAction: FloatingActionButton = findViewById(R.id.action_share) // Mengganti TextView dengan FloatingActionButton
        shareAction.setOnClickListener {
            shareItem(tour)
        }
    }

    private fun shareItem(tour: Tour?) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this tour!")
            putExtra(
                Intent.EXTRA_TEXT, """
                    ${tour?.name}
                    ${tour?.location}
                    ${tour?.facilities}
                    ${tour?.highlight}
                    ${tour?.description}
                """.trimIndent()
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
