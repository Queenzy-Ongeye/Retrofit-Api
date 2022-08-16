package dev.queen.retrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.queen.retrofit.databinding.RetrofitListPostsBinding

class RetrofitAdapter(var context: Context, var postList: List<Post>
) : RecyclerView.Adapter<RetrofitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        var bindingView = RetrofitListPostsBinding.inflate(LayoutInflater.from(context), parent, false)
        return RetrofitViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
        var currentItem = postList.get(position)
        val context = holder.itemView.context

//        var currentComment = commentList[position]

        with(holder.bindingView) {
            postId.text = currentItem.id.toString()
            userId.text = currentItem.userId.toString()
            tvTittle.text = currentItem.title
            tvBody.text = currentItem.body

//            cardPosts.setOnClickListener {
//                var intent = Intent(context , ViewComments:: class.java)
//                intent.putExtra("POST", currentComment.postId)
//                intent.putExtra("ID", currentComment.id)
//                intent.putExtra("NAME", currentComment.name)
//                intent.putExtra("EMAIL", currentComment.email)
//                intent.putExtra("BODY", currentComment.commbody)
//            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size

    }
}

class RetrofitViewHolder(var bindingView: RetrofitListPostsBinding) :
    RecyclerView.ViewHolder(bindingView.root) {

}