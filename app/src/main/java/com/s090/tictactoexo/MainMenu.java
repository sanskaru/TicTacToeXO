package com.s090.tictactoexo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    public void clicker(View view)
    {
        NumberPicker numberPicker=(NumberPicker) findViewById(R.id.menuSelector);
        numberPicker.setMaxValue(3);
        numberPicker.setMinValue(0);
        String[] pickerVal={"Single Player Mode","2 Player Mode","3 Player Mode","4 Player Mode"};
        numberPicker.setDisplayedValues(pickerVal);
        int ch=numberPicker.getValue();
        switch (ch) {
            case 0:
            {
                Intent intent = new Intent(MainMenu.this, SinglePlayerMode.class);
                startActivity(intent);
                break;
            }
            case 1:
            {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case 2:
            {
                Intent intent = new Intent(MainMenu.this, ThreePlayerLocal.class);
                startActivity(intent);
                break;
            }
            case 3:
            {
                Toast.makeText(MainMenu.this, "On the way my friend.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    public void changeToAbout(View view) {
        Intent intent = new Intent(MainMenu.this,about.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        NumberPicker numberPicker=(NumberPicker) findViewById(R.id.menuSelector);
        numberPicker.setMaxValue(3);
        numberPicker.setMinValue(0);
        String[] pickerVal={"Single Player Mode","2 Player Mode","3 Player Mode","4 Player Mode"};
        numberPicker.setDisplayedValues(pickerVal);
    }
}
