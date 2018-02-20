package ca.cmpt213.as2;

import java.text.DecimalFormat;
import java.util.List;

public class Feedback {
    private String confidential_comments= null;
    private List<Group> group = null;

    public String getComments() {
        return confidential_comments;
    }

    public List<Group> getGroupList() {
        return group;
    }

    public Group getCreator() {
        return group.get(0);
    }

    public String prettifyData() {
        String printData = "";
        String creator = getCreator().getSfu_email();
        double total = 0;

        String temp = "";
        for (Group student : group) {
            temp = "";
            String score = student.getContribution().getScore();
            String comment = FileHandler.findBetween("(.*)\n", student.getContribution().getComment());

            String endString = creator
                    + ","
                    + score
                    + ","
                    + comment
                    + "\n";

            // handle own score
            // TODO: add comments
            if (student.getSfu_email().equals(creator)){
                temp = ",-->," + endString;
            } else {
                total += Double.parseDouble(student.getContribution().getScore());
                temp = "," + student.getSfu_email() + "," + endString;
            }

            printData = temp.concat(printData);
        }

        String confidentialComment = FileHandler.findBetween("(.*)\n", confidential_comments).replace("\"", "\'");
        String average = new DecimalFormat("##.#").format(total / (group.size() - 1));
        // get average
        String avgStr = ",-->,"
                + creator
                + ","
                + "avg " + average + "/" + Integer.toString(group.size())
                + ",,,"
                + confidentialComment
                + "\n";

        printData = printData.concat(avgStr);

        return printData;
    }

    public Boolean hasValidData() {
        Boolean valid = true;
        double scoreSum = 0;
        for (Group student : group) {
            scoreSum += Double.parseDouble(student.getContribution().getScore());

            if (!student.hasValidData()) {
                valid = false;
                break;
            }
        }

        if (!(Math.abs(scoreSum - (20 * group.size())) < 0.1)) {
            valid = false;
            ErrorHandler.error("Incorrect score sums");
        }

        return valid;
    }
}
