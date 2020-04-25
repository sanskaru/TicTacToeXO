package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class about extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView title = (TextView) findViewById(R.id.title);
        String tileString=getString(R.string.app_name) + " " + getString(R.string.app_version);
        title.setText(tileString);
        TextView lenkPlox = (TextView) findViewById(R.id.textView4);
        Spanned text;
        text = Html.fromHtml("You can contact me on " +
                "<a href='https://www.android-examples.com/sanskaru//'>GitHub</a>"+", "+
                "<a href='https://www.twitter.com/sannvict090'>Twitter</a>"+", "+"<a href=mailto:sannvict090@gmail.com>or email me</a>");
        lenkPlox.setText(text);
        getSupportActionBar().setTitle("About");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() { // the method to be called when the back button is pressed
        onBackPressed();
        return true;
    }
}
