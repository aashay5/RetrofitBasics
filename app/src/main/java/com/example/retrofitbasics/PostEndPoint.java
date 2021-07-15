package com.example.retrofitbasics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostEndPoint {
    @GET("posts")
    Call<List<Post>> getPosts();


    @GET("posts/{id}")
    Call<Post> getSinglePost(@Path("id") int id);

    @GET("posts")
    Call<List<Post>> getPostByUserId(@Query("userId") int id);
}
