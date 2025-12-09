package Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with administrative privileges, extending User with functions 
 * for deleting users, posts.
 * Extends the {@code User} class with additional functions for deleting users and posts.
 * 
 * @author Asail
 * @author Hessa
 */
class Admin extends User {
    private List<User> deletedUsers;
    private List<Post> deletedPosts;

    /**
     * Default constructor.
     */
    public Admin() {
        super();
    }
    /**
     * Constructor that initializes an admin user with a username and email.
     * 
     * @param userName The username of the admin
     * @param email The email address of the admin
     */
    public Admin(String userName, String email) {
        super(userName, email);
        this.deletedUsers = new ArrayList<>();
        this.deletedPosts = new ArrayList<>();
    }

    /**
     * Removes a user from the system.
     * 
     * @param user The user to be removed
     * @param allUsers The list of all users in the system
     */
    public void deleteUser(User user, List<User> allUsers) {
        if (allUsers.contains(user)) {
            allUsers.remove(user);
            System.out.println(user.getUserName() + " Has Been Removed From The System By The Admin");
        } else {
            System.out.println("User Not Found");
        }
    }
    
    /**
     * @return List of deleted users
     */
    public List<User> getDeletedUsers() {
        return deletedUsers;
    }

    /**
     * Removes a post from a user's list of posts.
     * 
     * @param user The user whose post is to be deleted
     * @param post The post to be deleted
     */
    public void deletePost(User user, Post post) {
        if (user.getPosts().contains(post)) {
            user.getPosts().remove(post);
            System.out.println("Post By " + user.getUserName() + " Has Been Removed By The Admin.");
        } else {
            System.out.println("Post Not Found.");
        }
    }


    /**
    * @return List of deleted posts
    */
    public List<Post> getDeletedPosts() {
        return deletedPosts;
    }
    
    /**
     * Implements the abstract display method for admin.
     * Displays admin-specific information.
     */
    @Override
    public void display() {
        System.out.println("Admin User: " + userName + ", Email: " + email );
    }

    /**
     * Overrides the toString method to include information about the admin.
     * 
     * @return A string representation of the admin
     */
    @Override
    public String toString() {
        return "Admin User: " + userName + ", Email: " + email + ", Posts: " + posts.size() +  ", Following: " + following.size() + ", Followers: " + followers.size();
    }

    /**
     * Returns the type of the object as a string.
     * 
     * @return "Admin" as the type of this object
     */
    @Override
    public String getType() {
        return "Admin";
    }
}