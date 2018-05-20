package pro100vnik.githubexplorer.activities.searchuser.usersrecycler;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.R2;
import pro100vnik.githubexplorer.activities.repositories.RepositoriesActivity;
import pro100vnik.githubexplorer.github.models.GithubUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersRecyclerFragment extends Fragment implements UserClickedListener {

    public static final String TAG = "USERS_RECYCLER_FRAGMENT";

    @BindView(R2.id.usersRecyclerView)
    RecyclerView usersRecyclerView;
    private Unbinder unbinder;

    private List<GithubUser> users;

    protected void setUsers(List<GithubUser> users) {
        this.users = users;
    }

    public UsersRecyclerFragment() {
        // Required empty public constructor
    }

    public static UsersRecyclerFragment getInstance(List<GithubUser> users){
        UsersRecyclerFragment fragment = new UsersRecyclerFragment();
        fragment.setUsers(users);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_users_recycler, container, false);
        unbinder = ButterKnife.bind(this, view);

        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        usersRecyclerView.setAdapter(new UsersAdapter(users, this));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onUserClicked(GithubUser user) {
        Intent intent = new Intent(getActivity().getApplicationContext(), RepositoriesActivity.class);
        intent.putExtra(GithubUser.USERNAME, user.getUsername());
        intent.putExtra(GithubUser.FULLNAME, user.getFullname());
        intent.putExtra(GithubUser.REPOS, user.getRepos());
        intent.putExtra(GithubUser.PUBLIC_REPOS, user.getPublic_repo_count());
        startActivity(intent);
    }
}
