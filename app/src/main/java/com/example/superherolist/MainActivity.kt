package com.example.superherolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SuperHeroAdapter()
        recyclerView.adapter = adapter


        val api = ApiClient.client.create(ApiInterface::class.java)
        val superheroes:Single<ListSuperHero> = api.getSuperHeroes()

        superheroes.subscribe(
            { superHeroes ->
                adapter.setData(superHeroes)
            },
            { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        )


    }
}


typealias ListSuperHero= List<SuperHero>

data class SuperHero(val name :String, val slug:String, val images: Images )

data class Images(val xs: String)


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

    interface ApiInterface{
        @GET("/superhero-api/api/all.json")
        fun getSuperHeroes(): Single<ListSuperHero>
    }