package com.s090.tictactoexo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class SinglePlayerMode extends AppCompatActivity {
    int playerState = 0, human, ai; // at present at two player local mode. 0 is x and 1 is o.
    int[] boardState = {-1, -1, -1, -1, -1, -1, -1, -1, -1}; // -1 means unplayed, else stores playerState, denoting which player tapped which cell
    int playCounter = 0, winnerwinner = 0; // denotes number of tapped or played grids, winnerwinner denotes somebody has won, if winnerwinner=-1 then tie
    View myview;
    Boolean isEasy, b;
    void easyMode() {
        Random random = new Random();
        int i = random.nextInt(9);
        while (boardState[i] != -1) {
            i = random.nextInt(9);
        }
        if (boardState[i] == -1) {
            ViewGroup group = (ViewGroup) findViewById(R.id.gridLayout);
            myview = (View) group.findViewWithTag(String.valueOf(i));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drop_in(myview);
                }
            }, 1000);
        }
    }
    void hardMode()
    {
        Toast.makeText(SinglePlayerMode.this, "Coming Soon", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
    public void myDialog(String title, String msg) // method to call dialog box
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayerMode.this);
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
        if (b) button.setTextColor(Color.WHITE);
        else button.setTextColor(Color.BLACK);
    }

    public int endStateChecker(int[] boardState) {
        //all possible winning positions below
        if (boardState[0] == boardState[1] && boardState[1] == boardState[2] && boardState[0] != -1) {
            Log.i("Info", "Player " + boardState[0] + " has won.");

            winnerwinner = boardState[0];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[0] == boardState[3] && boardState[3] == boardState[6] && boardState[0] != -1) {
            Log.i("Info", "Player " + boardState[0] + " has won.");
            winnerwinner = boardState[0];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[0] == boardState[4] && boardState[4] == boardState[8] && boardState[0] != -1) {
            Log.i("Info", "Player " + boardState[0] + " has won.");

            winnerwinner = boardState[0];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[1] == boardState[4] && boardState[4] == boardState[7] && boardState[1] != -1) {
            Log.i("Info", "Player " + boardState[1] + " has won.");

            winnerwinner = boardState[1];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[2] == boardState[5] && boardState[5] == boardState[8] && boardState[2] != -1) {
            Log.i("Info", "Player " + boardState[2] + " has won.");

            winnerwinner = boardState[2];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[2] == boardState[4] && boardState[4] == boardState[6] && boardState[2] != -1) {
            Log.i("Info", "Player " + boardState[2] + " has won.");

            winnerwinner = boardState[2];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[3] == boardState[4] && boardState[4] == boardState[5] && boardState[3] != -1) {
            Log.i("Info", "Player " + boardState[3] + " has won.");

            winnerwinner = boardState[3];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        } else if (boardState[6] == boardState[7] && boardState[7] == boardState[8] && boardState[6] != -1) {
            Log.i("Info", "Player " + boardState[6] + " has won.");

            winnerwinner = boardState[6];
            myDialog("Congratulations","Player "+winnerwinner+" has won.");
        }
        if (playCounter == 9 && winnerwinner == 0) // if all grids have been tapped and nobody won, i.e. it's a draw or a tie
        {
            Log.i("Info", "Board full.");
            myDialog("It's a tie!","The board is full. Please play again.");
            winnerwinner=-1;
        }
        return winnerwinner;
    }

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
            boardState[cellState] = playerState;
            // changing grid number to record which player tapped so that nobody can change on tapping again

        }
        endStateChecker(boardState);
        if(endStateChecker(boardState)==0) {
            if (playerState == ai) {
                if(isEasy) easyMode();
                else hardMode();
            }
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
        setContentView(R.layout.activity_main);
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayerMode.this);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE: {

                        isEasy=false;
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE: {

                        isEasy=true;
                        break;
                    }
                }
            }
        };
        builder
                .setTitle("Select Game Mode")
                .setMessage("Select Game Difficulty level")
                .setPositiveButton("Hard", dialogClickListener)
                .setNegativeButton("Easy", dialogClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button Easy=alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button Hard=alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (b) {
            Easy.setTextColor(Color.WHITE);
            Hard.setTextColor(Color.WHITE);
        }
        else
        {
            Easy.setTextColor(Color.BLACK);
            Hard.setTextColor(Color.BLACK);
        }
        AlertDialog.Builder initDialogBuilder = new AlertDialog.Builder(SinglePlayerMode.this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE: {
                        playerState = 1;
                        human=playerState;
                        ai=0;
                        break;
                    }
                    case DialogInterface.BUTTON_NEGATIVE: {
                        playerState = 0;
                        human=playerState;
                        ai=1;
                        break;
                    }
                }
            }
        };
        initDialogBuilder
                .setTitle("Select your counter")
                .setPositiveButtonIcon(getDrawable(R.drawable.o))
                .setPositiveButton("", listener)
                .setNegativeButtonIcon(getDrawable(R.drawable.x))
                .setNegativeButton("", listener)
                .setMessage("Please select your counter.");
        AlertDialog initDialog = initDialogBuilder.create();
        initDialog.show();
        Button btnPositive = initDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = initDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        layoutParams.weight = 1;
        btnPositive.setLayoutParams(layoutParams);
        btnNegative.setLayoutParams(layoutParams);
        initDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // what to do if dialog box is cancelled

            @Override
            public void onCancel(DialogInterface dialog) {
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent i = new Intent();
                setResult(0, i);
                finish();
            }
        });
    }
}
