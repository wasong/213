package ca.cmpt213.as2;

public class Group {
    private String name = null;
    private String sfu_email = null;
    private Contribution contribution = null;

    public String getName() {
        return name;
    }

    public String getSfu_email() {
        return sfu_email;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public String getGroupID() {
        return FileHandler.findBetween("-t(.*)-s", this.sfu_email);
    }

    public String getStudentNumber(String email) {
        return email.substring(email.length() - 1, email.length());
    }

    public Boolean hasValidData() {
        return (name != null && sfu_email != null && contribution.hasValidData());
    }
}
