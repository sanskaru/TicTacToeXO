package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FourPlayerMode extends AppCompatActivity {
    int playerState=0; // at present at three player local mode. 0 is sq and 1 is x and 2 is o
    int[] boardState={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0, winnerwinner = 0; // denotes number of tapped or played grids, winnerwinner denotes somebody has won
    Boolean b;
    public void myDialog(String title, String msg) // method to call dialog box
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(FourPlayerMode.this);
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
        Button button=dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if(b) button.setTextColor(Color.WHITE);
        else button.setTextColor(Color.BLACK);
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
            else if (playerState==2)
            {
                boardState[cellState]=playerState;
                counter.setImageResource(R.drawable.sq);
                playCounter++;
                playerState=3;
                playaaah.setImageResource(R.drawable.tri);
            }
            else
            {
                boardState[cellState]=playerState;
                counter.setImageResource(R.drawable.tri);
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
        Intent intent=getIntent();
        b=intent.getBooleanExtra("Dark Mode", true);
        System.out.println("Bool "+b);
        themeUtils.onActivityCreateSetConditionTheme(this, b);
        setContentView(R.layout.activity_three_player_local);

        final ImageView playaaah = (ImageView) findViewById(R.id.playaaah);
        LayoutInflater layoutInflater = LayoutInflater.from(this); // setting custom dialog view
        View custDialog = layoutInflater.inflate(R.layout.my_alert, null); // making it a view
        AlertDialog.Builder initDialogBuilder=new AlertDialog.Builder(FourPlayerMode.this);
        initDialogBuilder
                .setTitle("Select your counter")
                .setMessage("Please select your counter.");
        final AlertDialog initDialog = initDialogBuilder.create();
        initDialog.setView(custDialog); // setting view as our dialog view
        View.OnClickListener listener = new View.OnClickListener() { // custom onClickListener for customised buttons
            @Override
            public void onClick(View view) {
                int i = view.getId();
                switch (i)
                {
                    case R.id.o_button:
                    {
                        playerState=1;
                        playaaah.setImageResource(R.drawable.o);
                        initDialog.dismiss(); // since this won't dismiss by itself
                        break;
                    }
                    case R.id.x_button:
                    {
                        playerState=0;
                        playaaah.setImageResource(R.drawable.x);
                        initDialog.dismiss();
                        break;
                    }
                    case R.id.sq_button:
                    {
                        playerState=2;
                        playaaah.setImageResource(R.drawable.sq);
                        initDialog.dismiss();
                        break;
                    }
                    case R.id.tri_button:
                    {
                        playerState=3;
                        playaaah.setImageResource(R.drawable.tri);
                        initDialog.dismiss();
                        break;
                    }
                }
            }
        };
        custDialog.findViewById(R.id.tri_button).setOnClickListener(listener); // attaching the onClick method to every button
        custDialog.findViewById(R.id.sq_button).setOnClickListener(listener);
        custDialog.findViewById(R.id.x_button).setOnClickListener(listener);
        custDialog.findViewById(R.id.o_button).setOnClickListener(listener);
        initDialog.show();

        initDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });
        androidx.gridlayout.widget.GridLayout gridLayout=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        if(b) gridLayout.setBackground(getDrawable(R.drawable.board4dark));
    }
}
