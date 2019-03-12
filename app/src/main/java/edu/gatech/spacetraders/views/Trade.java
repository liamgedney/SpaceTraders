package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.MarketplaceRecyclerViewAdapter;

public class Trade extends AppCompatActivity {

    Button buyButton;
    Button sellButton;
    Button backButton;
    Market myMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        buyButton = (Button) findViewById(R.id.mktbuy_button);
        sellButton = (Button) findViewById(R.id.mktsell_button);
        backButton = (Button) findViewById(R.id.mktback_button);
        //myMarket = new Market(0, )
        //MarketplaceRecyclerViewAdapter recyclerView = new MarketplaceRecyclerViewAdapter(R.id.mktgoods_list, myMarket.getList());

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {

            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openCargoScreen();
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

    public void openCargoScreen() {
        Intent intent = new Intent(this, CargoScreen.class);
        startActivity(intent);
    }
}
