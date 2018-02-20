package ca.cmpt213.as2;

import java.util.ArrayList;
import java.util.List;

public class StudentGroup {
    private String groupID = "";
    private List<Feedback> feedbacks = new ArrayList<>();

    private void setGroupID(String groupID) {
        if (!this.groupID.equals(groupID)) this.groupID = groupID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void addFeedback(Feedback feedback) {
        String studentGroup = feedback.getCreator().getGroupID();
        if (feedbacks.size() == 0) {
            setGroupID(studentGroup);
            feedbacks.add(feedback);
        } else if (groupID.equals(studentGroup) && !feedbacks.contains(feedback)) {
            feedbacks.add(feedback);
        }
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
}
