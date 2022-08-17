package dev.queen.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
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

    }


    fun viewComments() {

//        Renaming the actionbar
//        val actionBar: ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayShowHomeEnabled(true)
        var extras = intent
        postId = extras.getIntExtra("POST_ID", 0)


//        var name = extras.getStringExtra("NAME")
//        var email = extras.getStringExtra("EMAIL")
//        var body = extras.getStringExtra("BODY")
//        actionBar.setTitle(name)
//
//        bindingViewComments.tvName.text = name
//        bindingViewComments.tvEmail.text = email
//        bindingViewComments.tvBdy.text = body
//        bindingViewComments.commId.text = id

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

            }
        })
    }
}



