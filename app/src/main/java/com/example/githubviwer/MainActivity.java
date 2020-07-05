package com.example.githubviwer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.User;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<User> userList = new ArrayList<>();
    EditText edtUsuario;
    Button btnPesquisar;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPesquisar = findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(v -> {

            edtUsuario = findViewById(R.id.edtLogin);
            String userArray[] = edtUsuario.getText().toString().trim().replaceAll("\\s+", "").split(",");
            for (String user : userArray) {
                Call<User> call = new RetrofitInitializer().getUser().select(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userList.add(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("Retrofit", "falha");
                    }
                });
            }
            Intent i = new Intent(MainActivity.this, UserList.class);

            Gson gson = new Gson();

            String UserJson = gson.toJson(userList);

            if (count != 0) {
                i.putExtra("userList", UserJson);
                Log.i("Retrofit", UserJson);
                startActivity(i);

            } else {
                count++;
            }
        });
    }
}