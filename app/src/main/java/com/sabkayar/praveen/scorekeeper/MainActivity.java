package com.sabkayar.praveen.scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.sabkayar.praveen.scorekeeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int mScore1;
    private int mScore2;
    private ActivityMainBinding mBinding;

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            mBinding.layoutTeam1.textViewScore.setText(String.valueOf(mScore1));
            mBinding.layoutTeam2.textViewScore.setText(String.valueOf(mScore2));
        }

        mBinding.layoutTeam1.textViewLabel.setText(R.string.team_1);
        mBinding.layoutTeam2.textViewLabel.setText(R.string.team_2);
    }


    public void decreaseScore(View view) {
        // Get the ID of the button that was clicked
        if (mBinding.layoutTeam1.buttonDown.isPressed()) {
            mScore1--;
            mBinding.layoutTeam1.textViewScore.setText(String.valueOf(mScore1));
        } else {
            mScore2--;
            mBinding.layoutTeam2.textViewScore.setText(String.valueOf(mScore2));
        }
    }

    public void increaseScore(View view) {
        // Get the ID of the button that was clicked
        if (mBinding.layoutTeam1.buttonUp.isPressed()) {
            mScore1++;
            mBinding.layoutTeam1.textViewScore.setText(String.valueOf(mScore1));
        } else {
            mScore2++;
            mBinding.layoutTeam2.textViewScore.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Check if the correct item was clicked
        if(item.getItemId()==R.id.night_mode){
            // DONE: Get the night mode state of the app.
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
        // Recreate the activity for the theme change to take effect.
        recreate();
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //Save the scores
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}