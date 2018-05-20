package pro100vnik.githubexplorer.activities.searchuser.usersrecycler;

import pro100vnik.githubexplorer.github.models.GithubUser;

public interface UserClickedListener {
    void onUserClicked(GithubUser user);
}
