package retrofit;
import android.util.Log;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import services.GitHubServices;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder().baseUrl("https://api.github.com")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public GitHubServices getUser(){
        return retrofit.create(GitHubServices.class);
    }

}
