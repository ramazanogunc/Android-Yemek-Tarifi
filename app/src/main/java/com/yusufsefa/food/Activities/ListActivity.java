package com.yusufsefa.food.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import com.yusufsefa.food.Adapters.ListViewAdapter;
import com.yusufsefa.food.Async.FoodAsync;
import com.yusufsefa.food.Models.Food;
import com.yusufsefa.food.Models.Material;
import com.yusufsefa.food.R;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar toolbar;
    ListView listView;
    List<Food> foodList;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listView);
        prepareToolbar(toolbar);

        Integer category_id = getCategoryId();
        FoodAsync foodAsync = new FoodAsync();
        foodAsync.prepareCategoryFoods(this, category_id);

    }

    private void prepareToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private Integer getCategoryId() {
        return getIntent().getExtras().getInt("category_id");
    }

    public void saveFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void prepareListView(List<Food> foodList) {
        listViewAdapter = new ListViewAdapter(this, foodList);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_activity_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();
                List<Food> searchList = new ArrayList<>();
                for (int i = 0; i < foodList.size(); i++) {
                    Food temp = foodList.get(i);
                    if (temp.getName().toLowerCase().contains(query) ||
                            temp.getDescription().contains(query)
                    ) {
                        searchList.add(temp);
                    } else {
                        for (int j = 0; j < temp.getMaterials().size(); j++) {
                            Material tempMaterial = temp.getMaterials().get(j);
                            if (tempMaterial.getName().toLowerCase().contains(query))
                                searchList.add(temp);
                        }
                    }
                }
                listViewAdapter.updateData(searchList);
                return true;
            }
        });


        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Integer foodId = ((Food) parent.getAdapter().getItem(position)).getId();
        Intent detailActivity = new Intent(this, DetailActivity.class);
        detailActivity.putExtra("food_id", foodId);
        startActivity(detailActivity);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
