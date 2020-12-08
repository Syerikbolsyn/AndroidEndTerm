package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.Comments
import com.example.myapplication.R
import com.example.myapplication.CommentListAdapter
import kotlinx.android.synthetic.main.fragment_todolist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todolist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       comments_recycle_view.adapter = CommentListAdapter(view.context)
        comments_recycle_view.layoutManager = LinearLayoutManager(view.context)

        MainActivity.apiService.getComments().enqueue(object : Callback<MutableList<Comments>> {
            override fun onFailure(call: Call<MutableList<Comments>>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<MutableList<Comments>>, response: Response<MutableList<Comments>>) {
                Log.e("Response size: ", response.body()!!.size.toString())
                MainActivity.comments = response.body()!!
                (comments_recycle_view.adapter as CommentListAdapter).notifyDataSetChanged()
            }
        })

        start_creating_button.setOnClickListener {
            val action = ToDoListFragment.actionToDoListFragmentToCreateFragment()
            it.findNavController().navigate(action)
        }
    }
}