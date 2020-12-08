package com.example.myapplication

import android.net.MailTo
import com.google.gson.annotations.SerializedName

data class Comments {
    data class Task(
        @SerializedName("postId")
        val postId: Int,
        @SerializedName("name")
        val name : String ,
        @SerializedName("email")
        val email: MailTo,
        @SerializedName("body")
        val body: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("completed")
        val completed: Boolean
    )
}