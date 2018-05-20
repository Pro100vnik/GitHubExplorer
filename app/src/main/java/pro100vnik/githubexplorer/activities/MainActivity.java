package pro100vnik.githubexplorer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.activities.searchuser.SearchUserActivity;
import pro100vnik.githubexplorer.github.GithubService;
import pro100vnik.githubexplorer.github.models.GithubRepository;
import pro100vnik.githubexplorer.github.models.GithubUsers;
import pro100vnik.githubexplorer.utils.RetrofitUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), SearchUserActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
