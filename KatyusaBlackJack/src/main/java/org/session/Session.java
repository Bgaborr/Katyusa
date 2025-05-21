package org.session;

import org.models.User;

public class Session {
    private static User currentUser;
    private static int currentTokens;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void setTokens(int tokens) {
        currentTokens = tokens;
    }

    public static int getTokens() {
        return currentTokens;
    }
}
