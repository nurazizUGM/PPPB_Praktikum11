package com.example.praktikum11

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikum11.databinding.ActivityMainBinding
import com.example.praktikum11.model.Authors
import com.example.praktikum11.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val client = ApiClient.getInstance()
        val response = client.getAuthors()
        val authorList = ArrayList<String>()

        response.enqueue(object : Callback<List<Authors>> {
            override fun onResponse(call: Call<List<Authors>>, response: Response<List<Authors>>) {
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        authorList.add(it.name)
                    }
                    binding.lvNama.adapter = ArrayAdapter(
                        this@MainActivity,
                        android.R.layout.simple_list_item_1,
                        authorList
                    )
                }
            }

            override fun onFailure(call: Call<List<Authors>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}