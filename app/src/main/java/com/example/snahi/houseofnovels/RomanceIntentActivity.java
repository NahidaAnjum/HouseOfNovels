package com.example.snahi.houseofnovels;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class RomanceIntentActivity extends AppCompatActivity {

    List<BookModel> list;
    Button romancebtn;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance_intent);
        recyclerView = findViewById(R.id.rv);
        this.setTitle(getString(R.string.romance_ttl));
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ctl.setTitle(getString(R.string.tittle_toolbar));
        RomanceAsynctask romanceAsynctask= new RomanceAsynctask();
       romanceAsynctask.execute();

    }


    public class RomanceAsynctask extends AsyncTask<String, Void, String> {

        String Myurl = getString(R.string.romance_url);

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
            //Toast.makeText(RomanceIntentActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
            list = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                String links = null;
                int i = 0;
                for (i = 0; i <jsonArray.length(); i++) {
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            runLayoutAnimation(recyclerView);
        }

        private void runLayoutAnimation(final RecyclerView recyclerView) {
            final Context context = recyclerView.getContext();
            final LayoutAnimationController controller =
                    AnimationUtils.loadLayoutAnimation(context, R.anim.anim2);
            recyclerView.setAdapter(new AdapterClass(RomanceIntentActivity.this, list));
            recyclerView.setLayoutManager(new LinearLayoutManager(RomanceIntentActivity.this));
            recyclerView.setLayoutAnimation(controller);
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }

    }
}
