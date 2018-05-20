package pro100vnik.githubexplorer.github.models;

import java.io.Serializable;

public class GithubUser implements Serializable {

    private String username;
    private String fullname;
    private int repos;
    private int public_repo_count;

    public static String USERNAME = "GithubUserUsername";
    public static String FULLNAME = "GithubUserFullname";
    public static String REPOS = "GithubUserRepos";
    public static String PUBLIC_REPOS = "GithubUserPublicRepos";

    public GithubUser(String username, String fullname, int repos, int public_repo_count) {
        this.username = username;
        this.fullname = fullname;
        this.repos = repos;
        this.public_repo_count = public_repo_count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRepos() {
        return repos;
    }

    public void setRepos(int respos) {
        this.repos = respos;
    }

    public int getPublic_repo_count() {
        return public_repo_count;
    }

    public void setPublic_repo_count(int public_repo_count) {
        this.public_repo_count = public_repo_count;
    }
}
