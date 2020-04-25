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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int playerState=0; // at present at two player local mode. 1 is x and 0 is o.
    int[] boardState={-1,-1,-1,-1,-1,-1,-1,-1,-1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0, winnerwinner=0; // denotes number of tapped or played grids, winnerwinner denotes somebody has won
    public void myDialog(String title, String msg) // method to call dialog box
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title).setMessage(msg).setCancelable(false);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() { // attaches an onClickListener to button
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
    public void drop_in(View view) // method invoked on tapping any grid cell
    {
        ImageView counter=(ImageView) view;
        int cellState = Integer.parseInt(counter.getTag().toString()); // getting the associated tags or basically cell number
        if (boardState[cellState]==-1) {
            counter.setTranslationY(-1000f);
            if (playerState == 0) {
                playerState = 1;
                counter.setImageResource(R.drawable.x);
                playCounter++; // updaating number of played grids
            } else {
                playerState = 0;
                counter.setImageResource(R.drawable.o);
                playCounter++;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300); // drop-in animation
            boardState[cellState] = playerState; // changing grid number to record which player tapped so that nobody can change on tapping again
        }   //all possible winning positions below
            if(boardState[0]==boardState[1] && boardState[1]==boardState[2] && boardState[0]!=-1) {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[0]==boardState[3] && boardState[3]==boardState[6] && boardState[0]!=-1)
            {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[0]==boardState[4] && boardState[4]==boardState[8] && boardState[0]!=-1)
            {
                Log.i("Info","Player "+boardState[0]+" has won.");
                myDialog("Congratulations!","Player "+boardState[0]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[1]==boardState[4] && boardState[4]==boardState[7] && boardState[1]!=-1)
            {
                Log.i("Info","Player "+boardState[1]+" has won.");
                myDialog("Congratulations!","Player "+boardState[1]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[2]==boardState[5] && boardState[5]==boardState[8] && boardState[2]!=-1)
            {
                Log.i("Info","Player "+boardState[2]+" has won.");
                myDialog("Congratulations!","Player "+boardState[2]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[2]==boardState[4] && boardState[4]==boardState[6] && boardState[2]!=-1)
            {
                Log.i("Info","Player "+boardState[2]+" has won.");
                myDialog("Congratulations!","Player "+boardState[2]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[3]==boardState[4] && boardState[4]==boardState[5] && boardState[3]!=-1)
            {
                Log.i("Info","Player "+boardState[3]+" has won.");
                myDialog("Congratulations!","Player "+boardState[3]+" has won.");
                winnerwinner=1;
            }
            else if(boardState[6]==boardState[7] && boardState[7]==boardState[8] && boardState[6]!=-1)
            {
                Log.i("Info","Player "+boardState[6]+" has won.");
                myDialog("Congratulations!","Player "+boardState[6]+" has won.");
                winnerwinner=1;
            }
            if (playCounter==9 && winnerwinner==0) // if all grids have been tapped and nobody won, i.e. it's a draw or a tie
            {
                Log.i("Info","Board full.");
                myDialog("It's a Tie!", "The board is full. Please play again.");
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder initDialogBuilder=new AlertDialog.Builder(MainActivity.this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                    {
                        playerState=1;
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE:
                    {
                        playerState=0;
                        break;
                    }
                }
            }
        };
        initDialogBuilder
                .setTitle("Select your counter")
                .setPositiveButtonIcon(getDrawable(R.drawable.o))
                .setPositiveButton("",listener)
                .setNegativeButtonIcon(getDrawable(R.drawable.x))
                .setNegativeButton("",listener)
                .setMessage("Please select your counter.");
        AlertDialog initDialog = initDialogBuilder.create();
        initDialog.show();
        Button btnPositive = initDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = initDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 1;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);
        getSupportActionBar().setTitle("Two-Player local mode");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() { // the method to be called when the back button is pressed
        onBackPressed();
        return true;
    }
}
