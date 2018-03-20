package apavlov.models;

import apavlov.util.NameConstant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class Comment - wrapper for Item.
 *
 * @author Pavlov Artem
 * @since 20.03.2018
 */
public class Comment {
    /**
     * The var - date create comment or update.
     */
    private Timestamp date;

    /**
     * The var - text comment`s.
     */
    private String comment;

    /**
     * The getter for var date.
     *
     * @return date;
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * The getter for var comment.
     *
     * @return text comment`s;
     */
    public String getComment() {
        return comment;
    }

    /**
     * The constructor for class Comment.
     *
     * @param comment - text comment`s;
     */
    public Comment(String comment) {
        this(new Timestamp(System.currentTimeMillis()), comment);
    }

    /**
     * The constructor for class Comment.
     *
     * @param date - date create or update comment;
     * @param comment - text comment`s;
     */
    public Comment(Timestamp date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    /**
     * The method parse type Timestamp to String.
     *
     * @return date type string;
     */
    public String getStringDate() {
        return new SimpleDateFormat(NameConstant.FORMAT_DATE.toString()).format(new Date(this.date.getTime()));
    }
}
