package com.example.dell_pc.scarnedicegame;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnRoll,btnHold,btnReset;
    TextView txtC,txtU,txtLable;
    ImageView imageView;
    int User_Overall=0,User_Turn=0,Computer_Overall=0,Computer_Turn=0;
    boolean turnFlag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHold=(Button)findViewById(R.id.btnHold);
        btnReset=(Button)findViewById(R.id.btnReset);
        btnRoll=(Button)findViewById(R.id.btnRoll);
        imageView=(ImageView)findViewById(R.id.imgView);
        btnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        HoldMethod();
            }
        });
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RollMethod();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetMethod();
            }
        });
        txtC=(TextView)findViewById(R.id.lblCScore);
        txtU=(TextView)findViewById(R.id.lblmyScore);
        txtLable=(TextView)findViewById(R.id.txtTurnScore);

    }
    public void HoldMethod(){
            if(turnFlag)
            {
                    Computer_Overall=Computer_Overall+Computer_Turn;
                txtC.setText(String.valueOf(Computer_Overall));
                turnFlag=false;
            }
        else
            {
                User_Overall=User_Turn+User_Overall;
                txtU.setText(String.valueOf(User_Overall));
                turnFlag=true;
                ComputerTurn();
            }
    }
    public void ResetMethod()
    {
        User_Overall=0;User_Turn=0;Computer_Overall=0;Computer_Turn=0;
        Toast.makeText(getApplicationContext(),"RESET",Toast.LENGTH_SHORT);

    }
    public void RollMethod() {

        int min = 1;
        int max = 6;
        int RandomSave;
        Random random = new Random();
        RandomSave = random.nextInt(max - min + 1) + min;
        System.out.println("value:******" + RandomSave);
        switch (RandomSave) {
            case 1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1, null));
                //imageView.setImageResource(R.drawable.dice1);
                Dice1();
                break;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2, null));
                //imageView.setImageResource(R.drawable.dice2);
                AddDice(2);
                break;
            case 3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3, null));
                //imageView.setImageResource(R.drawable.dice3);
                AddDice(3);
                break;
            case 4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4, null));
                // imageView.setImageResource(R.drawable.dice4);
                AddDice(4);
                break;
            case 5:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5, null));
                //imageView.setImageResource(R.drawable.dice5);
                AddDice(5);
                break;
            case 6:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6, null));
                //imageView.setImageResource(R.drawable.dice6);
                AddDice(6);
                break;

        }

        /*if(Computer_Overall>100)
        {
            Toast.makeText(getApplicationContext(),"Computer Won"+String.valueOf(Computer_Overall),Toast.LENGTH_LONG).show();
            txtLable.setText(String.valueOf(""));
            ResetMethod();
        }
        if(User_Overall>100)

            Toast.makeText(getApplicationContext(),"Userer Won:"+String.valueOf(User_Overall),Toast.LENGTH_LONG).show();
            txtLable.setText(String.valueOf(""));
            ResetMethod();
        }*/

    }
public void  AddDice(int x)
        {
                if(turnFlag)
                {
                    Computer_Turn=Computer_Turn+x;

                    Toast.makeText(getApplicationContext(),String.valueOf("Computer turn Score:"+Computer_Turn),Toast.LENGTH_SHORT).show();
                }else{
                    User_Turn=User_Turn+x;
                    Toast.makeText(getApplicationContext(),String.valueOf("User turn Score:"+User_Turn),Toast.LENGTH_SHORT).show();

                }
        }
public void ComputerTurn() {

    btnHold.setEnabled(false);
    btnReset.setEnabled(false);
    btnRoll.setEnabled(false);
    RollMethod();
        if (Computer_Turn > 19 && turnFlag) {
            btnHold.setEnabled(true);
            btnReset.setEnabled(true);
            btnRoll.setEnabled(true);
            Computer_Overall=Computer_Overall+Computer_Turn;
            txtC.setText(String.valueOf(Computer_Overall));
            turnFlag=false;

        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ComputerTurn();
                }
            }, 2000);
        }
    btnHold.setEnabled(true);
    btnReset.setEnabled(true);
    btnRoll.setEnabled(true);
}

public void Dice1(){
        if(turnFlag)
        {
            Computer_Turn=0;
            txtC.setText(String.valueOf(Computer_Overall));
            turnFlag=false;
        }
    else{
            User_Turn=0;
            txtU.setText(String.valueOf(User_Overall));
            turnFlag=true;
        }

}


}
