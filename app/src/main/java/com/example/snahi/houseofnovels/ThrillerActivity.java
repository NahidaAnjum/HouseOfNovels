package com.example.snahi.houseofnovels;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ThrillerActivity extends AppCompatActivity {
    List<BookModel> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance_intent);
        recyclerView = findViewById(R.id.rv);
        new RrbAsynctask().execute();
        this.setTitle(getString(R.string.thrillerttl));
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ctl.setTitle(getString(R.string.tittle_toolbar));
    }


    public class RrbAsynctask extends AsyncTask<String, Void, String> {

        String Myurl = getString(R.string.thriller_url);

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(Myurl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder stringBuilder = new StringBuilder();
                String Line = null;
                while ((Line = br.readLine()) != null) {
                    stringBuilder.append(Line + "\n");
                }
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list = new ArrayList<>();
            // Toast.makeText(RrbIntent.this, ""+s, Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                String links = null;
                int i = 0;
                for (i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String id = jsonObject1.optString("id");
                    JSONObject vlminfo = jsonObject1.getJSONObject("volumeInfo");
                    String title = vlminfo.optString("title");
                    String author = vlminfo.optString("authors");
                    String pulisher = vlminfo.optString("publisher");
                    String desc = vlminfo.optString("description");
                    String pulisherdate = vlminfo.optString("publishedDate");
                    JSONObject link = vlminfo.getJSONObject("imageLinks");
                    String imagelink = link.optString("thumbnail");
                    JSONObject salesinfo = jsonObject1.getJSONObject("saleInfo");
                    JSONObject cost = salesinfo.getJSONObject("retailPrice");
                    Float price = Float.valueOf(cost.optString("amount"));

                    BookModel bookmodel = new BookModel(id, title, author, pulisher, pulisherdate, imagelink, price, desc);
                    list.add(bookmodel);
                }
                Toast.makeText(ThrillerActivity.this, "" + i, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            runLayoutAnimation(recyclerView);
        }

        private void runLayoutAnimation(final RecyclerView recyclerView) {
            final Context context = recyclerView.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.anim2);
            recyclerView.setAdapter(new AdapterClass(ThrillerActivity.this, list));
            recyclerView.setLayoutManager(new LinearLayoutManager(ThrillerActivity.this));


            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }
    }
}

