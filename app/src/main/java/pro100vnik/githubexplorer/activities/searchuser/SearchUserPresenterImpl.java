package pro100vnik.githubexplorer.activities.searchuser;

import java.util.concurrent.TimeUnit;

import pro100vnik.githubexplorer.github.GithubService;
import pro100vnik.githubexplorer.github.models.GithubUsers;
import pro100vnik.githubexplorer.utils.RetrofitUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchUserPresenterImpl implements SearchUserContract.SearchUserPresenter {

    private SearchUserContract.SearchUserInteractor interactor;

    public SearchUserPresenterImpl(SearchUserContract.SearchUserInteractor interactor){
        this.interactor = interactor;
        this.interactor.showNoResults();
    }

    @Override
    public void searchTextChanged(String searchText) {
        if(searchText.length() >= 3){
            sendRequest(searchText);
        }
        else {
            interactor.showNoResults();
        }
    }

    private void sendRequest(String searchText){
        GithubService service = RetrofitUtils.createService(GithubService.class, GithubService.ENDPOINT);
        service.getUsers(searchText)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GithubUsers>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        interactor.showError();
                    }

                    @Override
                    public void onNext(GithubUsers githubUsers) {
                        interactor.userListChanged(githubUsers.getUsers());
                    }
                });
    }
}
