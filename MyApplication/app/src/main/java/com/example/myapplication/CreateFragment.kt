package com.example.myapplication

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.security.auth.callback.Callback

class CreateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_comments_button.setOnClickListener {

            val enqueue: Any = MainActivity.apiService.addComment(
                create_title.editText?.text.toString(),
                create_completed_checkbox.isChecked
            ).enqueue(object : Callback<Comments> {
                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }

                override fun onResponse(call: Call<Comments>, response: Response<Comments>) {
                    Log.e("Response body: ", response.body()!!.toString())
                    val comment = response.body()!!
                    MainActivity.comments.add(comment)
                    CommentListAdapter.instance.notifyDataSetChanged()
                }
            })

            activity?.onBackPressed()
        }
    }
}