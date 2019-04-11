package edu.gatech.spacetraders.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Difficulty;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.entity.Universe;
import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

/**
 * The MainActivity does everything
 */
public class MainActivity extends AppCompatActivity {

    private boolean isNotInteger(String s) {
        try {
            //int num = Integer.parseInt(s);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * gets the random with range
     * @return int random number in range
     */
    private int randomWithRange()
    {
        int range = (9) + 1;
        return (int) (Math.random() * range);
    }

    private String nameStr;
    private Difficulty diffSpinText;
    private int pil;
    private int fight;
    private int trade;
    private int engr;
    private GameData gameData;
    private final GameDataInstanceGetter gameDataInstanceGetter = new GameDataInstanceGetter();

    private EditText playerName;
    private EditText pilotSkill;
    private EditText fighterSkill;
    private EditText traderSkill;
    private EditText engrSkill;

    private MediaPlayer space;

    private Spinner diffSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName =  findViewById(R.id.player_name_input);
        pilotSkill =  findViewById(R.id.pilot_skill_input);
        fighterSkill =  findViewById(R.id.fighter_skill_input);
        traderSkill =  findViewById(R.id.trader_skill_input);
        engrSkill = findViewById(R.id.engr_skill_input);

        diffSpinner =  findViewById(R.id.difficulty_spinner);
        diffSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values()));
        Button createButton = findViewById(R.id.create_button);
        Button exitButton = findViewById(R.id.exit_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                int LENGTHOFTHINGS = 16;
                Editable txt = playerName.getText();
                Editable ptxt = pilotSkill.getText();
                Editable ftxt = fighterSkill.getText();
                Editable ttxt = traderSkill.getText();
                Editable etxt = engrSkill.getText();

                if ("".equals(txt.toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a name.",
                            Toast.LENGTH_SHORT).show();
                } else if (isNotInteger(ptxt.toString())
                            || isNotInteger(ftxt.toString())
                            || isNotInteger(ttxt.toString())
                            || isNotInteger(etxt.toString())) {
                    Toast.makeText(MainActivity.this,
                            "Points must be non-negative integers.",
                            Toast.LENGTH_SHORT).show();
                } else if ((Integer.valueOf(ptxt.toString()) < 0)
                        || (Integer.valueOf(ftxt.toString()) < 0)
                        || (Integer.valueOf(ttxt.toString()) < 0)
                        || (Integer.valueOf(etxt.toString()) < 0)) {
                    Toast.makeText(MainActivity.this,
                            "Points must be non-negative integers.",
                            Toast.LENGTH_SHORT).show();
                } else if ((Integer.valueOf(ptxt.toString())
                        + Integer.valueOf(ftxt.toString())
                        + Integer.valueOf(ttxt.toString())
                        + Integer.valueOf(etxt.toString()))
                        != LENGTHOFTHINGS) {
                    Toast.makeText(MainActivity.this,
                            "You must have a total of 16 skill points.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    nameStr = playerName.getText().toString();
                    pil = Integer.valueOf(ptxt.toString());
                    fight = Integer.valueOf(ftxt.toString());
                    trade = Integer.valueOf(ttxt.toString());
                    engr = Integer.valueOf(etxt.toString());
                    diffSpinText = (Difficulty) diffSpinner.getSelectedItem();

                    gameData = new GameData();
                    int[] pointsArr = new int[4];
                    pointsArr[0] = pil;
                    pointsArr[1] = fight;
                    pointsArr[2] = trade;
                    pointsArr[3] = engr;
                    Player player = new Player(nameStr, pointsArr, diffSpinText);
                    gameData.setPlayer(player);
                    //                                                                     DEBUGGING
//                    System.out.println(player);

                    Universe universe = new Universe(player, player.getShip());
                    gameData.setUniverse(universe);
                    //                                                                     DEBUGGING
//                    System.out.println(universe.toString());

                    SolarSystem startingSystem = universe.getSystems()[randomWithRange()];
                    gameData.setCurrentSolarSystem(startingSystem);

                    gameDataInstanceGetter.newGameData(gameData);
                    space = MediaPlayer.create(MainActivity.this, R.raw.space);
                    space.start();
                    openChoiceScreen();
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View activity_main) {
                finish();
                System.exit(0);
            }
        });
    }

    /**
     * opens the Loading Screen
     */
    /*public void openLoadingScreen() {
        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);
    }*/

    private void openChoiceScreen() {
        Intent intent = new Intent(this, ChoiceScreen.class);
        startActivity(intent);
    }

   /*
    public void onCreate(View view) {
    }*/
}