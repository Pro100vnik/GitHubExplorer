package pro100vnik.githubexplorer.activities.repositories;

import java.util.List;

import pro100vnik.githubexplorer.github.models.GithubRepository;

public interface RepositoriesContract {
    interface RepositoriesPresenter{
        void getPage(int page);
    }

    interface RepositoriesInteractor{
        void showRepos(List<GithubRepository> repos, int previousPage, boolean previousVisible, int nextPage, boolean nextVisible);
        void showError();
    }
}
