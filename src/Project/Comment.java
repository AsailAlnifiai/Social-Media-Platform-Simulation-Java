package Project;

import java.util.Objects;

/**
 * The Comment class represents a comment made by a user on a social media platform. 
 * It contains the content of the comment and the author (user) who made it. 
 * It provides methods to access and modify these attributes and implements methods 
 * from the SocialMediaElement interface.
 * 
 * @author Asail
 */
public class Comment implements SocialMediaElement {
    
    private String Content;
    private User Author;
    
    /**
     * Default constructor for the Comment class.
     */
    public Comment(){
        
    }
    
    /**
     * Constructs a Comment object with specified content and author.
     * 
     * @param Content the text content of the comment
     * @param Author the user who made the comment
     */
    public Comment(String Content, User Author){
        this();
        this.Content=Content;
        this.Author=Author;
    }

    /**
     * Gets the content of the comment.
     * 
     * @return the text content of the comment
     */
    public String getContent(){
        return Content;
    }
    
    /**
     * Sets the content of the comment.
     * 
     * @param Content the new text content of the comment
     */
    public void setContent(String Content){
        this.Content = Content;
    }

    /**
     * Gets the author of the comment.
     * 
     * @return the user who made the comment
     */
    public User getAuthor(){
        return Author;
    }
    
    /**
     * Sets the author of the comment.
     * 
     * @param Author the user who made the comment
     */
    public void setAuthor(User Author){
        this.Author = Author;
    }
    
    /**
     * Displays the comment details, including the author's username and the content.
     */
    @Override
    public void display(){
        System.out.println("Comment by " + Author.getUserName() + ": " + Content); 
    }
    
    /**
     * Returns the type of the object as a string.
     * 
     * @return "Comment" as the type of this object
     */
    @Override
    public String getType(){
        return "Comment";
    }
     
    /**
     * Returns a string representation of the comment.
     * 
     * @return a string in the format "Comment by Author: Content"
     */
    @Override
    public String toString() {
        return "Comment by " + Author.getUserName() + ": "+Content.substring(0, Math.min(Content.length(),20));
    }
    
    /**
     * Checks if two Comment objects are equal based on their content and author.
     * 
     * @param o the object to compare with this comment
     * @return true if both the content and author are equal, false otherwise
     */
    @Override 
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass()!=o.getClass())
            return false;
        Comment comment =(Comment)o;
        return Objects.equals(Content,comment.Content)&&Objects.equals(Author, comment.Author);
    }
    
    /**
     * Generates a hash code for the Comment object based on its content and author.
     * 
     * @return the hash code of the Comment object
     */
    @Override
    public int hashCode(){
        return Objects.hash(Content,Author);
    }
}
