package dev.queen.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.queen.retrofit.databinding.ActivityViewCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewComments : AppCompatActivity() {
    lateinit var bindingViewComments: ActivityViewCommentsBinding
    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingViewComments = ActivityViewCommentsBinding.inflate(layoutInflater)
        setContentView(bindingViewComments.root)

        viewComments()
        fetchPost()

        bindingViewComments.tvComments.setOnClickListener {
            fetchComments()
        }


    }


    fun viewComments() {
        var extras = intent
        postId = extras.getIntExtra("POST_ID", 0)
    }

    fun fetchPost() {
        var apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)

        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                var post = response.body()
                if (post != null) {
                    bindingViewComments.tvName.text = post.title
                    bindingViewComments.tvBdy.text = post.body

                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }
        })
    }

    fun fetchComments() {
        var apiClient = APIClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getComments(postId)

        request.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
                var comments = response.body()

                if (response.isSuccessful){
                    Log.d("TAG", comments.toString())
                    comments?.let { displayComment(it) }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun displayComment(comment: List<Comments>){
        var adapter = CommentAdapter(comment)
        bindingViewComments.rcvComment.layoutManager = LinearLayoutManager(this)
        bindingViewComments.rcvComment.adapter = adapter
    }

}



