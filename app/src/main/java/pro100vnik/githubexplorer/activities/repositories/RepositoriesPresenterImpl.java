package pro100vnik.githubexplorer.activities.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pro100vnik.githubexplorer.github.GithubService;
import pro100vnik.githubexplorer.github.models.GithubRepository;
import pro100vnik.githubexplorer.github.models.GithubUser;
import pro100vnik.githubexplorer.utils.RetrofitUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoriesPresenterImpl implements RepositoriesContract.RepositoriesPresenter {

    private RepositoriesContract.RepositoriesInteractor interactor;

    private GithubUser user;
    private int pages;
    private final int perPageCount = 30;
    private final int firstPageNumber = 1;

    public RepositoriesPresenterImpl(RepositoriesContract.RepositoriesInteractor interactor, GithubUser user) {
        this.interactor = interactor;
        this.user = user;

        pages = determinePages(user.getRepos(), perPageCount);
        getPage(firstPageNumber);
    }

    @Override
    public void getPage(final int page) {
        GithubService service = RetrofitUtils.createService(GithubService.class, GithubService.ENDPOINT);
        service.getUserRepos(user.getUsername(), page, perPageCount)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GithubRepository[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        interactor.showError();
                    }

                    @Override
                    public void onNext(GithubRepository[] githubRepositories) {
                        List<GithubRepository> repos = new ArrayList<>();
                        for(GithubRepository repo : githubRepositories){
                            repos.add(repo);
                        }

                        int previousPage = page - 1;
                        int nextPage = page + 1;
                        boolean previousVisible = true;
                        boolean nextVisible = true;

                        if(previousPage < firstPageNumber){
                            previousVisible = false;
                        }

                        if(nextPage > pages){
                            nextVisible = false;
                        }

                        interactor.showRepos(repos, previousPage, previousVisible, nextPage, nextVisible);
                    }
                });
    }

    private int determinePages(int repos, int perPage){
        return (int) Math.ceil((double)repos / (double)perPage);
    }
}
