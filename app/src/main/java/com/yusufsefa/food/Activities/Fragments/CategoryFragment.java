package com.yusufsefa.food.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.yusufsefa.food.Activities.ListActivity;
import com.yusufsefa.food.Adapters.CategoryAdapter;
import com.yusufsefa.food.Async.CategoryAsync;
import com.yusufsefa.food.ICategoryClickListenner;
import com.yusufsefa.food.Models.Category;
import com.yusufsefa.food.R;
import java.util.List;

public class CategoryFragment extends Fragment implements ICategoryClickListenner {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        CategoryAsync categoryAsync = new CategoryAsync();
        categoryAsync.prepareAllCAtegories(this);

    }

    @Override
    public void onCategoryClick(View v, int categoryId) {
        Intent listActivity = new Intent(getContext(), ListActivity.class);
        listActivity.putExtra("category_id",categoryId);
        startActivity(listActivity);
    }

    public void prepareRecyclerView(List<Category> categoryList){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList,this,getContext());

        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
