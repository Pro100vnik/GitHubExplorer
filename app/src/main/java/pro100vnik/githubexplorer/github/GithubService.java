package pro100vnik.githubexplorer.github;

import android.telecom.Call;

import pro100vnik.githubexplorer.github.models.GithubRepository;
import pro100vnik.githubexplorer.github.models.GithubUsers;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Single;

//https://api.github.com/users/{username}/repos
//https://api.github.com/legacy/user/search/{keyword}
//?page=2&per_page=100

public interface GithubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{username}/repos")
    rx.Observable<GithubRepository[]> getUserRepos(@Path("username") String username, @Query("page") int page, @Query("count") int count);

    @GET("/legacy/user/search/{keyword}")
    rx.Observable<GithubUsers> getUsers(@Path("keyword") String keyword);
}
