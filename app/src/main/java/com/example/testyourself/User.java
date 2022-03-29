package com.example.testyourself;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

/**
 * This class is for storing the user data. It contains the username, xp, and level of the user. It also
 * contains a method for checking if the user has reached a xp threshold that will give them a level up.
 */

public class User {
    public static String username;
    public static int level;
    public static int xp;
    public static String icon;

    //Method that checks if the user is required a level up
    public static int levelUpCheck()
    {
        if (xp >= 100)
        {
            level++;
            xp = 0;

            return 1;
        }

        return 0;
    }
}
