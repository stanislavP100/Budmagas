package com.example.android.sunshine.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.sunshine.Activity.SecondActivity.CementActivity;
import com.example.android.sunshine.Activity.SecondActivity.SatengipsActivity;
import com.example.android.sunshine.R;

public class BudivelniSumishActivity extends AppCompatActivity {

    private Button cement;
    private Button satengips;
    private Button kley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budivelni_sumish);

        cement=findViewById(R.id.cement_button_id);
        satengips=findViewById(R.id.satengips_button_id);
        kley=findViewById(R.id.kley_button_id);

        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), CementActivity.class);
                startActivity(intent);
            }
        });

        satengips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), SatengipsActivity.class);
                startActivity(intent);
            }
        });

        kley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), SatengipsActivity.class);
                startActivity(intent);
            }
        });
    }
}
