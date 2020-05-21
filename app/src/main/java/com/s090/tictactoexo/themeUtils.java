package com.s090.tictactoexo;

import android.app.Activity;

class themeUtils {
    /**
     * Set the theme of the Activity
     */
    static Boolean myBool;

    public static void onActivityCreateSetConditionTheme(Activity activity, boolean b) {
        myBool=b;
        if(b) activity.setTheme(R.style.AppTheme);
        else activity.setTheme(R.style.themeLight);
    }
}
