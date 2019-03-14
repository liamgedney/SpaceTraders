package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Difficulty;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.entity.Universe;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

public class MainActivity extends AppCompatActivity {

    private boolean isAnInteger(String s) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    String nameStr;
    Difficulty diffSpinText;
    int pil;
    int fight;
    int trade;
    int engr;
    GameData gameData;
    GameDataInstanceGetter gameDataInstanceGetter = new GameDataInstanceGetter();

    EditText playerName;
    EditText pilotSkill;
    EditText fighterSkill;
    EditText traderSkill;
    EditText engrSkill;

    Button createButton;
    Button exitButton;
    Spinner diffSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName = (EditText) findViewById(R.id.player_name_input);
        pilotSkill = (EditText) findViewById(R.id.pilot_skill_input);
        fighterSkill = (EditText) findViewById(R.id.fighter_skill_input);
        traderSkill = (EditText) findViewById(R.id.trader_skill_input);
        engrSkill = (EditText) findViewById(R.id.engr_skill_input);

        diffSpinner = (Spinner) findViewById(R.id.difficulty_spinner);
        diffSpinner.setAdapter(new ArrayAdapter<Difficulty>(this, android.R.layout.simple_spinner_item, Difficulty.values()));
        createButton = (Button) findViewById(R.id.create_button);
        exitButton = (Button) findViewById(R.id.exit_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                if (playerName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                } else if (!isAnInteger(pilotSkill.getText().toString())
                            || !isAnInteger(fighterSkill.getText().toString())
                            || !isAnInteger(traderSkill.getText().toString())
                            || !isAnInteger(engrSkill.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Points must be non-negative integers.", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(pilotSkill.getText().toString()) < 0
                            || Integer.valueOf(fighterSkill.getText().toString()) < 0
                            || Integer.valueOf(traderSkill.getText().toString()) < 0
                            || Integer.valueOf(engrSkill.getText().toString()) < 0) {
                    Toast.makeText(MainActivity.this, "Points must be non-negative integers.", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(pilotSkill.getText().toString()) + Integer.valueOf(fighterSkill.getText().toString())
                        + Integer.valueOf(traderSkill.getText().toString()) + Integer.valueOf(engrSkill.getText().toString()) != 16) {
                    Toast.makeText(MainActivity.this, "You must have a total of 16 skill points.", Toast.LENGTH_SHORT).show();
                } else {
                    nameStr = playerName.getText().toString();
                    pil = Integer.valueOf(pilotSkill.getText().toString());
                    fight = Integer.valueOf(fighterSkill.getText().toString());
                    trade = Integer.valueOf(traderSkill.getText().toString());
                    engr = Integer.valueOf(engrSkill.getText().toString());
                    diffSpinText = (Difficulty) diffSpinner.getSelectedItem();

                    gameData = new GameData();

                    Player player = new Player(nameStr, pil, fight, trade, engr, diffSpinText);
                    gameData.setPlayer(player);
                    System.out.println(player);

                    Universe universe = new Universe();
                    gameData.setUniverse(universe);
                    System.out.println(universe.toString());

                    SolarSystem startingSystem = universe.getSystems()[randomWithRange(0, 9)];
                    gameData.setCurrentSolarSystem(startingSystem);

                    gameDataInstanceGetter.newGameData(gameData);

                    openChoiceScreen();
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                finish();
                System.exit(0);
            }
        });
    }
    public void openLoadingScreen() {
        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);
    }

    public void openChoiceScreen() {
        Intent intent = new Intent(this, ChoiceScreen.class);
        startActivity(intent);
    }
}