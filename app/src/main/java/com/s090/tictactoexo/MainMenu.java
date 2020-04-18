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
        if(numberPicker.getValue()==0)
        {
            Intent intent=new Intent(MainMenu.this, SinglePlayerMode.class);
            startActivity(intent);
        }
        else if(numberPicker.getValue()==1)
        {
            Intent intent=new Intent(MainMenu.this, MainActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(MainMenu.this,"Work in progress, Coming soon.",Toast.LENGTH_SHORT).show();
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
