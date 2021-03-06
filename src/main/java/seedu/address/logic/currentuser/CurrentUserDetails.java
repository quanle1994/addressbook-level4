package seedu.address.logic.currentuser;

/**
 * Describe the current user
 */
public class CurrentUserDetails {
    private static String userId = "PUBLIC";
    private static String userIdHex = "";
    private static String saltText = "";
    private static String passwordText = "";

    public static void setCurrentUser(String userId, String userIdHex, String saltText, String passwordText) {
        CurrentUserDetails.userId = userId;
        CurrentUserDetails.userIdHex = userIdHex;
        CurrentUserDetails.saltText = saltText;
        CurrentUserDetails.passwordText = passwordText;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        CurrentUserDetails.userId = userId;
    }

    public static String getUserIdHex() {
        return userIdHex;
    }

    public static void setUserIdHex(String userIdHex) {
        CurrentUserDetails.userIdHex = userIdHex;
    }

    public static String getSaltText() {
        return saltText;
    }

    public static void setSaltText(String saltText) {
        CurrentUserDetails.saltText = saltText;
    }

    public static String getPasswordText() {
        return passwordText;
    }

    public static void setPasswordText(String passwordText) {
        CurrentUserDetails.passwordText = passwordText;
    }
}
