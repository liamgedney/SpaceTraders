package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Difficulty;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Universe;
import edu.gatech.spacetraders.viewmodels.GameData;

public class ChoiceScreen extends AppCompatActivity {

    Button tradeButton;
    Button travelButton;
    Button save;

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicescreen);

        tradeButton = (Button) findViewById(R.id.trade_button);
        travelButton = (Button) findViewById(R.id.travel_button);
        save = findViewById(R.id.save);

        tradeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTradeScreen();
            }
        });
        travelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTravelScreen();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {

            }
        });
    }
    public void openTradeScreen() {
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }
    public void openTravelScreen() {
        Intent intent = new Intent(this, TravelScreen.class);
        startActivity(intent);
    }

    public boolean saveBinary(File file) {
        boolean success = true;
        try {
            //ObjectOutputStream out = new ObjectOutputStream();
        } finally {

        }
    }

}
