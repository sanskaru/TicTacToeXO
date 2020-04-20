package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SinglePlayerMode extends AppCompatActivity {
    int playerState = 0; // at present at two player local mode. 0 is x and 1 is o.
    int[] boardState = {-1, -1, -1, -1, -1, -1, -1, -1, -1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0, winnerwinner = 0; // denotes number of tapped or played grids, winnerwinner denotes somebody has won
    public void drop_in(View view) // method invoked on tapping any grid cell
    {
        ImageView counter = (ImageView) view;
        int cellState = Integer.parseInt(counter.getTag().toString()); // getting the associated tags or basically cell number
        if (boardState[cellState] == -1) {
            counter.setTranslationY(-1000f);
            if (playerState == 0) {
                playerState = 1;
                counter.setImageResource(R.drawable.x);
                playCounter++; // updating number of played grids
            } else {
                playerState = 0;
                counter.setImageResource(R.drawable.o);
                playCounter++;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300); // drop-in animation
            boardState[cellState] = playerState; // changing grid number to record which player tapped so that nobody can change on tapping again
        }
    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_mode);
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayerMode.this);
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch(which){
                        case DialogInterface.BUTTON_POSITIVE:
                        {
                            Toast.makeText(SinglePlayerMode.this, "Hard Mode Selected", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case DialogInterface.BUTTON_NEGATIVE:
                        {
                            Toast.makeText(SinglePlayerMode.this,"Easy Mode Selected",Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            };
        builder
                .setCancelable(false)
                .setTitle("Select Game Mode")
                .setMessage("Select Game Difficulty level")
                .setPositiveButton("Hard", dialogClickListener)
                .setNegativeButton("Easy",dialogClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}