package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.ApiService
import com.example.myapplication.Comments
import com.example.myapplication.User
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commentId = arguments?.getInt("commentId")!!
        val userId = arguments?.getInt("userId")!!

        MainActivity.apiService.getCommentById(commentId).enqueue(object : Callback<Comments> {
            override fun onFailure(call: Call<Comments>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                Log.e("Response body: ", response.body()!!.toString())
                val comment = response.body()!!
               val detail_title.text = comment.title
                val detail_completed_checkbox.isChecked = comment.completed

            }
        })

        MainActivity.apiService.getUser(userId).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("error", t.message.toString())
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.e("Response body: ", response.body()!!.toString())
                val user = response.body()!!
                val detail_user_email.text = user.email
                val detail_user_name.text = user.name
                val detail_user_phone.text = user.phone
                val detail_user_website.text = user.website
            }
        })

    }
}