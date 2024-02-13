package com.example.superherolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val myAdapter = SuperHeroAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

        val api = ApiClient.client.create(ApiInterface::class.java)

        lifecycleScope.launch {
            try {
                val superHeroes = withContext(Dispatchers.IO){
                    api.getSuperHeroes()
                }
                myAdapter.setData(superHeroes)

            }catch (error:Throwable){
                Log.e("MainActivity", "Error: ${error.message}", error)
                Toast.makeText(this@MainActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

class ApiClient {
    companion object {
        val client: Retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://akabab.github.io")
            .build()
    }
}

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    suspend fun getSuperHeroes(): SuperHero
}
