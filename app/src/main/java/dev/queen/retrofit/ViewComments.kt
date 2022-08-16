package dev.queen.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import dev.queen.retrofit.databinding.ActivityViewCommentsBinding

class ViewComments : AppCompatActivity() {
    lateinit var bindingViewComments: ActivityViewCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingViewComments = ActivityViewCommentsBinding.inflate(layoutInflater)
        setContentView(bindingViewComments.root)
        viewComments()
    }

    fun viewComments(){
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        var extras = intent

        var name = extras.getStringExtra("NAME")
        var email = extras.getStringExtra("EMAIL")
        var body = extras.getStringExtra("BODY")
        var postId = extras.getStringExtra("POST")
        var id = extras.getStringExtra("ID")

        actionBar.setTitle(name)

        bindingViewComments.tvName.text = name
        bindingViewComments.tvEmail.text = email
        bindingViewComments.tvBdy.text = body
        bindingViewComments.pstId.text = postId
        bindingViewComments.commId.text = id

    }
}