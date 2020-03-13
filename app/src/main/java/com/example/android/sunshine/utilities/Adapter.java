package com.example.android.sunshine.utilities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.android.sunshine.R;

public class Adapter extends RecyclerView.Adapter<Adapter.NumberViewHolder> {



        private static final String TAG = Adapter.class.getSimpleName();

        private static int viewHolderCount;

        private int mNumberItems;

        private String [] strings;

        public Adapter(int numberOfItems, String [] strings) {
            mNumberItems = numberOfItems;
            this.strings=strings;
            viewHolderCount = 0;
        }


        @Override
        public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            Context context = viewGroup.getContext();
            int layoutIdForListItem = R.layout.number_list_item;
            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            NumberViewHolder viewHolder = new NumberViewHolder(view);


            viewHolder.viewHolderIndex.setText(strings[viewHolderCount]);

            int backgroundColorForViewHolder = ColorUtils
                    .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
            viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);

            viewHolderCount++;
            Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                    + viewHolderCount);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(NumberViewHolder holder, int position) {
            Log.d(TAG, "#" + position);
            holder.bind(position);
        }


        @Override
        public int getItemCount() {
            return mNumberItems;
        }


        class NumberViewHolder extends RecyclerView.ViewHolder {

            TextView listItemNumberView;
            TextView viewHolderIndex;


            public NumberViewHolder(View itemView) {
                super(itemView);

                listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);

                viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            }


            void bind(int listIndex) {
                listItemNumberView.setText(String.valueOf(listIndex));
            }
        }


    }
