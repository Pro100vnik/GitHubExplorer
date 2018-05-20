package pro100vnik.githubexplorer.activities.repositories;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.R2;
import pro100vnik.githubexplorer.activities.repositories.repositoriesrecycler.RepositoriesAdapter;
import pro100vnik.githubexplorer.github.models.GithubRepository;
import pro100vnik.githubexplorer.github.models.GithubUser;

public class RepositoriesActivity extends Activity implements RepositoriesContract.RepositoriesInteractor {

    @BindView(R2.id.repositoriesUsername)
    TextView usernameTextView;
    @BindView(R2.id.repositoriesPreviousButton)
    Button previousButton;
    @BindView(R2.id.repositoriesNextButton)
    Button nextButton;
    @BindView(R2.id.repositoriesRecycler)
    RecyclerView repositoriesRecycler;
    private Unbinder unbinder;

    private RepositoriesPresenterImpl presenter;
    private int previousPage;
    private int nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        unbinder = ButterKnife.bind(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                GithubUser user = new GithubUser(extras.getString(GithubUser.USERNAME), extras.getString(GithubUser.FULLNAME), extras.getInt(GithubUser.REPOS), extras.getInt(GithubUser.PUBLIC_REPOS));
                presenter = new RepositoriesPresenterImpl(this, user);

                usernameTextView.setText(user.getUsername() + "'s repositories");

                repositoriesRecycler.setLayoutManager(new LinearLayoutManager(this));
            }
        }
        else {
            showError();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showRepos(List<GithubRepository> repos, int previousPage, boolean previousVisible, int nextPage, boolean nextVisible) {
        if (previousVisible) {
            previousButton.setVisibility(View.VISIBLE);
            this.previousPage = previousPage;
        } else {
            previousButton.setVisibility(View.GONE);
        }

        if(nextVisible){
            nextButton.setVisibility(View.VISIBLE);
            this.nextPage = nextPage;
        }
        else {
            nextButton.setVisibility(View.GONE);
        }

        repositoriesRecycler.setAdapter(new RepositoriesAdapter(repos));
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R2.id.repositoriesPreviousButton)
    public void previousClicked(){
        presenter.getPage(previousPage);
    }

    @OnClick(R2.id.repositoriesNextButton)
    public void nextClicked(){
        presenter.getPage(nextPage);
    }
}
