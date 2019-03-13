package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Difficulty;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Universe;
import edu.gatech.spacetraders.viewmodels.GameData;

public class ChoiceScreen extends AppCompatActivity {

    Button tradeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choicescreen);

        tradeButton = (Button) findViewById(R.id.trade_button);

        tradeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTradeScreen();
            }
        });
    }
    public void openTradeScreen() {
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }

}
