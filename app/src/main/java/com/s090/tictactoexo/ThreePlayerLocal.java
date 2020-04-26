package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ThreePlayerLocal extends AppCompatActivity {
    int playerState=0; // at present at three player local mode. 0 is sq and 1 is x and 2 is o
    int[] boardState={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0, winnerwinner = 0; // denotes number of tapped or played grids, winnerwinner denotes somebody has won
    public void myDialog(String title, String msg) // method to call dialog box
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ThreePlayerLocal.this);
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
        ImageView counter = (ImageView) view;
        ImageView playaaah = (ImageView) findViewById(R.id.playaaah);
        int cellState = Integer.parseInt(counter.getTag().toString()); // getting the associated tags or basically cell number
        if (boardState[cellState] == -1)
        {
            counter.setTranslationY(-1000f);
            if (playerState == 0) {
                boardState[cellState]=playerState;
                playerState++;
                counter.setImageResource(R.drawable.x);
                playCounter++;
                playaaah.setImageResource(R.drawable.o);// updating number of played grids
            } else if (playerState==1){
                boardState[cellState]=playerState;
                playerState++;
                counter.setImageResource(R.drawable.o);
                playCounter++;
                playaaah.setImageResource(R.drawable.sq);
            }
            else
            {
                boardState[cellState]=playerState;
                counter.setImageResource(R.drawable.sq);
                playCounter++;
                playerState=0;
                playaaah.setImageResource(R.drawable.x);
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300); // drop-in animation
        }
        //all possible winning positions below
        if(boardState[0]==boardState[1] && boardState[1]==boardState[2] && boardState[2]==boardState[3] && boardState[0]!=-1) {
            Log.i("Info","Player "+boardState[0]+" has won.");
            myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[0]==boardState[4] && boardState[4]==boardState[8] && boardState[8]==boardState[12] && boardState[0]!=-1)
        {
            Log.i("Info","Player "+boardState[0]+" has won.");
            myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[0]==boardState[5] && boardState[5]==boardState[10] && boardState[10]==boardState[15] && boardState[0]!=-1)
        {
            Log.i("Info","Player "+boardState[0]+" has won.");
            myDialog("Congratulations!","Player "+boardState[0]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[1]==boardState[5] && boardState[5]==boardState[9] && boardState[9]==boardState[13] && boardState[1]!=-1)
        {
            Log.i("Info","Player "+boardState[1]+" has won.");
            myDialog("Congratulations!","Player "+boardState[1]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[2]==boardState[6] && boardState[6]==boardState[10] && boardState[10]==boardState[14] && boardState[2]!=-1)
        {
            Log.i("Info","Player "+boardState[2]+" has won.");
            myDialog("Congratulations!","Player "+boardState[2]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[3]==boardState[7] && boardState[7]==boardState[11] && boardState[11]==boardState[15] && boardState[3]!=-1)
        {
            Log.i("Info","Player "+boardState[3]+" has won.");
            myDialog("Congratulations!","Player "+boardState[3]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[3]==boardState[6] && boardState[6]==boardState[9] && boardState[9]==boardState[12] && boardState[3]!=-1)
        {
            Log.i("Info","Player "+boardState[3]+" has won.");
            myDialog("Congratulations!","Player "+boardState[3]+" has won.");
            winnerwinner=1;
        }
        else if(boardState[4]==boardState[5] && boardState[5]==boardState[6] && boardState[6]==boardState[7] && boardState[6]!=-1)
        {
            Log.i("Info","Player "+boardState[4]+" has won.");
            myDialog("Congratulations!","Player "+boardState[4]+" has won.");
            winnerwinner=1;
        }
    else if(boardState[8]==boardState[9] && boardState[9]==boardState[10] && boardState[10]==boardState[11] && boardState[8]!=-1)
    {
        Log.i("Info","Player "+boardState[8]+" has won.");
        myDialog("Congratulations!","Player "+boardState[8]+" has won.");
        winnerwinner=1;
    }
    else if(boardState[12]==boardState[13] && boardState[13]==boardState[14] && boardState[14]==boardState[15] && boardState[12]!=-1)
    {
        Log.i("Info","Player "+boardState[12]+" has won.");
        myDialog("Congratulations!","Player "+boardState[12]+" has won.");
        winnerwinner=1;
    }
        if (playCounter==16 && winnerwinner==0) // if all grids have been tapped and nobody won, i.e. it's a draw or a tie
        {
            Log.i("Info","Board full.");
            myDialog("It's a Tie!", "The board is full. Please play again.");
        }
    }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_local);
        final ImageView playaaah = (ImageView) findViewById(R.id.playaaah);
        AlertDialog.Builder initDialogBuilder=new AlertDialog.Builder(ThreePlayerLocal.this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        {
                            playerState=1;
                            playaaah.setImageResource(R.drawable.o);
                            break;
                        }
                        case DialogInterface.BUTTON_NEGATIVE:
                        {
                            playerState=0;
                            playaaah.setImageResource(R.drawable.x);
                            break;
                        }
                        case DialogInterface.BUTTON_NEUTRAL:
                        {
                            playerState=2;
                            playaaah.setImageResource(R.drawable.sq);
                            break;
                        }
                    }
                }
            };
            initDialogBuilder
                    .setTitle("Select your counter")
                    .setNegativeButtonIcon(getDrawable(R.drawable.x))
                    .setPositiveButton("", listener)
                    .setPositiveButtonIcon(getDrawable(R.drawable.o))
                    .setNegativeButton("", listener)
                    .setNeutralButton("      ", listener)
                    .setMessage("Please select your counter.");
            AlertDialog initDialog = initDialogBuilder.create();
            initDialog.show();
            Button neutralButton = initDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            Drawable drawable = getDrawable(R.drawable.sq);
            // set the bounds to place the drawable a bit right
            drawable.setBounds((int) (drawable.getIntrinsicWidth() * 0.05),
                    0, (int) (drawable.getIntrinsicWidth() * 0.25),
                    (int) (drawable.getIntrinsicHeight() * 0.2));
            neutralButton.setCompoundDrawables(drawable, null, null, null);
            Button positiveButton = initDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button negativeButton = initDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            layoutParams.weight = 10;
            positiveButton.setLayoutParams(layoutParams);
            negativeButton.setLayoutParams(layoutParams);
            neutralButton.setLayoutParams(layoutParams);
            getSupportActionBar().setTitle("Three-player local mode");
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
