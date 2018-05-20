package pro100vnik.githubexplorer.activities.searchuser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.R2;
import pro100vnik.githubexplorer.activities.searchuser.error.UsersErrorFragment;
import pro100vnik.githubexplorer.activities.searchuser.noresults.UsersNoResultsFragment;
import pro100vnik.githubexplorer.activities.searchuser.usersrecycler.UsersAdapter;
import pro100vnik.githubexplorer.activities.searchuser.usersrecycler.UsersRecyclerFragment;
import pro100vnik.githubexplorer.github.models.GithubUser;

public class SearchUserActivity extends Activity implements SearchUserContract.SearchUserInteractor {

    @BindView(R2.id.searchEditText)
    EditText searchEditText;
    @BindView(R2.id.usersContentLayout)
    RelativeLayout usersContentLayout;
    private Unbinder unbinder;

    private SearchUserPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        unbinder = ButterKnife.bind(this);

        presenter = new SearchUserPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnTextChanged(R2.id.searchEditText)
    public void searchTextChanged(){
        presenter.searchTextChanged(searchEditText.getText().toString());
    }

    @Override
    public void userListChanged(List<GithubUser> users) {
        UsersRecyclerFragment fragment = UsersRecyclerFragment.getInstance(users);
        replaceFragment(fragment, UsersRecyclerFragment.TAG);
    }

    @Override
    public void showError() {
        UsersErrorFragment fragment = UsersErrorFragment.getInstance();
        replaceFragment(fragment, UsersErrorFragment.TAG);
    }

    @Override
    public void showNoResults() {
        UsersNoResultsFragment fragment = UsersNoResultsFragment.getInstance();
        replaceFragment(fragment, UsersNoResultsFragment.TAG);
    }
    private void replaceFragment(Fragment fragment, String tag){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(usersContentLayout.getId(), fragment, tag);
        fragmentTransaction.commit();
    }
}
