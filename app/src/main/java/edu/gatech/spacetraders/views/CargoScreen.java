package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.CargoRecyclerViewAdapter;
import edu.gatech.spacetraders.entity.Good;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.MarketplaceRecyclerViewAdapter;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

public class CargoScreen extends AppCompatActivity {

    Button buyButton;
    Button sellButton;
    Button backButton;
    EditText buyCode;
    CargoRecyclerViewAdapter adapter;

    GameData gameData = GameDataInstanceGetter.getGameData();

    Player player = gameData.getPlayer();
    SolarSystem currSS = gameData.getCurrentSolarSystem();
    Ship ship = player.getShip();

    private List<String> recycleViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycleViewList = player.makeList(currSS.getMarket());
        setContentView(R.layout.activity_cargo);
        System.out.println(gameData.getPlayer().getShip().getCargoHold().get(Good.WATER));

        buyButton = (Button) findViewById(R.id.cargobuy_button);
        sellButton = (Button) findViewById(R.id.cargosell_button);
        backButton = (Button) findViewById(R.id.cargoback_button);
        buyCode = (EditText) findViewById(R.id.cargo_position);
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
            public void onClick(View activity_main) {
                adapter.notifyDataSetChanged();
                openTradeScreen();
                adapter.notifyDataSetChanged();
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
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
                System.out.println("CURCARGO: " + gameData.getPlayer().getShip().getCurCargo());
                System.out.println("MAXCARGO: " + gameData.getPlayer().getShip().getMaxCargo());
                System.out.println("CURRENT CREDITS: " + gameData.getPlayer().getCredits());
                for (String s: market.getList()) {
                    System.out.println(s);
                }
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
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }

}
