package com.example.android.sunshine.Activity.SecondActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.sunshine.R;
import com.example.android.sunshine.utilities.Adapter;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import com.example.android.sunshine.utilities.Product;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CementActivity extends AppCompatActivity {


    private Adapter mAdapter;

    private ArrayList<String> productsImageString=new ArrayList<>();


    private RecyclerView mNumbersList;

    private URL weatherRequestUrl;

    ArrayList<Product> products=new ArrayList<>();

  static   Bitmap noImageBitmap ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cement);

        mNumbersList = findViewById(R.id.rv_numbers);

        noImageBitmap=BitmapFactory.decodeResource(getResources(),R.drawable.noimage2);

        Observable<String> obString=  Observable.fromCallable(() -> {


            System.out.println(Thread.currentThread().getName()+ "##############");
            return getResponse();
        });


        obString.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(

                (String st)->{
                    System.out.println(st);
                    products= OpenWeatherJsonUtils.getDetailsFromJson(st);
                    productsImageString=OpenWeatherJsonUtils.getImageStringFromJson(st);
                    mAdapter = new Adapter(CementActivity.this, products);
                    mNumbersList.setAdapter(mAdapter);


                    final int[] ii = {0};

//

                    Observable<Bitmap>bn=Observable.create(s->{

                        for(String g : productsImageString)


                                s.onNext(getBitmap("https://chitadrita.herokuapp.com/get-image?image=" + g));


                    });


                bn.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Bitmap>() {
                                       @Override
                                       public void onSubscribe(@NonNull Disposable d) {

                                       }

                                       @Override
                                       public void onNext(@NonNull Bitmap bitmap) {

                                          products.get(ii[0]).setImageBitmap(bitmap);
                                          mAdapter.notifyDataSetChanged();
                                          ii[0]++;

                                       }

                                       @Override
                                       public void onError(@NonNull Throwable e) {

                                           e.printStackTrace();

                                       }

                                       @Override
                                       public void onComplete() {



                                       }


                                   }


                        );


                });


        LinearLayoutManager layoutManager = new LinearLayoutManager(CementActivity.this);
        mNumbersList.setLayoutManager(layoutManager);
        mAdapter = new Adapter(CementActivity.this, products);
        mNumbersList.setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                                                 @Override
                                                 public void onChanged() {
                                                     super.onChanged();
                                                 }
                                             }
        );

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



    public static Bitmap getBitmap(String url) throws IOException {
        try {

            InputStream is = (InputStream) new URL(url).getContent();



            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {

            return noImageBitmap;
        }
    }


}
