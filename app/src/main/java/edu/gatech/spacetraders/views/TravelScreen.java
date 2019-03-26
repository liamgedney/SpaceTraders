package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


public class TravelScreen extends AppCompatActivity {

    Button travelButton;
    Button backButton;
    EditText planetCode;
    MarketplaceRecyclerViewAdapter adapter;

    GameData gameData = GameDataInstanceGetter.getGameData();

    Player player = gameData.getPlayer();
    SolarSystem currSS = gameData.getCurrentSolarSystem();
    int techLevel = gameData.getCurrentSolarSystem().getTechLvl();
    Ship ship = player.getShip();
    private List<String> recycleViewList = new ArrayList<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        System.out.println(gameData.getPlayer().getShip().getCargoHold().get(Good.WATER));

        travelButton = (Button) findViewById(R.id.travel_button);
        backButton = (Button) findViewById(R.id.travelback_button);
        planetCode = (EditText) findViewById(R.id.travel_position);

        final RecyclerView recyclerView = findViewById(R.id.planets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int count = 0;
//        for (SolarSystem planet: planets) {
////            String newString = count + " ";
////            newString += String.format("%1$11s", good.toString());
////            newString += String.format("%1$5s", "$" + market.getPrice(good));
////            newString += String.format("%1$5s", "" + ship.getCargoHold().get(good).toString());
////            recycleViewList.add(newString);
////            count++;
////        }


        adapter = new MarketplaceRecyclerViewAdapter(this, recycleViewList);
        recyclerView.setAdapter(adapter);
        //needs to update current planet and replace recyclerview index with the planet left
        travelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                openTravelScreen();
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

    public void openTravelScreen() {
        Intent intent = new Intent(this, TravelScreen.class);
        startActivity(intent);
    }
//needs to be edited to updateView of recycler and current planet
//    public List<String> updateView(Market market, Player player) {
//        int count = 0;
//        List<String> list = new ArrayList<>(10);
//        ship = player.getShip();
//        for (Good good: Good.values()) {
//            String newString = count + " ";
//            newString += String.format("%1$11s", good.toString());
//            newString += String.format("%1$5s", "$" + market.getPrice(good));
//            newString += String.format("%1$5s", "" + ship.getCargoHold().get(good).toString());
//            list.add(newString);
//            count++;
//        }
//        return list;
//    }
}
