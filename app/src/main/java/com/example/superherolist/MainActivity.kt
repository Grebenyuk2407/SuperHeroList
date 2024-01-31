package com.example.superherolist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
        val superheroes: Single<SuperHero> = api.getSuperHeroes()

        superheroes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { superHeroes ->
                    myAdapter.setData(superHeroes)
                },
                { error ->
                    Log.e("MainActivity", "Error: ${error.message}", error)
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            )
    }
}

class ApiClient {
    companion object {
        val client: Retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://akabab.github.io")
            .build()
    }
}

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    fun getSuperHeroes(): Single<SuperHero>
}
