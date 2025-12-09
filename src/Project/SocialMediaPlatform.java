package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main platform class managing users, posts, and relationships.
 * Provides a menu for actions such as adding users, following/unfollowing, 
 * viewing info, and admin-only deletions.
 * 
 * @throws SocialMediaException on invalid actions.
 * 
 * @author Haneen
 * @author Ghala
 * @author Asail
 */
public class SocialMediaPlatform {
    private final List<User> users;
    private Scanner scanner;
    
    /**
     * Initializes the platform with an empty user list and a scanner for input.
     */
    public SocialMediaPlatform() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Adds a new user (Admin or RegularUser) based on input choice.
     */
    public void addUser() {
        System.out.println("Choose User Type: \n1. Admin  \n2. Regular User");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Write User Name: ");
        String username = scanner.nextLine();
        System.out.println("Write Email: ");
        String email = scanner.nextLine();
        
        if (findUserByUsername(username) != null) {
            System.out.println("Error: Username already exists. Please choose a different one.");
            return;
        }
        
        if (findUserByEmail(email) != null) {
            System.out.println("Error: Email '" + email + "' is already registered. Please use a different email.");
            return; 
        }
        
        if (!isValidEmail(email)) {
            System.out.println("Error: Invalid email format. Email must be 'name@domain.com'");
            return;
        }

        User newUser;  
        if (userTypeChoice == 1) {
            newUser = new Admin(username, email); 
        } else {
            newUser = new RegularUser(username, email); 
        }

        users.add(newUser);
        System.out.println("User Added Successfully!");
    }
    
    /**
     * Adds a post for a specified user if found.
     */
    public void addPost() {
        System.out.println("Write User Name: ");
        String username = scanner.nextLine();
        User user = findUserByUsername(username);
        if (user != null) {
            System.out.println("Write Post Content: ");
            String content = scanner.nextLine(); 
            Post newPost = new Post(content, user);
            user.addPost(newPost);
            System.out.println("Post Added Successfully!");
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Makes one user follow another based on usernames entered.
     */
    public void followUser() {
        System.out.println("Write Your User Name: ");
        String followerUsername = scanner.nextLine();
        System.out.println("Write User Who Want To Follow: ");
        String followeeUsername = scanner.nextLine();
        
        User follower = findUserByUsername(followerUsername);
        User followee = findUserByUsername(followeeUsername);
        
        if (follower != null && followee != null) {
            try {
                follower.followUser(followee);
                System.out.println("You Follow Now " + followeeUsername);
            } catch (SocialMediaException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Unfollows a user.
     */
    public void unfollowUser(){
        System.out.println("Write Your User Name: ");
        String followerUserName = scanner.nextLine();
        System.out.println("Write User Who Want To Unfollow: ");
        String unfolloweeUsername = scanner.nextLine();
        
        User follower = findUserByUsername(followerUserName);
        User followee = findUserByUsername(unfolloweeUsername);
        
        if (follower != null && followee != null) {
            try {
                follower.unfollowUser(followee);
                System.out.println("You Unfollowed " + unfolloweeUsername);
            } catch (SocialMediaException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Removes a user from another user's following list.
     */
    public void removeFollower(){
        System.out.println("Write Your User Name: ");
        String followerUserName = scanner.nextLine();
        System.out.println("Write User Who Want To Remove From Your Followers List: ");
        String removefolloweeUsername = scanner.nextLine();
        
        User follower = findUserByUsername(followerUserName);
        User followee = findUserByUsername(removefolloweeUsername);
        
        if (follower != null && followee != null) {
            try {
                follower.removeFollower(followee);
                System.out.println("You Removed " + removefolloweeUsername);
            } catch (SocialMediaException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Deletes a specified user (admin-only action).
     * @param admin the admin performing the action
     */
    public void deleteUser(User admin) {
        System.out.println("Write The User Name Of The User To Delete: ");
        String username = scanner.nextLine();
        User userToDelete = findUserByUsername(username);

        if (userToDelete != null) {
            ((Admin) admin).deleteUser(userToDelete, users); 
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Deletes a specific post of a user (admin-only action).
     * @param admin the admin performing the action
     */
    public void deletePost(User admin) {
        System.out.println("Write User Name Of The Post Author: ");
        String username = scanner.nextLine();
        User user = findUserByUsername(username);

        if (user != null) {
            System.out.println("Write Post Number: ");
            int postIndex = scanner.nextInt();
            if (postIndex < 0 || postIndex >= user.getPosts().size()) {
                System.out.println("Post Not Found!");
            } else {
                Post postToDelete = user.getPosts().get(postIndex);
                ((Admin) admin).deletePost(user, postToDelete);
            }
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Displays detailed information for a specific user.
     */
    public void displayUserInfo() {
        System.out.println("Write User Name: ");
        String username = scanner.nextLine();
        User user = findUserByUsername(username);
        if (user != null) {
            user.display();
            System.out.println("Posts: "); 
            for (Post post : user.getPosts()){
                post.display();
            }
        } else {
            System.out.println("User Not Found!");
        }
    }
    
    /**
     * Generates and prints a report of all users and their posts.
     */
    public void generateReport() {
        System.out.println("Report: ");
        for (User user : users) {
            System.out.println(user);
            for (Post post : user.getPosts()) {
                System.out.println("\t" + post);
            }
           
            System.out.println();
        }
        System.out.println("Report Genrate Successfully!");
    }
    
    /**
     * Adds a comment to a specific post by a specified user.
     * @throws Project.SocialMediaException
     */
    public void addComment() throws SocialMediaException {
        System.out.println("Write Username Who Post: ");
        String postAuthorUsername = scanner.nextLine();
        User postAuthor = findUserByUsername(postAuthorUsername);
        if (postAuthor == null) {
            System.out.println("User Not Found!");
            return;
        }
        System.out.println("Write Post Number: ");
        int postIndex = scanner.nextInt();
        scanner.nextLine();
        if (postIndex < 0 || postIndex >= postAuthor.getPosts().size()) {
            System.out.println("Wrong Post Number!");
            return;
        }
        
        Post post = postAuthor.getPosts().get(postIndex);
        
        System.out.println("Write User Who Want To Comment: ");
        String commenterUsername = scanner.nextLine();
        User commenter = findUserByUsername(commenterUsername);
        if (commenter == null){
            System.out.println("User Not Found!");
            return;
        }
        
        System.out.println("Write Comment Content: ");
        String content = scanner.nextLine();
        
        Comment newComment = new Comment (content, commenter);
        post.addComment(newComment);
        System.out.println("Comment Add Successfully!");
    }
    
    /**
     * Displays a user's followers and followees.
     */
    public void displayFollowersAndFollowees() {
        System.out.println("Write User Name: ");
        String username = scanner.nextLine();
        User user = findUserByUsername(username);
        if (user != null) {
            user.displayFollowersAndFollowees(users);
        } else {
            System.out.println("User Not Found!");
        }
    }

    /**
     * Finds and returns a user by username.
     *
     * @param username the username to search for
     * @return the matching user or null if not found
     */
    private User findUserByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds and returns a user by email.
     *
     * @param email the email to search for
     * @return the matching user or null if not found
     */
    private User findUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Checks if the provided email string has a valid format.
     * A minimal validation is performed to ensure the email contains '@' and '.' characters.
     * * @param email the email string to validate
     * @return true if the email is considered valid (contains '@' and '.'); false otherwise
     */
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Runs the social media platform, displaying a menu for user actions.
     *
     * @throws SocialMediaException for invalid actions
     */
    public void run() throws SocialMediaException {
        while (true) {
            System.out.println("\n --- Menu Social Media ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Post");
            System.out.println("3. Add Comment");
            System.out.println("4. Following User");
            System.out.println("5. Unfollowing User");
            System.out.println("6. Removing User From The Followers List");
            System.out.println("7. Display Followers and Following");
            System.out.println("8. Display User Info");
            System.out.println("9. Genrate Report");
            System.out.println("10. Delete User (For The Admin Only)");
            System.out.println("11. Delete Post (For The Admin Only)");
            System.out.println("12. Exit");
            
            System.out.print("Write Choice: ");
            String inputChoice = scanner.nextLine();
            
            int choice;
            try {
                choice = Integer.parseInt(inputChoice);
            } catch (NumberFormatException e) {
                System.out.println("Error: Choice must be a number between 1 and 12.");
                continue;
            }
            
            switch(choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addPost();
                    break;
                case 3:
                    addComment();
                    break;
                case 4:
                    followUser();
                    break;
                case 5: 
                    unfollowUser();
                    break;
                case 6:
                    removeFollower();
                    break;
                case 7:
                    displayFollowersAndFollowees();
                    break;
                case 8:
                    displayUserInfo();
                    break;
                case 9:
                    generateReport();
                    break;
                case 10:
                    System.out.println("Enter Admin User Name: ");
                    String adminUsername = scanner.nextLine();
                    User adminUser = findUserByUsername(adminUsername);
                    if (adminUser instanceof Admin) {
                        deleteUser(adminUser); 
                    } else {
                        System.out.println("Only Admins Can Perform This Action");
                    }
                    break;
                case 11:
                    System.out.println("Enter Admin User Name: ");
                    String adminPostUsername = scanner.nextLine();
                    User adminPostUser = findUserByUsername(adminPostUsername);
                    if (adminPostUser instanceof Admin) {
                        deletePost(adminPostUser);
                    } else {
                        System.out.println("Only Admins Can Perform This Action");
                    }
                    break;
                case 12:
                    System.out.println("Thank You For Using Our Platform!");
                    return;
                default:
                    System.out.println("Wrong Choice Please Try Again.");
            }  
        }
    }    
}

/**
 * Exception for invalid social media actions.
 * 
 * @param message error description
 */
class SocialMediaException extends Exception {
    public SocialMediaException(String message) {
        super(message);
    }
}