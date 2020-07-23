package com.example.kpi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class User_profile extends AppCompatActivity {
    TextView Usename, Department, TipsRole, Ward, Mobile, Email;
    RequestQueue rq1;
    Toolbar usert;
    DrawerLayout userd;
    NavigationView usern;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        usert = findViewById(R.id.toolbar3);
        userd = findViewById(R.id.drawer_layout1);
        usern = findViewById(R.id.navigation_view1);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, userd, usert, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        userd.addDrawerListener(actionBarDrawerToggle);


        rq1 = Volley.newRequestQueue(this);

        Usename = findViewById(R.id.username);
        Department = findViewById(R.id.department);
        TipsRole = findViewById(R.id.role);
        Ward = findViewById(R.id.ward);
        Mobile = findViewById(R.id.mobile);
        Email = findViewById(R.id.email);
        applicationName10();
    }

    public void applicationName10() {
        SharedPreferences result = getSharedPreferences("save_data", Context.MODE_PRIVATE);
        final String x = result.getString("Record_id", "data not found");
        String url1 = "https://deva.ncs-it.co.uk/tinav3/User_Information";
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("value");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject values = jsonArray.getJSONObject(i);
                                String record_id = values.getString("Record_Id");
                                if (record_id.equals(x)) {
                                    String username = values.getString("AD_Login").toUpperCase();
                                    String department = values.getString("Department");
                                    String Tips_role = values.getString("TiPS_Role");
                                    String ward = values.getString("Ward");
                                    String mobile = values.getString("Mobile_Number");
                                    String email = values.getString("Email_Address");
                                    Log.d("user_profile", "user_profile" + x);
                                    Usename.setText(username);
                                    Department.setText(department);
                                    TipsRole.setText(Tips_role);
                                    Ward.setText(ward);
                                    Mobile.setText(mobile);
                                    Email.setText(email);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = "apiTina:apiTinaQwert@123$#";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        rq1.add(jsonObjectRequest1);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}


