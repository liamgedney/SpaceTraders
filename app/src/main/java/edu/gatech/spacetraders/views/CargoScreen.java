package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.EnumMap;
import java.util.List;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.CargoRecyclerViewAdapter;
import edu.gatech.spacetraders.entity.Good;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

/**
 * class for the CargoScreen
 */
public class CargoScreen extends AppCompatActivity {

    private EditText buyCode;
    private CargoRecyclerViewAdapter adapter;

    private final GameData gameData = GameDataInstanceGetter.getGameData();

    private final Player player = gameData.getPlayer();
    private final SolarSystem currSS = gameData.getCurrentSolarSystem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> recycleViewList = player.makeList(currSS.getMarket());
        setContentView(R.layout.activity_cargo);
        //                                                                                 DEBUGGING
//        System.out.println(gameData.getPlayer().getShip().getCargoHold().get(Good.WATER));

        Button buyButton = findViewById(R.id.cargobuy_button);
        Button sellButton = findViewById(R.id.cargosell_button);
        Button backButton = findViewById(R.id.cargoback_button);
        buyCode = findViewById(R.id.cargo_position);
        final RecyclerView recyclerView = findViewById(R.id.cargogoods_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Market market = currSS.getMarket();
        currSS.setMarket(market);
        player.updateList(market);
        gameData.setPlayer(player);
        gameData.setCurrentSolarSystem(currSS);

        adapter = new CargoRecyclerViewAdapter(this, recycleViewList);
        adapter.setList(player.getList());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                adapter.notifyDataSetChanged();
                openTradeScreen();
                adapter.notifyDataSetChanged();
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                int bCode = Integer.valueOf(buyCode.getText().toString());
                Market market = currSS.getMarket();
                EnumMap<Good, Integer> prices = market.getPrices();
                if (player.canSell(bCode)) {
                    player.sell(bCode);
                    player.upCredits(prices, bCode);
                    market.upInventory(bCode, 1);
                    currSS.setMarket(market);
                    player.updateList(market);
                    gameData.setPlayer(player);
                    gameData.setCurrentSolarSystem(currSS);
                    adapter.setList(player.getList());
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

    private void openTradeScreen() {
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }

}
