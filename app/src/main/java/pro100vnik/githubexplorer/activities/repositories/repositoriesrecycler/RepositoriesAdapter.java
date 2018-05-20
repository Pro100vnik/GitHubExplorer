package pro100vnik.githubexplorer.activities.repositories.repositoriesrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pro100vnik.githubexplorer.R;
import pro100vnik.githubexplorer.github.models.GithubRepository;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

    private List<GithubRepository> repositories;

    public RepositoriesAdapter(List<GithubRepository> repositories){
        this.repositories = repositories;
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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepository repo = repositories.get(position);

        TextView title = holder.view.findViewById(R.id.repository_item_title);
        title.setText(repo.getName());
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
