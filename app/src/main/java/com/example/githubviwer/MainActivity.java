package com.example.githubviwer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import model.User;
import retrofit.RetrofitInitializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<User> userList = new ArrayList<>();
    Button btnPesquisar;
    AutoCompleteTextView edtUsuario;
    Intent i;

    private static final String[] EXEMPLOS = new String[]{"LeonardoSteinke", "Gusjfy", "Facebook", "Moodle", "Android"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        btnPesquisar.setOnClickListener(v -> {

            String userArray[] = edtUsuario.getText().toString().replaceAll(" ", "").split(",");
            int[] count = {0};
            for (String user : userArray) {
                Call<User> call = new RetrofitInitializer().getUser().select(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        count[0]++;
                        userList.add(response.body());
                        Gson gson = new Gson();
                        String UserJson = gson.toJson(userList);
                        if (userArray.length == count[0]) {
                            i = new Intent(MainActivity.this, UserList.class);
                            i.putExtra("userList", UserJson);
                            Log.i("Retrofit", UserJson);
                            edtUsuario.setText("");
                            startActivity(i);
                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("Retrofit", "falha");
                    }
                });
            }

        });
    }

    private void initComponents() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, EXEMPLOS);
        edtUsuario = findViewById(R.id.edtLogin);
        edtUsuario.setAdapter(adapter);
        btnPesquisar = findViewById(R.id.btnPesquisar);
    }
}