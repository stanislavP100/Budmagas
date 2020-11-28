/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.sunshine.utilities.Adapter;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private Adapter mAdapter;
    private RecyclerView mNumbersList;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page); //activity_main);

        cardView= findViewById(R.id.first_page_card_view_1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), ChildActivity.class);
                startActivity(intent);
            }
        });

      //  loadWeatherData(); //на first page не праює, до по цепочці все далі грузить а вигружать нема куди (мабуть)
    }



//
//        private void loadWeatherData() {
//
//        new WeaterQueryTask().execute("https://chitadrita.herokuapp.com/");
//
//        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



//    public void onClick() {
//        Context context = this;
//
//        Intent intent=new Intent(context,ChildActivity.class);
//
//        startActivity(intent);
//        // TODO (1) Create a new Activity called DetailActivity using Android Studio's wizard
//        // TODO (2) Change the root layout of activity_detail.xml to a FrameLayout and remove unnecessary xml attributes
//        // TODO (3) Remove the Toast and launch the DetailActivity using an explicit Intent
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idItemThetWasSelected=item.getItemId();

//        if(idItemThetWasSelected==R.id.refresh_button)
//        {
//        loadWeatherData();
//        }

        return super.onOptionsItemSelected(item);
    }



//
//    public class WeaterQueryTask extends AsyncTask <String, Void, String[]> {
//
//
//        @Override
//        protected String[] doInBackground(String... params) {
//            /* If there's no zip code, there's nothing to look up. */
//            if (params.length == 0) {
//                return null;
//            }
//
//
//           URL weatherRequestUrl = NetworkUtils.buildUrl();
//
//            try {
//                String jsonWeatherResponse = NetworkUtils
//                       .getResponseFromHttpUrl(weatherRequestUrl);
//
//               String[] simpleJsonWeatherData= OpenWeatherJsonUtils
//                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
//
//
//                return simpleJsonWeatherData;
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//                return null;
//            }
//
//        }
//
//
//
//        @Override
//        protected void onPostExecute(String[] strings) {
//
//            mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);
//
//            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//            mNumbersList.setLayoutManager(layoutManager);
//
//            mAdapter = new Adapter(MainActivity.this,strings);
//            mNumbersList.setAdapter(mAdapter);
//
//
//
//        }
//    }



}
