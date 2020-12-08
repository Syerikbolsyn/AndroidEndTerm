package com.example.myapplication
import com.example.myapplication.Comments
import com.example.myapplication.User
import retrofit2.Call
import retrofit2.http.*

public interface ApiService {
    @GET("todos/")
    fun getComments(): Call<MutableList<Comments>>

    @GET("todos/{id}/")
    fun getCommentById(@Path("id") todoId: Int): Call<Task>

    @FormUrlEncoded
    @POST("todos")
    fun addComment(
        @Field("title") title: String,
        @Field("completed") completed: Boolean
    ): Call<Comments>

    @FormUrlEncoded
    @PUT("todos/{id}/")
    fun updateComment(@Path("id") taskId: Int, @Field("completed") completed: Boolean): Call<Comments>

    @GET("users/{id}/")
    fun getUser(@Path("id") userId: Int): Call<User>


}
