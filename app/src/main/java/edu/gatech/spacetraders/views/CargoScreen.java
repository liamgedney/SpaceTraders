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
    MarketplaceRecyclerViewAdapter adapter;

    GameData gameData = GameDataInstanceGetter.getGameData();
    Player player = gameData.getPlayer();
    int techLevel = gameData.getCurrentSolarSystem().getTechLvl();
    Ship ship = player.getShip();

    Market market = new Market(techLevel, player, ship);
    List<Good> goodsList = market.getGoods();
    private List<String> recycleViewList = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);

        buyButton = (Button) findViewById(R.id.cargobuy_button);
        sellButton = (Button) findViewById(R.id.cargosell_button);
        backButton = (Button) findViewById(R.id.cargoback_button);
        buyCode = (EditText) findViewById(R.id.mktplace_position);

        RecyclerView recyclerView = findViewById(R.id.cargogoods_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int count = 0;
        for (Good good: goodsList) {
            String newString = count + " ";
            newString += String.format("%1$11s", good.toString());
            newString += String.format("%1$5s", "$" + market.getPrice(good));
            newString += String.format("%1$5s", "" + ship.getCargoHold().get(good).toString());
            recycleViewList.add(newString);
            count++;
        }


        adapter = new MarketplaceRecyclerViewAdapter(this, recycleViewList);
        recyclerView.setAdapter(adapter);

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTradeScreen();
                int bCode;
                bCode = Integer.valueOf(buyCode.getText().toString());
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                int bCode = Integer.valueOf(buyCode.getText().toString());
                Player player = gameData.getPlayer();
                SolarSystem currSS = gameData.getCurrentSolarSystem();
                Market market = currSS.getMarket();
                EnumMap<Good, Integer> prices = market.getPrices();
                player.sell(bCode);
                player.upCredits(prices, bCode);
                market = player.updateMarket(market, bCode);
                gameData.setPlayer(player);
                currSS.setMarket(market);
                gameData.setCurrentSolarSystem(currSS);
                updateView(market, player);
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

    public void updateView(Market market, Player player) {
        int count = 0;
        List<String> list = new ArrayList<>(10);
        ship = player.getShip();
        for (Good good: Good.values()) {
            String newString = count + " ";
            newString += String.format("%1$11s", good.toString());
            newString += String.format("%1$5s", "$" + market.getPrice(good));
            newString += String.format("%1$5s", "" + ship.getCargoHold().get(good).toString());
            list.add(newString);
            count++;
        }
    }
}
