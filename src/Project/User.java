package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a user in a social media system, with attributes for username, email,
 * posts, and following relationships. This abstract class is designed for extension 
 * and implements the SocialMediaElement interface.
 *
 * Methods include following, unfollowing users, managing posts, and displaying 
 * followers and followees, with exception handling for invalid operations.
 * 
 * @author sadeem
 * @author Nada Almalki
 * @author Asail
 */

abstract class User implements SocialMediaElement {
    
    protected String userName;
    protected String email;
    protected List<Post> posts;
    protected List<Following> following;
    protected List<Following> followers; 

    /**
     * Default constructor initializing the lists.
     */
    public User() {
        this.posts = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    /**
     * Constructor that initializes a user with a username and email.
     * 
     * @param userName The username of the user
     * @param email The email address of the user
     */
    public User(String userName, String email) {
        this();
        this.userName = userName;
        this.email = email;
    }

    /**
     * Abstract method to display information about the user.
     * Subclasses must implement this method.
     */
    @Override
    public abstract void display();
    
    /**
     * Gets the username of the user.
     * 
     * @return The username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the user.
     * 
     * @param userName The new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the email of the user.
     * 
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the list of posts created by the user.
     * 
     * @return The list of posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Gets the list of users that this user is following.
     * 
     * @return The list of following users
     */
    public List<Following> getFollowing() {
        return following;
    }
    
    /**
     * Gets the list of users that follows this user.
     * 
     * @return The list of user followers
     */
    public List<Following> getFollowers() {
        return followers;
    }
   
    
    /**
     * Adds a new post to the user's list of posts.
     * 
     * @param post The post to be added
     */
    public void addPost(Post post) {
        posts.add(post);
    }


    /**
     * Follows another user.
     * 
     * @param user The user to follow
     * @throws SocialMediaException if the user is null or already followed
     */
    public void followUser(User user) throws SocialMediaException {
        if (user == null) {
            throw new SocialMediaException("Cannot Follow Empty User");
        }
        
        if (this.equals(user)) {
            throw new SocialMediaException("Cannot Follow yourself");
        }
        
        Following newFollowing = new Following(this, user);
        if (!following.contains(newFollowing)) {
            following.add(newFollowing);
            
            //Following followRelation = new Following(user,this);
            if (!user.getFollowers().contains(newFollowing)){ //(followRelation)){
                user.getFollowers().add(newFollowing);//(followRelation);
            }
        } else {
            throw new SocialMediaException("You already Following this user");
        }
    }

    /**
     * Unfollows a user.
     * 
     * @param user The user to unfollow
     * @throws SocialMediaException if the user is null or not followed
     */
    public void unfollowUser(User user) throws SocialMediaException {
        if (user == null) {
            throw new SocialMediaException("Cannot unfollow an empty user.");
        }
        Following followRelation = new Following(this, user);
        if (following.contains(followRelation)) {
            following.remove(followRelation);
            
          //  Following followerRelation = new Following(user,this);
            if (user.getFollowers().contains(followRelation)) {
                user.getFollowers().remove(followRelation);
            }
        } else {
            throw new SocialMediaException("You are not following this user.");
        }
    }

    /**
     * Removes a follower from this user's follower list.
     * 
     * @param follower The user to remove as a follower
     * @throws SocialMediaException if the follower is null or not following
     */
    public void removeFollower(User follower) throws SocialMediaException {
        if (follower == null) {
            throw new SocialMediaException("Cannot remove a null follower.");
        }
        
        Following followerRelation = new Following(follower, this);
       // Following followingRelation = new Following(follower, this); 
        
        if (this.followers.contains(followerRelation)) {
            this.followers.remove(followerRelation);
            
            if (follower.getFollowing().contains(followerRelation)) {
                follower.getFollowing().remove(followerRelation);
            }
            
            System.out.println(follower.getUserName() + " has been removed from your followers list.");
            
        } else {
            throw new SocialMediaException(follower.userName + " is not following you.");
        }
    }

    /**
     * Displays the followers and followees of the user.
     * 
     * @param allUsers The list of all users in the system
     */
    public void displayFollowersAndFollowees(List<User> allUsers) {
        System.out.println("Followers:");
        if (this.followers.isEmpty()) {
            System.out.println("No followers.");
        } else {
            for (Following followRelation : this.followers) {
                System.out.println(followRelation.getFollower().getUserName());
            }
        }

        System.out.println("\nFollowing:");
        if (this.following.isEmpty()) {
            System.out.println("Not following anyone.");
        } else {
            for (Following followRelation : following) {
                System.out.println(followRelation.getFollowee().getUserName());
            }
        }
    }
    
    /**
     * Provides a string representation of the user, including the username, email, 
     * number of posts, following count, and follower count.
     * 
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User: " + userName + ", Email: " + email + ", Posts: " + posts.size() + ", Following: " + following.size()+ ", Followers: "+ followers.size();
    }

    /**
     * Compares this user with another object for equality based on username and email.
     * 
     * @param o the object to compare
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(email, user.email);
    }

    /**
     * Generates a hash code for the user based on username and email.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, email);
    }
}
