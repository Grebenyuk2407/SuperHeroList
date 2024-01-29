package com.example.superherolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.recycler_view)
        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getSuperHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()){
                    val items = it
                    val myAdapter = RecyclerViewAdapter(items as MutableList<SuperHero>)

                    recyclerView.adapter = myAdapter
                }
            },{

                Toast.makeText(this,"Error ${it.message}", Toast.LENGTH_SHORT).show()

            })
        

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
            .baseUrl("https://akabab.github.io")
            .build()
    }

}

    interface ApiInterface{
        @GET("/superhero-api/api/all.json")
        fun getSuperHeroes(): Single<ListSuperHero>
    }