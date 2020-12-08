package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ToDoListFragment
import com.example.myapplication.ToDoListFragment
import com.example.myapplication.Comments
import kotlinx.android.synthetic.main.fragment_create.*
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListAdapter(
    val context: Context,
) : RecyclerView.Adapter<CommentListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.singlecomment_view, parent, false)
        instance = this
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val comment = MainActivity.comments[position]
        holder.titleText.text = comment.title
        holder.completedCheckBox.isChecked = comment.completed


        holder.itemView.setOnClickListener {
            val bundle = bundleOf("commentId" to comment.id, "userId" to comment.userId)
            it.findNavController().navigate(R.id.action_toDoListFragment_to_detailFragment, bundle)
        }

        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.single_completed_checkbox)
        checkBox.setOnClickListener {
            MainActivity.apiService.updateComment(
                comment.id,
                checkBox.isChecked
            ).enqueue(object : Callback<Comments> {
                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }

                override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                    Log.e("Response body: ", response.body()!!.toString())
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return MainActivity.comments.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.findViewById(R.id.single_title)
        var completedCheckBox: CheckBox = itemView.findViewById(R.id.single_completed_checkbox)
    }

    companion object {
        lateinit var instance: CommentListAdapter
    }
}