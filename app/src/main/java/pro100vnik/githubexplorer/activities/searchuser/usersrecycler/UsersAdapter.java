package pro100vnik.githubexplorer.activities.searchuser.usersrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.github.models.GithubUser;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<GithubUser> users;
    private UserClickedListener clickListener;

    public UsersAdapter(List<GithubUser> users, UserClickedListener listener){
        this.users = users;
        this.clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public ViewHolder(View view){
            super(view);
            this.view = view;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GithubUser user = users.get(position);

        TextView username = holder.view.findViewById(R.id.user_item_username);
        TextView repositories = holder.view.findViewById(R.id.user_item_reposCount);

        username.setText(user.getUsername());
        repositories.setText("PUBLIC REPOSITORIES: " + String.valueOf(user.getPublic_repo_count()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onUserClicked(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
