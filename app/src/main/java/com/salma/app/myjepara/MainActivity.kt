package com.salma.app.myjepara

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTour: RecyclerView
    private val list = ArrayList<Tour>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        window.statusBarColor = resources.getColor(R.color.toolbar_color, theme)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val aboutPage: ImageView = findViewById(R.id.about_page)
        aboutPage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        rvTour = findViewById(R.id.rv_tour)
        rvTour.setHasFixedSize(true)
        list.addAll(getListTour())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        rvTour.layoutManager = if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 3)
        } else {
            LinearLayoutManager(this)
        }

        val listTourAdapter = ListTourAdapter(list)
        rvTour.adapter = listTourAdapter

        listTourAdapter.setOnItemClickCallBack(object : ListTourAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Tour) {
                showSelectedTour(data)
            }
        })
    }

    private fun showSelectedTour(tour: Tour) {
        Toast.makeText(this, "You choose " + tour.name, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Recycle")
    private fun getListTour(): ArrayList<Tour> {
        val dataName = resources.getStringArray(R.array.data_name)
        val facilities = resources.getStringArray(R.array.data_facilities)
        val highlights = resources.getStringArray(R.array.data_highlight)
        val locations = resources.getStringArray(R.array.data_location)
        val dataDescriptions = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listTour = ArrayList<Tour>()
        for (i in dataName.indices) {
            val tour = Tour(
                name = dataName[i],
                placeName = facilities[i],
                location = locations[i],
                facilities = facilities[i],
                description = dataDescriptions[i],
                highlight = highlights[i],
                photo = dataPhoto.getResourceId(i, -1)
            )
            listTour.add(tour)
        }
        return listTour
    }
}
