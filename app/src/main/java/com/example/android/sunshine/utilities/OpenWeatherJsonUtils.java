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

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class OpenWeatherJsonUtils {


    public static ArrayList<Product> getDetailsFromJson(String st)throws JSONException

    {
        ArrayList<Product> products=new ArrayList<>();

        final String OWM_LIST ="all goods"; //"electroTools"; //"all goods"

        /* All temperatures are children of the "temp" object */
        final String OWM_NAME = "name";




        /* Max temperature for the day */
        final String OWM_ID = "id";
        final String OWM_PRICE = "price";


        JSONObject allGoodsJson = new JSONObject(st);



        for (int i = 0; i < allGoodsJson.getJSONArray(OWM_LIST).length(); i++) {

            Product tmp=new
                    Product();

            JSONObject oneGood = allGoodsJson.getJSONArray(OWM_LIST).getJSONObject(i);

            tmp.setName(oneGood.getString(OWM_NAME));

            tmp.setId(oneGood.getLong(OWM_ID));

            tmp.setPrice(oneGood.getDouble(OWM_PRICE));

        //    tmp.setImageBitmap(getBitmap("https://chitadrita.herokuapp.com/get-image?image="+tmp.getImage()));


            products.add(i,tmp);


        }



        return products;
    }



    public  static ArrayList<String> getImageStringFromJson(String jsonString) throws JSONException {

        final String OWM_IMAGE = "image";
        final String OWM_LIST ="all goods";

        ArrayList<String> images=new ArrayList<>();


        JSONObject allGoodsJson = new JSONObject(jsonString);



        for (int i = 0; i < allGoodsJson.getJSONArray(OWM_LIST).length(); i++) {

            String tmp ;

            JSONObject oneGood = allGoodsJson.getJSONArray(OWM_LIST).getJSONObject(i);

            tmp = oneGood.getString(OWM_IMAGE);

            //    tmp.setImageBitmap(getBitmap("https://chitadrita.herokuapp.com/get-image?image="+tmp.getImage()));



            images.add(i, tmp);


        }

        return images;
    }

}