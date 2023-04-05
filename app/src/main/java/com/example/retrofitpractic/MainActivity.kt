package com.example.retrofitpractic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitpractic.databinding.ActivityMainBinding
import com.example.retrofitpractic.retrofit.AuthRequest
import com.example.retrofitpractic.retrofit.MainApi
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(MainApi::class.java)

        binding.bt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //      val product = api.getProductById(3)
                val user = api.auth(
                    AuthRequest(
                        binding.username.text.toString(),
                        binding.password.text.toString()

                    )
                )
                runOnUiThread {
                    //  binding.firstName.text = product.description
                    binding.apply {
                        Picasso.get().load(user.image).into(iv)
                        firstName.text=user.firstName
                        lastName.text=user.lastName
                    }

                }

            }
        }
    }
}