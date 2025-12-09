package Project;

/**
 * Represents a regular user in the social media system, extending the abstract {@link User} class.
 * Provides implementations for displaying user information, retrieving user type, and formatting 
 * user details as a string.
 * 
 * @author Asail
 */
class RegularUser extends User {

    /**
     * Default constructor initializing an empty RegularUser instance.
     */
    public RegularUser(){
        super();
    }
    
    /**
     * Constructs a RegularUser with the specified username and email.
     * 
     * @param userName the username of the user
     * @param email the email of the user
     */
    public RegularUser(String userName, String email) {
        super(userName, email);
    }

    /**
     * Displays the username and email of this RegularUser.
     */
    @Override
    public void display() {
        System.out.println("Regular User: " + userName + ", Email: " + email);
    }

    /**
     * Returns a string with details about the RegularUser.
     * 
     * @return a formatted string with the user's details
     */
    @Override
    public String toString() {
        return "Regular User: " + userName + ", Email: " + email + ", Posts: " + posts.size() + ", Following: " + following.size() + ", Followers: " + followers.size();
    }

    /**
     * Returns the user type as "Regular User".
     * 
     * @return the type of user
     */
    @Override
    public String getType() {
        return "Regular User";
    }
}