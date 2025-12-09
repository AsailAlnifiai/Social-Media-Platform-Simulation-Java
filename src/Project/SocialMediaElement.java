package Project;


/**
 * Interface representing a general element in a social media platform.
 * Classes implementing this interface must provide methods to display
 * their details and return their specific type.
 * 
 * @author Haneen
 */
public interface SocialMediaElement {

    /**
     * Displays the element's details.
     */
    void display();
    
    /**
     * Returns the type of the social media element.
     * 
     * @return the type as a String
     */
    String getType();
}
