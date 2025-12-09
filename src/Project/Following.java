package Project;

import java.util.Objects;

/**
 * The Following class represents a relationship where a user (follower) follows
 * another user (followee). It provides methods to manage the relationship and
 * overrides toString(), equals(), and hashCode() methods.
 *
 * @author Nada Almalki
 */
public class Following {
    
    private User follower;
    private User followee;
    
    /**
     * Default constructor for the Following class.
     */
    public Following (){
        
    }
     
    /**
     * Constructor with follower and followee parameters.
     *
     * @param follower the user who is following
     * @param followee the user being followed
     */
    public Following(User follower, User followee) {
        this();
        this.follower = follower;
        this.followee = followee;
    } 

    /**
     * Gets the follower.
     *
     * @return the user who is following
     */
    public User getFollower() {
        return follower;
    }    
    
    /**
     * Sets the follower.
     *
     * @param follower the user who will follow
     */
    public void setFollower(User follower) {
        this.follower = follower;
    }

    /**
     * Gets the followee.
     *
     * @return the user who is being followed
     */
    public User getFollowee() {
        return followee;
    }

    /**
     * Sets the followee.
     *
     * @param followee the user who will be followed
     */
    public void setFollowee(User followee) {
        this.followee = followee;
    }

    /**
     * Converts the Following object to a string representation.
     *
     * @return a string in the format "follower Following followee"
     */
    @Override 
    public String toString() {
        return follower.getUserName() + " Following" + followee.getUserName();
    }
    
    /**
     * Checks if two Following objects are equal.
     *
     * @param o the object to compare
     * @return true if both the follower and followee are the same in both
     * objects, false otherwise
     */
    @Override 
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass()!=o.getClass())
            return false;
        Following following = (Following) o;
        return Objects.equals(follower, following.follower) && Objects.equals(followee, following.followee);
    }
    
    /**
     * Generates a hash code for the Following object based on the follower and
     * followee.
     *
     * @return the hash code of the Following object
     */ 
    @Override 
    public int hashCode() {
        return Objects.hash(follower,followee);
    }
}
