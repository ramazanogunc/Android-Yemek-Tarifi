package com.yusufsefa.food.Async;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yusufsefa.food.Activities.Fragments.CategoryFragment;
import com.yusufsefa.food.GlobalData;
import com.yusufsefa.food.Models.Category;
import com.yusufsefa.food.Parsers.CategoryParser;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class CategoryAsync extends AsyncHttpClient {
    ProgressDialog pd;

    public void prepareAllCAtegories(final Fragment fragment){
        pd = new ProgressDialog(fragment.getContext());
        pd.setMessage("Yükleniyor");
        pd.setCancelable(false);
        pd.show();
        String url = ((GlobalData)fragment.getContext().getApplicationContext()).getWebUrl();
        this.get(url+"api/categories", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(new String(responseBody));
                    List<Category> categoryList = new CategoryParser().prepareData(jsonArray);
                    ((CategoryFragment)fragment).prepareRecyclerView(categoryList);

                } catch (JSONException e) {e.printStackTrace();
                    Toast.makeText(fragment.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                pd.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(fragment.getContext(), "İnternet Bağlantınızı Kontrol Edin", Toast.LENGTH_SHORT).show();
                pd.dismiss();

            }
        });
    }
}
