package com.example.android.sunshine.Activity.SecondActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.R;
import com.example.android.sunshine.utilities.Adapter;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.Product;

import java.net.URL;
import java.util.ArrayList;

public class CementActivity extends AppCompatActivity {


    private Adapter mAdapter;

    private ArrayList<Bitmap> productsImage=new ArrayList<>();

    private RecyclerView mNumbersList;

    private Image image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cement);



        loadWeatherData();
    }




    private void loadWeatherData() {

        new WeaterQueryTask().execute("https://chitadrita.herokuapp.com/");

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu1, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int idItemThetWasSelected=item.getItemId();
//
//        if(idItemThetWasSelected==R.id.action_refresh)
//        {
//            loadWeatherData();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }




    public class WeaterQueryTask extends AsyncTask<String, Void, ArrayList<Product> > {


        @Override
        protected ArrayList <Product> doInBackground(String... params) {
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }




            ArrayList<Product> products=null; // сюда прилітатимуть продукти з сервера

            URL weatherRequestUrl = NetworkUtils.buildUrl();

            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl); // json строка з сервера

                products= OpenWeatherJsonUtils.getDetailsFromJson(jsonWeatherResponse); // готові продукти

///////////////////////////////////////////////////////////////////////////////////
//                Bitmap tmp, tmp2;
//
//                ArrayList <String> imageName;
//                imageName = new ArrayList<>(products.size());
//
//
//                for( Product i : products)
//                {
//                    String oo="https://chitadrita.herokuapp.com/get-image?image="+i.getImage();
//                    imageName.add(oo);;
//                }
//
//
//                for(String i : imageName){
//
//                   tmp=getBitmap(i);
//
//                   if(tmp==null)
//                       tmp=getBitmap("https://chitadrita.herokuapp.com/get-image?image=bol.jpeg");
//
//                    tmp2=Bitmap.createScaledBitmap(tmp,300,300,true);
//                    productsImage.add(tmp2);
//                }

//////////////////////////////////////////////////////////////////////////////////////////


                return products;



            } catch (Exception e) {

                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Product> product) {



            mNumbersList = findViewById(R.id.rv_numbers);

            LinearLayoutManager layoutManager = new LinearLayoutManager(CementActivity.this);
            mNumbersList.setLayoutManager(layoutManager);

            mAdapter = new Adapter(CementActivity.this, product);
            mNumbersList.setAdapter(mAdapter);




        }

    }

}
