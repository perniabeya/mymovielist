package com.example.mymovielist.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.R
import com.example.mymovielist.adapters.MovieAdapter
import com.example.mymovielist.data.Movie
import com.example.mymovielist.data.MyMovieService
import com.example.mymovielist.databinding.ActivityMainBinding
import com.example.mymovielist.databinding.ItemMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var adapter:MovieAdapter

    var movieList: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = MovieAdapter(movieList){ position ->
            var movie = movieList [position]

            var intent = Intent (this, DetailActivity::class.java)
            intent.putExtra("RECIPE_ID", movie.Title)
            startActivity(intent)

        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager (this)

        searchMovieByName("Matrix")
    }

    fun searchMovieByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = MyMovieService.getRetrofit()
                val result = service.findMovieByName(query)

                movieList = result.Search

                Log.i("API", movieList.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = movieList
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}