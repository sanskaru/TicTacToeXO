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
        if(numberPicker.getValue()!=1) Toast.makeText(MainMenu.this,"Coming Soon!",Toast.LENGTH_LONG).show();
        else
        {
            Intent intent=new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        }
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
