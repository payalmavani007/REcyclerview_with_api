package com.pro.sau.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()) );
        displaydata();
    }

    private void displaydata() {
    String url = "http://192.168.1.200/Mr.Gill/getCourceData";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject o = (JSONObject) jsonArray.get(i);
                        String name = o.getString("name");
                        Log.d(TAG, "onBindViewHolder: "+name);
                        String picture = o.getString("picture");
                        adapter = new DataAdapter(getApplicationContext(), jsonArray);
                        recyclerView.setAdapter(adapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}
