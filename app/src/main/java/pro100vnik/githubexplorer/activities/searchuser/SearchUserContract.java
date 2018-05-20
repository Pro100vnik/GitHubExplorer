package pro100vnik.githubexplorer.activities.searchuser;

import java.util.List;

import pro100vnik.githubexplorer.github.models.GithubUser;

public interface SearchUserContract {

    interface SearchUserPresenter {
        void searchTextChanged(String searchText);
    }

    interface SearchUserInteractor{
        void userListChanged(List<GithubUser> users);
        void showError();
        void showNoResults();
    }
}
