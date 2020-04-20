package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
        int cellState = Integer.parseInt(counter.getTag().toString()); // getting the associated tags or basically cell number
        if (boardState[cellState] == -1)
        {
            counter.setTranslationY(-1000f);
            if (playerState == 0) {
                boardState[cellState]=playerState;
                playerState++;
                counter.setImageResource(R.drawable.x);
                playCounter++; // updating number of played grids
            } else if (playerState==1){
                boardState[cellState]=playerState;
                playerState++;
                counter.setImageResource(R.drawable.o);
                playCounter++;
            }
            else
            {
                boardState[cellState]=playerState;
                counter.setImageResource(R.drawable.sq);
                playCounter++;
                playerState=0;
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

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_player_local);
    }
}
