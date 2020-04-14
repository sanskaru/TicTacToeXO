package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int playerState=0; // at present at two player local mode. 0 is red and 1 is yellow.
    int[] boardState={-1,-1,-1,-1,-1,-1,-1,-1,-1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0; // denotes number of tapped or played grids56t
    public void myDialog(String title, String msg) // method to call dialog box
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(msg).setCancelable(false);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                startActivity(getIntent());
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void drop_in(View view)
    {
        ImageView counter=(ImageView) view;
        int cellState = Integer.parseInt(counter.getTag().toString());
        if (boardState[cellState]==-1) {
            counter.setTranslationY(-1000f);
            if (playerState == 0) {

                playerState = 1;
                counter.setImageResource(R.drawable.red);
                playCounter++;
            } else {
                playerState = 0;
                counter.setImageResource(R.drawable.yellow);
                playCounter++;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
            boardState[cellState] = playerState;
        }   //all possible winning positions
            if(boardState[0]==boardState[1] && boardState[1]==boardState[2] && boardState[0]!=-1) {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            }
            else if(boardState[0]==boardState[3] && boardState[3]==boardState[6] && boardState[0]!=-1)
            {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            }
            else if(boardState[0]==boardState[4] && boardState[4]==boardState[8] && boardState[0]!=-1)
            {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            }
            else if(boardState[1]==boardState[4] && boardState[4]==boardState[7] && boardState[1]!=-1)
            {
                Log.i("Info","Player "+boardState[1]+" has won.");
                myDialog("Congratulations!","Player "+boardState[1]+" has won.");
            }
            else if(boardState[2]==boardState[5] && boardState[5]==boardState[8] && boardState[2]!=-1)
            {
                Log.i("Info","Player "+boardState[2]+" has won.");
                myDialog("Congratulations!","Player "+boardState[2]+" has won.");
            }
            else if(boardState[2]==boardState[4] && boardState[4]==boardState[6] && boardState[2]!=-1)
            {
                Log.i("Info","Player "+boardState[2]+" has won.");
                myDialog("Congratulations!","Player "+boardState[2]+" has won.");
            }
            else if(boardState[3]==boardState[4] && boardState[4]==boardState[5] && boardState[3]!=-1)
            {
                Log.i("Info","Player "+boardState[3]+" has won.");
                myDialog("Congratulations!","Player "+boardState[3]+" has won.");
            }
            else if(boardState[6]==boardState[7] && boardState[7]==boardState[8] && boardState[6]!=-1)
            {
                Log.i("Info","Player "+boardState[6]+" has won.");
                myDialog("Congratulations!","Player "+boardState[6]+" has won.");
            }
            if (playCounter==9)
            {
                Log.i("Info","Board full.");
                myDialog("It's a Draw!", "The board is full. Please play again.");
            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
