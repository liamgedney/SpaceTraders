package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Good;
import edu.gatech.spacetraders.entity.Market;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.entity.Travel;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;


/**
 * Screen for traveling
 */
public class TravelScreen extends AppCompatActivity {

    private EditText planetCode;
    private TravelRecyclerViewAdapter adapter;
    private TextView currentPlanet;
    private TextView currentFuel;

    private GameData gameData = GameDataInstanceGetter.getGameData();

    private final Player player = gameData.getPlayer();
    private final SolarSystem currSS = gameData.getCurrentSolarSystem();
    private final Ship ship = Objects.requireNonNull(player).getShip();
    private Travel travel = new Travel(gameData);
    private List<String> recycleViewList = travel.getInRangeList();
    private final EnumMap<Good, Integer> cargoHold = ship.getCargoHold();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (String s: travel.getInRangeList()) {
            Log.d("", s);
        }
        travel = new Travel(gameData);
        setContentView(R.layout.activity_travel);
        Log.d("", "" + cargoHold.get(Good.WATER));
        Button travelButton = findViewById(R.id.travel_button);
        Button backButton = findViewById(R.id.travelback_button);
        planetCode = findViewById(R.id.travel_position);
        currentPlanet = findViewById(R.id.currentplanetstats);
        currentFuel = findViewById(R.id.current_fuellevel);
        currentPlanet.setText(Objects.requireNonNull(currSS).toString());
        String fuel = "Current Fuel: " + ship.getCurFuel();
        currentFuel.setText(fuel);

        final RecyclerView recyclerView = findViewById(R.id.planets_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        int count = 0;
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
            @Override
            public void onClick(View activity_main) {
                openLoadingScreen();
                Log.d("", currSS.toString());
                Market mkt = currSS.getMarket();
                Log.d("", mkt.toString());
                Editable ttxt = planetCode.getText();
                int tcode = Integer.valueOf(ttxt.toString());
                if ((tcode >= 0) && (tcode < travel.getInRangeList().size())) {
                    gameData = travel.travel(tcode);
                    travel = new Travel(gameData);
                    recycleViewList = travel.getInRangeList();
                    adapter.setList(recycleViewList);
                    recyclerView.setAdapter(adapter);
                    Log.d("", Objects.requireNonNull(gameData.getCurrentSolarSystem()).toString());
//                    Log.d("", gameData.getPlayer().getShip().getCurFuel().toString());
                    //TextView currentFuel = (TextView) findViewById(R.id.current_fuellevel);
                    //TextView currentPlanet = (TextView) findViewById(R.id.currentplanetstats);
                    currentPlanet.setText(gameData.getCurrentSolarSystem().toString());
                    currentFuel.setText("Current Fuel: "
                            + Objects.requireNonNull(gameData.getPlayer()).getShip().getCurFuel());
                    int currTechLvl = currSS.getTechLvl();
                    currSS.setMarket(new Market(currTechLvl, player,
                            Objects.requireNonNull(player).getShip()));
                    gameData.setCurrentSolarSystem(currSS);
                    String display = travel.randomEvent();
                    Toast.makeText(TravelScreen.this, display, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TravelScreen.this, "Please select a valid planet.",
                            Toast.LENGTH_SHORT).show();
                }
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

    private void openLoadingScreen() {
        Intent intent = new Intent(this, Loading.class);
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

