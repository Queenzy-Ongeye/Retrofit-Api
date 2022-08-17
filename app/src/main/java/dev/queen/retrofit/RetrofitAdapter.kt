package dev.queen.retrofit

import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import dev.queen.retrofit.databinding.RetrofitListPostsBinding

class RetrofitAdapter(
    var postList: List<Post>
) : RecyclerView.Adapter<RetrofitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        var bindingView =
            RetrofitListPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetrofitViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
        var currentItem = postList.get(position)
        val context = holder.itemView.context
        var progressBar: ProgressBar? = null
        var i = 0
        val handler = Handler()

//        var currentComment = commentList[position]

        with(holder.bindingView) {
            postId.text = currentItem.id.toString()
            userId.text = currentItem.userId.toString()
            tvTittle.text = currentItem.title
            tvBody.text = currentItem.body

            cardPosts.setOnClickListener {
                var intent = Intent(context, ViewComments::class.java)
                intent.putExtra("POST_ID", currentItem.id)
                context.startActivity(intent)
                if (progressBar != null) {
                    progressBar.visibility = View.VISIBLE
                }

                Thread(Runnable {
                    while (i < 100) {
                        i+=100
                        handler.post(Runnable {
                            if (progressBar != null) {
                                progressBar.progress = i
                            }
                        })
                        try {
                            Thread.sleep(100)
                        }catch (e:InterruptedException){
                            e.printStackTrace()
                        }
                    }
                    if (progressBar != null) {
                        progressBar.visibility = View.INVISIBLE
                    }
                }).start()


            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size

    }
}

class RetrofitViewHolder(var bindingView: RetrofitListPostsBinding) :
    RecyclerView.ViewHolder(bindingView.root) {

}