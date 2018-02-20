package ca.cmpt213.as2;

public class Contribution {
    private String score = null;
    private String comment = null;

    public String getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Boolean hasValidData() {
        return (score != null) && (comment != null);
    }
}
