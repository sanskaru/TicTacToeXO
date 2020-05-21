package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    static Boolean darkMode, isDarkSet=false; // isDarkSet denotes that darkMode has been set by the user, darkMode is in general dark mode indicator
    SharedPreferences preferences; // for saving user settings, will crassh if not applied in onCreate
    SharedPreferences.Editor editor;
    public static final String themeKey="themeKey";
    public static final String themeType="DarkMode";
    static Context context;
    public void setDarkMode(View view)
    {
        if (darkMode)
        {
            darkMode=false;
            editor.putBoolean(themeType, darkMode).commit(); // saving user preference in the preference file
            themeUtils.onActivityCreateSetConditionTheme(this, darkMode); // apply theme on activity
            setContentView(R.layout.activity_main_menu); // refresh layout
        }
        else
        {
            darkMode=true;
            editor.putBoolean(themeType, darkMode).commit();
            themeUtils.onActivityCreateSetConditionTheme(this, darkMode);
            setContentView(R.layout.activity_main_menu);
        }
    }
    public void clicker(View view)
    {
        int ch=((menuClass) view).getValue();
        switch (ch) {
            case 0:
            {
                Intent intent = new Intent(MainMenu.this, SinglePlayerMode.class);
                intent.putExtra("Dark Mode", darkMode);
                startActivity(intent);
                break;
            }
            case 1:
            {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                intent.putExtra("Dark Mode", darkMode);
                startActivity(intent);
                break;
            }
            case 2:
            {
                Intent intent = new Intent(MainMenu.this, ThreePlayerLocal.class);
                intent.putExtra("Dark Mode", darkMode);
                startActivity(intent);
                break;
            }
            case 3:
            {
                Intent intent = new Intent(MainMenu.this,FourPlayerMode.class);
                intent.putExtra("Dark Mode", darkMode);
                startActivity(intent);
                break;
            }
        }
    }
    public void changeToAbout(View view) {
        Intent intent = new Intent(MainMenu.this,about.class);
        intent.putExtra("Dark Mode", darkMode);
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getSharedPreferences(themeKey,0); // declare it here, after super.OnCreate else app crashes
        editor=preferences.edit();
        if(preferences.contains(themeType)) // if user has set dark mode
        {
            darkMode=preferences.getBoolean(themeType, false);
            isDarkSet=true; // true because user had set it
        }
        if(isDarkSet==false) { // if user didn't set
            switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) { // get device dark mode settings
                case Configuration.UI_MODE_NIGHT_YES: { // systemwide dark mode on
                    darkMode = true;
                    themeUtils.onActivityCreateSetConditionTheme(this, true); // set dark theme
                    break;
                }
                case Configuration.UI_MODE_NIGHT_NO: { // systemwide dark mode off
                    darkMode = false;
                    themeUtils.onActivityCreateSetConditionTheme(this, false); // set light theme
                    break;
                }
            }
        }
        else themeUtils.onActivityCreateSetConditionTheme(this, darkMode); // if user has set, directly apply settings
        setContentView(R.layout.activity_main_menu);
        menuClass numberPicker=(menuClass) findViewById(R.id.choices);
        numberPicker.setAlpha(0f);
        numberPicker.setTranslationZ(-2000f);
        numberPicker.animate().alpha(1f).translationZBy(2000f).setDuration(1500);
    }
}
