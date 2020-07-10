package com.example.githubviwer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import model.User;

public class UserList extends AppCompatActivity {

    RecyclerView mRecyclerView;

    List<User> userList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);

        getSupportActionBar().setTitle("Lista de Usuários");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Gson gson = new Gson();
        Intent i = getIntent();
        Type type = new TypeToken<List<User>>(){}.getType();
        userList = gson.fromJson(i.getStringExtra("userList"), type);
        mRecyclerView = findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(this, userList);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
