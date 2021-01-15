package com.example.android.sunshine.Activity.SecondActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.R;
import com.example.android.sunshine.utilities.Adapter;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.Product;

import org.json.JSONException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CementActivity extends AppCompatActivity {


    private Adapter mAdapter;


    private ArrayList<Bitmap> productsImage=new ArrayList<>();
    private ArrayList<String> productsImageString=new ArrayList<>();

    private RecyclerView mNumbersList;

    private Image image;
    private URL weatherRequestUrl;

    ArrayList<Product> products=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cement);



        mNumbersList = findViewById(R.id.rv_numbers);


        Observable<String> obString=  Observable.fromCallable(new Callable<String>() {
            @Override public  String call(){
                return getResponse();
            }
        });


        obString.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String user) {

                try {
                    products= OpenWeatherJsonUtils.getDetailsFromJson(user);
                    productsImageString=OpenWeatherJsonUtils.getImageStringFromJson(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(productsImageString.size());

                mAdapter = new Adapter(CementActivity.this, products);
                mNumbersList.setAdapter(mAdapter);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        System.out.println(productsImageString.size());


        LinearLayoutManager layoutManager = new LinearLayoutManager(CementActivity.this);
        mNumbersList.setLayoutManager(layoutManager);



    }


    public String getResponse()
    {
        try {
            weatherRequestUrl = new URL("https://chitadrita.herokuapp.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        String jsonWeatherResponse = "";

        try {
            jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl); // json строка з сервера


        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonWeatherResponse;
    }


    private void loadWeatherData() {


        URL weatherRequestUrl = NetworkUtils.buildUrl();

        try {
            String jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl); // json строка з сервера

            System.out.println(jsonWeatherResponse);

            products= OpenWeatherJsonUtils.getDetailsFromJson(jsonWeatherResponse); // готові продукти

        } catch (Exception e) {

            e.printStackTrace();
        }


        mAdapter = new Adapter(CementActivity.this, products);
        mNumbersList.setAdapter(mAdapter);
    }


    public static Bitmap getBitmap(String url) {
        try {
            System.out.println(url);
            InputStream is = (InputStream) new URL(url).getContent();

            Bitmap d = BitmapFactory.decodeStream(is);
            // is.close();

            return d;
        } catch (Exception e) {

            return null;
        }
    }

//
//
//    public class WeaterQueryTask extends AsyncTask<String, Void, ArrayList<Product> > {
//
//
//        @Override
//        protected ArrayList <Product> doInBackground(String... params) {
//            /* If there's no zip code, there's nothing to look up. */
//            if (params.length == 0) {
//                return null;
//            }
//
//
//
//
//            ArrayList<Product> products=null; // сюда прилітатимуть продукти з сервера
//
//            URL weatherRequestUrl = NetworkUtils.buildUrl();
//
//            try {
//                String jsonWeatherResponse = NetworkUtils
//                        .getResponseFromHttpUrl(weatherRequestUrl); // json строка з сервера
//
//                products= OpenWeatherJsonUtils.getDetailsFromJson(jsonWeatherResponse); // готові продукти
//
//
//
//                return products;
//
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
//        @Override
//        protected void onPostExecute(ArrayList<Product> product) {
//
//
//
//            mNumbersList = findViewById(R.id.rv_numbers);
//
//            LinearLayoutManager layoutManager = new LinearLayoutManager(CementActivity.this);
//            mNumbersList.setLayoutManager(layoutManager);
//
//            mAdapter = new Adapter(CementActivity.this, product);
//            mNumbersList.setAdapter(mAdapter);
//
//
//
//
//        }
//
//    }

}
