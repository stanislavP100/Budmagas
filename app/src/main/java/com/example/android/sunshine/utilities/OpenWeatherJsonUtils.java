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
package com.example.android.sunshine.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {


    public static String[] getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        /* Weather information. Each day's forecast info is an element of the "list" array */
        final String OWM_LIST ="all goods"; //"electroTools"; //"all goods"

        /* All temperatures are children of the "temp" object */
        final String OWM_NAME = "name";

        /* Max temperature for the day */
        final String OWM_ID = "id";
        final String OWM_PRICE = "price";



        /* String array to hold each day's weather String */
        String[] parsedWeatherData = null;

        JSONObject allGoodsJson = new JSONObject(forecastJsonStr);

     parsedWeatherData = new String[allGoodsJson.getJSONArray(OWM_LIST).length()];



        for (int i = 0; i < allGoodsJson.getJSONArray(OWM_LIST).length(); i++) {

            String name;

            Long id;

            Double price;


            JSONObject oneGood = allGoodsJson.getJSONArray(OWM_LIST).getJSONObject(i);

            name=oneGood.getString(OWM_NAME);

            id=oneGood.getLong(OWM_ID);

            price=oneGood.getDouble(OWM_PRICE);


            parsedWeatherData[i] =id+" "+ name+" "+ price;


        }


        return parsedWeatherData;
    }


}