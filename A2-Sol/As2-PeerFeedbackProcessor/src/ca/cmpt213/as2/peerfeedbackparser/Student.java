package ca.cmpt213.as2.peerfeedbackparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Data read in from a single student comment file.
 */
public class Student {
    private File sourceFile;
    private String email;
    private String confidential_comments;
    private List<CommentOnTeammate> commentsOnTeammate = new ArrayList<>();


    // Inner class to store data from the feedback
    private class CommentOnTeammate {
        private String name;
        private String email;
        private double score;
        private String comment;

        public CommentOnTeammate(String name, String email, double score, String comment) {
            this.name = name;
            this.email = email.toLowerCase().trim();
            this.score = score;
            this.comment = comment;
        }

        public boolean isFor(String studentEmail) {
            return email.equalsIgnoreCase(studentEmail);
        }

        public String getEmail() {
            return email;
        }
        public double getScore() {
            return score;
        }

        public String getComment() {
            return comment.replace('"', '\'');
        }
    }

    public Student(String email, File sourceFile) {
        this.email = email.toLowerCase().trim();
        this.sourceFile = sourceFile;
    }

    public String getEmail() {
        return email;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public double getScoreForStudent(String email) {
        for (CommentOnTeammate comment : commentsOnTeammate) {
            if (comment.isFor(email)) {
                return comment.getScore();
            }
        }
        assert false;
        return -1;
    }

    public String getCommentForStudent(String email) {
        for (CommentOnTeammate comment : commentsOnTeammate) {
            if (comment.isFor(email)) {
                return comment.getComment();
            }
        }
        assert false;
        return "student not found";
    }

    public boolean hasNegativeScore() {
        for (CommentOnTeammate comment : commentsOnTeammate) {
            if (comment.getScore() < 0) {
                return true;
            }
        }
        return false;
    }

    public double sumFeedbackScoresTheyGave() {
        double sum = 0;
        for (CommentOnTeammate comment : commentsOnTeammate) {
            sum += comment.getScore();
        }
        return sum;
    }

    public double getNumberFeedbacks() {
        return commentsOnTeammate.size();
    }

    public boolean matchesEmail(String email) {
        return this.email.equalsIgnoreCase(email);
    }

    public boolean hasCommentOnEmail(String email) {
        for (CommentOnTeammate comment : commentsOnTeammate) {
            if (comment.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public String getConfidentialComments() {
        return confidential_comments.replace('"', '\'');
    }

    public void setConfidential_comments(String confidential_comments) {
        this.confidential_comments = confidential_comments;
    }

    public void addCommentOnTeammate(String studentName, String userId, double score, String comment) {
        CommentOnTeammate commentOnTeammate = new CommentOnTeammate(studentName, userId, score, comment);
        commentsOnTeammate.add(commentOnTeammate);

        // Maintain sorted order
        commentsOnTeammate.sort(new Comparator<CommentOnTeammate>() {
            @Override
            public int compare(CommentOnTeammate o1, CommentOnTeammate o2) {
                return o1.getEmail().compareToIgnoreCase(o2.getEmail());
            }
        });
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "email='" + email + '\'' +
                ", confidential_comments='" + confidential_comments + '\'' +
                ", commentsOnTeammate=" + commentsOnTeammate +
                '}';
    }
}
