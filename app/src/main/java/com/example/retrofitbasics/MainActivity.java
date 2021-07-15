package com.example.retrofitbasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView textView;
private EditText edtEnterId;
private Button btnTransfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        btnTransfer=findViewById(R.id.btnTransfer);
        edtEnterId=findViewById(R.id.edtEnterId);

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAllPosts();
            }
        });
    }
    private void getAllPosts(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        PostEndPoint endPoint=retrofit.create(PostEndPoint.class);
        int id=Integer.valueOf(edtEnterId.getText().toString());
//        Call<List<Post>> call =endPoint.getPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                ArrayList<Post>post=(ArrayList<Post>)response.body();
//                for(Post p:post){
//                    textView.setText(textView.getText() +"\n"+p.getTitle());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });

//        Call<Post>call=endPoint.getSinglePost(id);
//        call.enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                Post post=response.body();
//                textView.setText(post.getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
        Call<List<Post>> call =endPoint.getPostByUserId(id);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                ArrayList<Post> posts=(ArrayList<Post>)response.body();
                for(Post p: posts){
                    textView.setText(textView.getText()+"\n"+p.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}