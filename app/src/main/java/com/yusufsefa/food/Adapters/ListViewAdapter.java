package com.yusufsefa.food.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yusufsefa.food.GlobalData;
import com.yusufsefa.food.Models.Food;
import com.yusufsefa.food.Models.Material;
import com.yusufsefa.food.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    List<Food> foods;
    Context mContext;


    public ListViewAdapter(Context mContext, List<Food> foods) {
        this.mContext = mContext;
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);

        Food food = (Food)getItem(position);

        ImageView imageView = convertView.findViewById(R.id.foodImageView);
        String url = ((GlobalData)mContext.getApplicationContext()).getWebUrl();
        Picasso.get().load(url+food.getImage_url()).into(imageView);
        TextView foodText=convertView.findViewById(R.id.foodTitle);
        foodText.setText(food.getName());
        TextView materialText=convertView.findViewById(R.id.foodMaterial);

        String materials = "Malzemeler:  \n";
        for (int i = 0; i < food.getMaterials().size(); i++)
        {
            Material temp = food.getMaterials().get(i);
            materials+=temp.getName()+" ,";
        }
        materials = materials.substring(0, materials.length() - 1);
        materialText.setText(materials);

        return convertView;
    }

    public void updateData(List<Food> foodList){
        this.foods = foodList;
        notifyDataSetChanged();
    }
}
