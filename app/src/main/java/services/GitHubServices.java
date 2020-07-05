package services;

import model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubServices {

    @GET("users/{id}")
    Call<User> select(@Path("id") String login);

}
