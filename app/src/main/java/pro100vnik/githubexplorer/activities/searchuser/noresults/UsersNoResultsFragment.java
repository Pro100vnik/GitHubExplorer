package pro100vnik.githubexplorer.activities.searchuser.noresults;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro100vnik.githubexplorer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersNoResultsFragment extends Fragment {

    public static final String TAG = "USERS_NO_RESULTS_FRAGMENT";

    public UsersNoResultsFragment() {
        // Required empty public constructor
    }

    public static UsersNoResultsFragment getInstance(){
        return new UsersNoResultsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users_no_results, container, false);
    }

}
