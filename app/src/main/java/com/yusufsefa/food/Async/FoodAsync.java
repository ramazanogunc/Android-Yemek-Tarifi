package com.yusufsefa.food.Async;

import android.app.ProgressDialog;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yusufsefa.food.Activities.DetailActivity;
import com.yusufsefa.food.Activities.ListActivity;
import com.yusufsefa.food.GlobalData;
import com.yusufsefa.food.Parsers.FoodParser;
import com.yusufsefa.food.Activities.Fragments.HomeFragment;
import com.yusufsefa.food.Models.Food;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class FoodAsync extends AsyncHttpClient {

    ProgressDialog pd;


    public void prepareRandomFoods(final HomeFragment fragment){

        pd = new ProgressDialog(fragment.getContext());
        pd.setMessage("Yükleniyor...");
        pd.setCancelable(false);
        pd.show();
        String url = ((GlobalData)fragment.getContext().getApplicationContext()).getWebUrl();

        this.get(url+"api/foods/random/5", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                List<Food> foodList = null;
                try {
                    foodList = new FoodParser().prepareData(new JSONArray(new String(responseBody)));
                    ((HomeFragment)fragment). prepareListView(foodList);
                } catch (JSONException e) {
                    Toast.makeText(fragment.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(((HomeFragment)fragment).getContext(), "İnternet Bağlantınızı Kontrol Edin", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    public void prepareCategoryFoods(final ListActivity listActivity,Integer category_id){

        pd = new ProgressDialog(listActivity);
        pd.setMessage("Yükleniyor");
        pd.setCancelable(false);
        pd.show();
        String url = ((GlobalData)listActivity.getApplicationContext()).getWebUrl();

        this.get(url+"api/foods/category/"+category_id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                List<Food> foodList = null;
                try {
                    foodList = new FoodParser().prepareData(new JSONArray(new String(responseBody)));
                    listActivity.saveFoodList(foodList);
                    listActivity. prepareListView(foodList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(listActivity, "İnternet Bağlantınızı Kontrol Edin", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }




    public void getFood(final DetailActivity detailActivity, Integer id){

        pd = new ProgressDialog(detailActivity);
        pd.setMessage("Yükleniyor");
        pd.setCancelable(false);
        pd.show();
        String url = ((GlobalData)detailActivity.getApplicationContext()).getWebUrl();

        this.get(url+"api/foods/"+id, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Food food = new FoodParser().prepareFood(new JSONObject(new String(responseBody)));
                    detailActivity.prepareShowData(food);

                } catch (JSONException e) {
                    Toast.makeText(detailActivity,e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                pd.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(detailActivity, "İnternet Bağlantınızı Kontrol Edin", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }


}
