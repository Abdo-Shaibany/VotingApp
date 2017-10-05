package com.earchcor.www.votingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileAct extends AppCompatActivity {

    TextView tvWel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvWel = (TextView) findViewById(R.id.tvWelcome);
        tvWel.setText("Welcome " + MyDB.username);

    }

    public void buttonClicked(View view){

        if(view.getId() == R.id.toPassAct){
            Intent intent = new Intent(this , ChangePasswordAct.class);
            startActivity(intent);
        } else if(view.getId() == R.id.show_rates){
            Intent intent = new Intent(this , VotingRate.class);
            startActivity(intent);
        } else if(view.getId() == R.id.Result_button){
            Intent intent = new Intent(this , ResultAct.class);
            startActivity(intent);
        }
    }
}
