package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

public class ChoiceScreen extends AppCompatActivity {

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicescreen);


        Button tradeButton = findViewById(R.id.trade_button);
        Button travelButton = findViewById(R.id.travel_button);
        Button save = findViewById(R.id.save);
        Button newGame = findViewById(R.id.newGame);
        Button loadGame = findViewById(R.id.loadGame);

        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                openTradeScreen();
            }
        });

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                openTravelScreen();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                File file = new File(getFilesDir(), "data2.bin");
                try{
                    file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                GameDataInstanceGetter.saveBinary(file);
            }
        });
        loadGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                File file = new File(getFilesDir(), "data2.bin");
                if(file.exists()) {
                    GameDataInstanceGetter.loadBinary(file);
                }
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
//                File file = new File(getFilesDir(), "data3.bin");
//                try{
//                    file.createNewFile();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//                GameDataInstanceGetter.newBinary(file);
                openMainScreen();
            }
        });
    }
    private void openTradeScreen() {
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }
    private void openTravelScreen() {
        Intent intent = new Intent(this, TravelScreen.class);
        startActivity(intent);
    }
    private void openMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
