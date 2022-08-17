package dev.queen.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.queen.retrofit.databinding.CommentItemListBinding

class CommentAdapter(var commentList: List<Comments>):RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var binding = CommentItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComment = commentList[position]

        with(holder.binding){
            tvname.text = currentComment.name
            tvEmail.text = currentComment.email
            tvBoddy.text = currentComment.body
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}
class CommentViewHolder(var binding: CommentItemListBinding): RecyclerView.ViewHolder(binding.root)