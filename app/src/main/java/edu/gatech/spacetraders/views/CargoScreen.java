package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.spacetraders.R;

public class CargoScreen extends AppCompatActivity {

    Button buyButton;
    Button sellButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);

        buyButton = (Button) findViewById(R.id.mktbuy_button);
        sellButton = (Button) findViewById(R.id.mktsell_button);
        backButton = (Button) findViewById(R.id.mktback_button);

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTradeScreen();
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                //fjionafio
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openChoiceScreen();
            }
        });
    }

    public void openChoiceScreen() {
        Intent intent = new Intent(this, ChoiceScreen.class);
        startActivity(intent);
    }

    public void openTradeScreen() {
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }
}
