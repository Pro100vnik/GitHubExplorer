package pro100vnik.githubexplorer.activities.searchuser.error;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pro100vnik.githubexplorer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersErrorFragment extends Fragment {

    public static final String TAG = "USERS_ERROR_FRAGMENT";

    public UsersErrorFragment() {
        // Required empty public constructor
    }

    public static UsersErrorFragment getInstance(){
        return new UsersErrorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_error, container, false);
    }
}
