package com.yusufsefa.food.Parsers;

import com.yusufsefa.food.Models.Category;
import com.yusufsefa.food.Models.Food;
import com.yusufsefa.food.Models.Material;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodParser {


    public List<Food> prepareData(JSONArray jsonArray) {
        List<Food> foodList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length();i++)
            {
                Food food = prepareFood(jsonArray.getJSONObject(i));
                foodList.add(food);
            }
            return foodList;
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return null;

    }

    public Food prepareFood(JSONObject temp) {
        Food food = new Food();

        try {
            food.setId(temp.getInt("id"));
            food.setName(temp.getString("name"));
            food.setDescription(temp.getString("description"));
            food.setImage_url(temp.getString("image_url"));

            JSONObject c = temp.getJSONObject("category");
            CategoryParser categoryParser=new CategoryParser();

            Category category = categoryParser.prepareCategory(c);

            food.setCategory(category);

            List<Material> materials = new ArrayList<>();
            JSONArray materialArray = temp.getJSONArray("materials");

            for (int j = 0; j < materialArray.length();j++)
            {
                JSONObject tempMaterial = materialArray.getJSONObject(j);
                materials.add(new Material(tempMaterial.getInt("id"),tempMaterial.getString("name")));
            }
            food.setMaterials(materials);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return food;
    }
}
