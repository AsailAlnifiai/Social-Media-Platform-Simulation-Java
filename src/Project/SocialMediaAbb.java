package Project;

/**
 * Main class for launching the social media application. It initializes a
 * {@link SocialMediaPlatform} instance and starts the application.
 * 
 * @throws SocialMediaException if an error occurs during application runtime
 * 
 * @author Haneen
 */
public class SocialMediaAbb {

    /**
     *
     * @param args
     * @throws SocialMediaException
     */
    public static void main(String[] args) throws SocialMediaException {
        SocialMediaPlatform platform = new SocialMediaPlatform();
        platform.run();
    }
}
