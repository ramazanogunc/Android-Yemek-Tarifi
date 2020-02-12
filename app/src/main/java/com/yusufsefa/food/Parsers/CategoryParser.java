package com.yusufsefa.food.Parsers;

import com.yusufsefa.food.Models.Category;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryParser {


    public  List<Category> prepareData(JSONArray jsonArray) {
        List<Category> categoryList = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Category category = this.prepareCategory(jsonArray.getJSONObject(i));
                categoryList.add(category);
            }
            return categoryList;
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return null;

    }

    public  Category prepareCategory(JSONObject jsonObject) {


        try {
            Category category = new Category(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("image_url"));
            return category;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }
}
