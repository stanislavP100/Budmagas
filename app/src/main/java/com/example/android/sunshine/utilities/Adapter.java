package com.example.android.sunshine.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sunshine.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.NumberViewHolder> {

    private int mNumberItems;

    private ArrayList<Product> products;


    public Adapter(Context context, ArrayList<Product> product) {
        mNumberItems = product.size();
        this.products=product;
        //  bb=image;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_main;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new NumberViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        holder.setDetails(products, position);

    }


    @Override
    public int getItemCount() {

        return mNumberItems;
    }


    static class NumberViewHolder extends RecyclerView.ViewHolder {


        TextView viewHolderIndex;
        ImageView imageView;


        NumberViewHolder(View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.my_image);

            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_instance);
        }


        void setDetails(ArrayList<Product> products, int p) {
            viewHolderIndex.setText(products.get(p).getName());
            imageView.setImageBitmap(products.get(p).getImageBitmap());

        }



    }


//
//
//        private static final String TAG = Adapter.class.getSimpleName();
//
//        private static int viewHolderCount;
//
//        private Context context;
//
//        private int mNumberItems;
//
//        private String [] strings;
//
//        public Adapter(Context context, String [] st) {
//            mNumberItems = st.length;
//            this.context=context;
//            strings=st;
//            viewHolderCount = 0;
//        }
//
//        @Override
//        public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//            Context context = viewGroup.getContext();
//            int layoutIdForListItem = R.layout.number_list_item;
//            LayoutInflater inflater = LayoutInflater.from(context);
//            boolean shouldAttachToParentImmediately = false;
//
//            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
//            NumberViewHolder viewHolder = new NumberViewHolder(view);
//
//
//            viewHolderCount++;
//
//            System.out.println(viewHolderCount);
//
//            return viewHolder;
//        }
//
//
//        @Override
//        public void onBindViewHolder(NumberViewHolder holder, int position) {
//
//            Log.d(TAG, "#" + position);
//
//            holder.setDetails(strings, position);
//
//        }
//
//
//        @Override
//        public int getItemCount() {
//
//            return mNumberItems;
//        }
//
//
//        class NumberViewHolder extends RecyclerView.ViewHolder {
//
//
//            TextView viewHolderIndex;
//            ImageView imageView;
//
//
//            public NumberViewHolder(View itemView) {
//                super(itemView);
//
//                imageView=(ImageView) itemView.findViewById(R.id.my_image);
//
//                viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
//            }
//
//
//            void setDetails(String [] strings, int p) {
//                viewHolderIndex.setText(strings[p]);
//                imageView.setImageResource(R.drawable.perferator);
//            }
//
//        }


    }
