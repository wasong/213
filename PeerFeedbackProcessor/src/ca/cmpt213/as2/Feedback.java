package ca.cmpt213.as2;

import java.util.List;

public class Feedback {
    private String confidential_comments;
    private List<Group> group;

    public String getComments() {
        return confidential_comments;
    }

    public List<Group> getGroups() {
        return group;
    }
}
