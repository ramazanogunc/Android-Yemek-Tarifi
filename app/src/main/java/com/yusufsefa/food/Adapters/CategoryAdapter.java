package com.yusufsefa.food.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yusufsefa.food.GlobalData;
import com.yusufsefa.food.ICategoryClickListenner;
import com.yusufsefa.food.Models.Category;
import com.yusufsefa.food.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> categoryList;
    ICategoryClickListenner iCategoryClickListenner;
    Context context;

    public CategoryAdapter(List<Category> categoryList, ICategoryClickListenner iCategoryClickListenner, Context context) {
        this.categoryList = categoryList;
        this.iCategoryClickListenner = iCategoryClickListenner;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoryClickListenner.onCategoryClick(v, categoryList.get(view_holder.getPosition()).getId());
            }
        });
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        Category category = categoryList.get(position);
        String url = ((GlobalData)context.getApplicationContext()).getWebUrl();
        Picasso.get().load(url+category.getImage_url()).into(holder.imageView);
        //picasso
        holder.textView.setText(category.getName());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.imageViewCategory);
            textView = view.findViewById(R.id.textViewCategoryName);

        }
    }
}
