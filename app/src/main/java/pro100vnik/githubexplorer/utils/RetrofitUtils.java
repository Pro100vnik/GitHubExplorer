package pro100vnik.githubexplorer.utils;

import retrofit.RestAdapter;

public class RetrofitUtils {
    public static <T> T createService(final Class<T> cls, final String endpoint){
        return new RestAdapter.Builder().setEndpoint(endpoint).build().create(cls);
    }
}
