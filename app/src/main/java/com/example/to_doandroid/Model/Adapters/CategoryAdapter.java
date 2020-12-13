package com.example.to_doandroid.Model.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doandroid.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList arrayList;

    public CategoryAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        TextView workCategoryTitle, homeCategoryTitle, shopCategoryTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            workCategoryTitle = (TextView) itemView.findViewById(R.id.workCategoryTitle);
            homeCategoryTitle = (TextView) itemView.findViewById(R.id.homeCategoryTitle);
            shopCategoryTitle = (TextView) itemView.findViewById(R.id.shopCategoryTitle);
        }
    }
}
