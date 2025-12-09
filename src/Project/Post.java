package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Post class represents a social media post, containing content, an author, and a list of comments.
 * It provides methods to manage the post and its associated comments, and overrides key methods like
 * display, toString, equals, and hashCode for proper functionality.
 * 
 * @author Hessa
 */
public class Post implements SocialMediaElement {
    private String Content;
    private User Author;
    private List<Comment> Comments;
    
    /**
     * Default constructor for the Post class.
     * Initializes the list of comments.
     */
    public Post() {
        this.Comments = new ArrayList<>();
    }
    
    /**
     * Constructor with content and author parameters.
     * 
     * @param Content the content of the post
     * @param Author the user who created the post
     */
    public Post(String Content, User Author) {
        this();
        this.Content = Content;
        this.Author = Author;
    }
    
    /**
     * Gets the content of the post.
     * 
     * @return the content of the post
     */
    public String getContent() {
        return Content;
    }
    
    /**
     * Sets the content of the post.
     * 
     * @param Content the content to be set
     */
    public void setContent(String Content) {
        this.Content = Content;
    }
    
    /**
     * Gets the author of the post.
     * 
     * @return the user who created the post
     */
    public User getAuthor() {
        return Author;
    }
    
    /**
     * Sets the author of the post.
     * 
     * @param Author the user to be set as the author
     */
    public void setAuthor(User Author) {
        this.Author = Author;
    }
    
    /**
     * Gets the list of comments on the post.
     * 
     * @return the list of comments
     */
    public List<Comment> getComments() {
        return Comments;
    }
    
    /**
     * Sets the list of comments on the post.
     * 
     * @param Comments the list of comments to be set
     */
    public void setComments(List<Comment> Comments) {
        this.Comments = Comments;
    }   
    
    /**
     * Adds a comment to the post.
     * 
     * @param comment the comment to be added
     * @throws SocialMediaException if the comment is null
     */
    public void addComment(Comment comment) throws SocialMediaException {
        if (comment == null) {
            throw new SocialMediaException("Cannot add empty comment.");
        }
        Comments.add(comment);
    }
    
    /**
     * Displays the post content and the author's name.
     */
    @Override 
    public void display() {
        System.out.println("Posted by " + Author.getUserName() + ": " + Content);
    }
    
    /**
     * Gets the type of the social media element.
     * 
     * @return the type of the element, which is "Post"
     */
    @Override 
    public String getType() {
        return "Post";
    }   
    
    /**
     * Provides a string representation of the post.
     * 
     * @return a short preview of the post content, along with the author and the number of comments
     */
    @Override 
    public String toString() {
        String result = "Posted by " + Author.getUserName() + ": " + Content.substring(0, Math.min(Content.length(), 150));
    
        if (Comments.isEmpty()) {
            result += " [No comments]";
        } else {
            result += Comments;
        }
    
        return result;
}
    
    /**
     * Checks if two posts are equal based on their content and author.
     * 
     * @param o the object to compare with
     * @return true if both posts have the same content and author, false otherwise
     */
    @Override 
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null|| getClass()!=o.getClass())
            return false;
        Post post = (Post)o;
        return Objects.equals(Content, post.Content) && Objects.equals(Author, post.Author);
    }
    
    /**
     * Generates a hash code for the post based on its content and author.
     * 
     * @return the hash code of the post
     */
    @Override 
    public int hashCode() {
        return Objects.hash(Content, Author);
    }

}
