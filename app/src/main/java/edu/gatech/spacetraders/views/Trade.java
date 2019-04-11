package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.List;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.MarketplaceRecyclerViewAdapter;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

/**
 * Screen for trading
 */
public class Trade extends AppCompatActivity {

    private EditText buyCode;
    private MarketplaceRecyclerViewAdapter adapter;

    private GameData gameData = GameDataInstanceGetter.getGameData();

    private final Player player = gameData.getPlayer();
    private final SolarSystem currSS = gameData.getCurrentSolarSystem();
    Ship ship = player.getShip();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace);

        gameData = GameDataInstanceGetter.getGameData();

        Button buyButton = findViewById(R.id.mktbuy_button);
        Button sellButton = findViewById(R.id.mktsell_button);
        Button backButton = findViewById(R.id.mktback_button);
        buyCode =  findViewById(R.id.mktplace_position);
        final RecyclerView recyclerView = findViewById(R.id.mktgoods_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Market mkt = currSS.getMarket();
        mkt.updateList();
        currSS.setMarket(mkt);
        adapter = new MarketplaceRecyclerViewAdapter(this, mkt.getList());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                int bCode;
                Editable txt = buyCode.getText();
                bCode = Integer.valueOf(txt.toString());
                Market market = currSS.getMarket();
                if (market.canBuy(bCode)) {
                    market.buy(bCode);
                    market.updateList();
                    currSS.setMarket(market);
                    gameData.setCurrentSolarSystem(currSS);
                    gameData.setPlayer(market.updatePlayer(bCode));
                    adapter.setList(market.getList());
                    recyclerView.setAdapter(adapter);
                }
                //                                                                         DEBUGGING
//                System.out.println("CURCARGO: " + gameData.getPlayer().getShip().getCurCargo());
//                System.out.println("MAXCARGO: " + gameData.getPlayer().getShip().getMaxCargo());
//                System.out.println("CURRENT CREDITS: " + gameData.getPlayer().getCredits());
//                for (String s: market.getList()) {
//                    System.out.println(s);
//                }
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                openCargoScreen();
                adapter.notifyDataSetChanged();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {

                openChoiceScreen();
            }
        });
    }

    private void openChoiceScreen() {
        Intent intent = new Intent(this, ChoiceScreen.class);
        startActivity(intent);
    }

    private void openCargoScreen() {
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, CargoScreen.class);
        startActivity(intent);
    }

}
