package edu.gatech.spacetraders.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.spacetraders.R;
import edu.gatech.spacetraders.entity.Player;

public class MainActivity extends AppCompatActivity {

    String nameStr;
    String diffSpinText;
    int pil;
    int fight;
    int trade;
    int engr;

    EditText playerName;
    EditText pilotSkill;
    EditText fighterSkill;
    EditText traderSkill;
    EditText engrSkill;

    Button createButton;
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
        createButton = (Button) findViewById(R.id.create_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {
                nameStr = playerName.getText().toString();
                pil = Integer.valueOf(pilotSkill.getText().toString());
                fight = Integer.valueOf(fighterSkill.getText().toString());
                trade = Integer.valueOf(traderSkill.getText().toString());
                engr = Integer.valueOf(engrSkill.getText().toString());
                diffSpinText = diffSpinner.getSelectedItem().toString();
                int totalSkill = pil + fight + trade + engr;
                if (totalSkill > 16) {
                    Toast.makeText(MainActivity.this, "Must have less than 16 skill points", Toast.LENGTH_SHORT).show();
                } else {
                    Player ourPlayer = new Player(nameStr, pil, fight, trade, engr, diffSpinText);
                }



            }
        });
    }
}