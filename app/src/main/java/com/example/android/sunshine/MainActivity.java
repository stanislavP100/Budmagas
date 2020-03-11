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
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        loadWeatherData();

        try{

            NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());}
        catch (Exception ex){}


    }
        private void loadWeatherData() {


           // String location = SunshinePreferences.getPreferredWeatherLocation(this);
           new WeaterQueryTask().execute("https://chitadrita.herokuapp.com/");

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idItemThetWasSelected=item.getItemId();

        if(idItemThetWasSelected==R.id.refresh_button)
        {
        loadWeatherData();
        }

        return super.onOptionsItemSelected(item);
    }

    public class WeaterQueryTask extends AsyncTask <String, Void, String[]> {


        @Override
        protected String[] doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonWeatherResponse = NetworkUtils
                       .getResponseFromHttpUrl(weatherRequestUrl);

               String[] simpleJsonWeatherData= OpenWeatherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);


                return simpleJsonWeatherData;


            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] strings) {
            if (strings != null) {

                for (String weatherString : strings) {
                    mWeatherTextView.append((weatherString) + "\n");
                }
        }


        }
    }



}