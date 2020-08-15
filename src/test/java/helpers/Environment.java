package helpers;

public class Environment {
    public final static String
        fbUrl = System.getProperty("fb_url", "http://facebook.com"),
        fbUserName = System.getProperty("fb_username", null),
        fbPassword = System.getProperty("fb_password", null),
        fbNameSurname =  System.getProperty("fb_name_surname", null),
        remoteDriverUrl = System.getProperty("remote_driver_url", null),
        videoStorageUrl = System.getProperty("video_storage_url", null);
    public static boolean
            isCiBuild = remoteDriverUrl != null,
            isVideoOn = videoStorageUrl != null;
}

