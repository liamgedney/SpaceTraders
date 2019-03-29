package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
import edu.gatech.spacetraders.entity.Travel;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;


public class TravelScreen extends AppCompatActivity {

    Button travelButton;
    Button backButton;
    EditText planetCode;
    TravelRecyclerViewAdapter adapter;
    TextView currentPlanet;
    TextView currentFuel;

    GameData gameData = GameDataInstanceGetter.getGameData();

    Player player = gameData.getPlayer();
    SolarSystem currSS = gameData.getCurrentSolarSystem();
    int techLevel = gameData.getCurrentSolarSystem().getTechLvl();
    Ship ship = player.getShip();
    Travel travel = new Travel(gameData);
    private List<String> recycleViewList = travel.getInRangeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (String s: travel.getInRangeList()) {
            System.out.println(s);
        }
        setContentView(R.layout.activity_travel);
        System.out.println(gameData.getPlayer().getShip().getCargoHold().get(Good.WATER));

        travelButton = (Button) findViewById(R.id.travel_button);
        backButton = (Button) findViewById(R.id.travelback_button);
        planetCode = (EditText) findViewById(R.id.travel_position);
        currentPlanet = (TextView) findViewById(R.id.currentplanetstats);
        currentFuel = (TextView) findViewById(R.id.current_fuellevel);
        currentPlanet.setText(currSS.toString());
        String fuel = "Current Fuel: " + ship.getCurFuel();
        currentFuel.setText(fuel);

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


        adapter = new TravelRecyclerViewAdapter(this, recycleViewList);
        recyclerView.setAdapter(adapter);
        //needs to update current planet and replace recyclerview index with the planet left
        travelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                int tcode = Integer.valueOf(planetCode.getText().toString());
                if (tcode >= 0 && tcode < travel.getInRangeList().size()) {
                    gameData = travel.travel(tcode);
                    travel = new Travel(gameData);
                    recycleViewList = travel.getInRangeList();
                    adapter.setList(recycleViewList);
                    recyclerView.setAdapter(adapter);
                    System.out.println(gameData.getCurrentSolarSystem().toString());
                    System.out.println(gameData.getPlayer().getShip().getCurFuel());
                    //TextView currentFuel = (TextView) findViewById(R.id.current_fuellevel);
                    //TextView currentPlanet = (TextView) findViewById(R.id.currentplanetstats);
                    currentPlanet.setText(gameData.getCurrentSolarSystem().toString());
                    currentFuel.setText("Current Fuel: " + gameData.getPlayer().getShip().getCurFuel());
                } else {
                    Toast.makeText(TravelScreen.this, "Please select a valid planet.", Toast.LENGTH_SHORT).show();
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

