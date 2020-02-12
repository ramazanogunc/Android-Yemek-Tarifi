package com.yusufsefa.food.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.yusufsefa.food.Activities.DetailActivity;
import com.yusufsefa.food.Adapters.ListViewAdapter;
import com.yusufsefa.food.Async.FoodAsync;
import com.yusufsefa.food.Models.Food;
import com.yusufsefa.food.R;

import java.util.List;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        FoodAsync foodAsync = new FoodAsync();
        foodAsync.prepareRandomFoods(this);

    }

    public void prepareListView( List<Food> foodList ){
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), foodList);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(HomeFragment.this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Integer foodId = ((Food)parent.getAdapter().getItem(position)).getId();
        Intent detailActivty = new Intent(getContext(), DetailActivity.class);
        detailActivty.putExtra("food_id",foodId);
        startActivity(detailActivty);
    }



}
