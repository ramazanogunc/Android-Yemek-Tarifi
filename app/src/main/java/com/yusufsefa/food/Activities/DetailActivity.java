package com.yusufsefa.food.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.yusufsefa.food.Async.FoodAsync;
import com.yusufsefa.food.GlobalData;
import com.yusufsefa.food.Models.Food;
import com.yusufsefa.food.Models.Material;
import com.yusufsefa.food.R;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    ImageView foodImage;
    TextView toolbarTitle, categoryText, materialText, specificationText;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getViewId();
        prepareToolbar();
        Integer foodId = getFoodId();
        FoodAsync foodAsync = new FoodAsync();
        foodAsync.getFood(this, foodId);
    }

    private void getViewId(){
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        foodImage = findViewById(R.id.foodImage);
        categoryText = findViewById(R.id.categoryText);
        materialText = findViewById(R.id.materialText);
        specificationText = findViewById(R.id.specificationText);
    }

    private void prepareToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private Integer getFoodId(){
        return getIntent().getExtras().getInt("food_id");
    }

    public void prepareShowData(Food food){
        toolbarTitle.setText(food.getName()+" Tarifi");
        if (food.getName().length() > 8)
            toolbarTitle.setTextSize(20);
        String url = ((GlobalData)getApplicationContext()).getWebUrl();
        Picasso.get().load(url+food.getImage_url()).into(foodImage);
        categoryText.setText("Kategori: "+food.getCategory().getName());
        String materialString = prepareMaterialText(food.getMaterials());
        materialText.setText(materialString);
        specificationText.setText(food.getDescription());

    }

    private String prepareMaterialText(List<Material> materialList){
        String materials = "Malzemeler: ";
        for (int i = 0; i < materialList.size(); i++)
        {
            Material temp = materialList.get(i);
            materials+=temp.getName()+" ,";
        }

        return materials.substring(0, materials.length() - 1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
