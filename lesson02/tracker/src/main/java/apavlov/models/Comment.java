package apavlov.models;

import java.text.DateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * The class Comment - wrapper for Item.
 *
 * @author Pavlov Artem
 * @since 21.07.2017
 */
public class Comment {
    /**
     * The comporator for sort comment to date and time.
     */
    private Comparator<Comment> sortDate = new Comparator<Comment>() {
        @Override
        public int compare(Comment commentFirst, Comment commentSecond) {
            return commentFirst.getDateAndTimeCreate().compareTo(commentSecond.getDateAndTimeCreate());
        }
    };

    /**
     * The var for get date and time comment.
     */
    private String dateAndTimeCreate;
    /**
     * The var for text comment.
     */
    private String comment;

    /**
     * The getter for corporator`s sort to date.
     *
     * @return link to corporator;
     */
    public Comparator<Comment> getSortDate() {
        return sortDate;
    }

    /**
     * The getter for return date and time.
     *
     * @return return date and time to string;
     */
    public String getDateAndTimeCreate() {
        return dateAndTimeCreate;
    }

    /**
     * The getter for get comment.
     *
     * @return return comment;
     */
    public String getComment() {
        return comment;
    }

    /**
     * The setter for var comment.
     *
     * @param comment - text comment;
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * The constructor for class Comment.
     *
     * @param comment - text comment`s;
     */
    public Comment(String comment) {
        this.dateAndTimeCreate = parseDateAndTime();
        this.comment = comment;
    }

    /**
     * The method for parse format date to string.
     *
     * @return return date and time to string;
     */
    private String parseDateAndTime() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT,
                new Locale("ru", "RU"));
        return dateFormat.format(new Date());
    }
}
