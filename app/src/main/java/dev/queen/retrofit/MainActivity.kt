package dev.queen.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.queen.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPosts()
    }

    fun getPosts() {

        var service = APIClient.buildApiClient(ApiInterface::class.java)
        var request = service.getPosts()

        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val post = response.body()!!
                if (response.isSuccessful) {
                    Log.d("TAG", post.toString())
                    var adapter = RetrofitAdapter(baseContext, post)
                    binding.rcvRetrofit.adapter = adapter
                    binding.rcvRetrofit.layoutManager = LinearLayoutManager(baseContext)
                    Toast.makeText(baseContext, "fetch ${post.size} posts", Toast.LENGTH_LONG)
                        .show()
                }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

        })
    }

}