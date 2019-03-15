package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.MarketplaceRecyclerViewAdapter;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

public class Trade extends AppCompatActivity {

    Button buyButton;
    Button sellButton;
    Button backButton;
    EditText buyCode;
    MarketplaceRecyclerViewAdapter adapter;

    GameData gameData = GameDataInstanceGetter.getGameData();
    Player player = gameData.getPlayer();
    SolarSystem currSS = gameData.getCurrentSolarSystem();
    int techLevel = currSS.getTechLvl();
    Ship ship = player.getShip();



    Market market = new Market(techLevel, player, ship);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        buyButton = (Button) findViewById(R.id.mktbuy_button);
        sellButton = (Button) findViewById(R.id.mktsell_button);
        backButton = (Button) findViewById(R.id.mktback_button);
        buyCode = (EditText) findViewById(R.id.mktplace_position);
        final RecyclerView recyclerView = findViewById(R.id.mktgoods_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MarketplaceRecyclerViewAdapter(this, currSS.getMarket().getList());
        recyclerView.setAdapter(adapter);

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                int bCode;
                bCode = Integer.valueOf(buyCode.getText().toString());
                Market market = currSS.getMarket();
                market.buy(bCode);
                market.updateList();
                currSS.setMarket(market);
                gameData.setCurrentSolarSystem(currSS);
                gameData.setPlayer(market.updatePlayer(bCode));
                adapter.setList(market.getList());
                recyclerView.setAdapter(adapter);
                System.out.println("CURCARGO: " + gameData.getPlayer().getShip().getCurCargo());
                System.out.println("MAXCARGO: " + gameData.getPlayer().getShip().getMaxCargo());
                System.out.println("CURRENT CREDITS: " + gameData.getPlayer().getCredits());
                for (String s: market.getList()) {
                    System.out.println(s);
                }
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
