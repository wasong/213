package ca.coursePlanner.model;

public class About {
    private String authorName;
    private String appName;

    public About(String authorName, String appName) {
        this.authorName = authorName;
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
