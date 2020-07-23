package com.example.kpi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity1 extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    RequestQueue rq,rq1,rq3;
    List<SliderUtils> sliderImg;
    String text;
    TextView t1,Filter11,Filter12,Filter13;
    ImageView i2;
    Toolbar t3;
    DrawerLayout d1;
    NavigationView n1;
    ActionBarDrawerToggle actionBarDrawerToggle;
    //String request_url="https://deva.ncs-it.co.uk/tinav3/ImageLibraries?$filter=ImageType%20eq%20%27Banner%27";


    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 10000;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 5000;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        viewPager = findViewById(R.id.viewPager);
        rq= Volley.newRequestQueue(this);
        rq1=Volley.newRequestQueue(this);
        t1=findViewById(R.id.text);
        i2=findViewById(R.id.i1);

        t3=findViewById(R.id.toolbar2);
        d1=findViewById(R.id.drawer_layout);
        n1=findViewById(R.id.navigation_view);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,d1,t3,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        d1.addDrawerListener(actionBarDrawerToggle);
        n1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav1:
                        menuItem.setChecked(true);

                        Toast.makeText(getApplicationContext(),"case1",Toast.LENGTH_LONG).show();
                        d1.closeDrawers();
                        break;
                    case R.id.nav2:
                        menuItem.setChecked(true);
                        Intent user=new Intent(getApplicationContext(),User_profile.class);
                        startActivity(user);
                        Toast.makeText(getApplicationContext(),"case2",Toast.LENGTH_LONG).show();
                        d1.closeDrawers();
                        break;
                    case R.id.nav3:
                        Intent sign=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(sign);
                        Toast.makeText(getApplicationContext(),"case3",Toast.LENGTH_LONG).show();
                        d1.closeDrawers();
                        break;
                }

                return false;
            }
        });



        rq3 = Volley.newRequestQueue(MainActivity1.this);
        Filter11=findViewById(R.id.filter1);
        Filter12=findViewById(R.id.filter2);
        Filter13=findViewById(R.id.filter3);
        sliderImg=new ArrayList<>();
        //String responseText = GetResponseText(requestUrl);
        //JSONObject mainResponseObject = new JSONObject(responseText);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicationName1();

            }
        });

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        sendRequest();
        applicationName();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == dotscount -1) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void applicationName1()
    {

        SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
        String b=result.getString("Record_id","data not found");
        String c=result.getString("Record_id1","data not found");
        String url2="https://deva.ncs-it.co.uk/tinav3/App_Summary?$filter=User_Information_Record_Id%20eq%20"+b;
        Log.d("url see->","url->"+url2);
        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                int check=values.getInt("Application_Information_Record_Id");
                               // Toast.makeText(getApplicationContext(),"chek the value"+check,Toast.LENGTH_LONG).show();
                                if (check==9)
                                {
                                    Intent i1=new Intent(getApplicationContext(),SecondActivity.class);
                                    i1.putExtra("message",check);
                                    startActivity(i1);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
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

        })
        {
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
        rq3.add(jsonObjectRequest1);
    }

    public void applicationName()
    {
        SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
        String x=result.getString("Record_id","data not found");
        String url1="https://deva.ncs-it.co.uk/tinav3/App_Summary?$filter=User_Information_Record_Id%20eq%20"+x;
        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                String Name=values.getString("Application_Name");
                                int filter1=values.getInt("Filter1");
                                int filter2=values.getInt("Filter2");
                                int filter3=values.getInt("Filter3");
                                Filter13.setText(String.valueOf(filter3));
                                Filter12.setText(String.valueOf(filter2));
                                Filter11.setText(String.valueOf(filter1));

                                String ImgUrl=values.getString("Application_icon_URL")+""+values.getString("Application_Icon")+".png";
                                Picasso.with(getApplicationContext())
                                        .load(ImgUrl)
                                        .into(i2);

                                t1.setText(Name);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        })
        {


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

    public void sendRequest()
    {
        String request_url ="https://deva.ncs-it.co.uk/tinav3/ImageLibraries?$filter=ImageType%20eq%20%27Banner%27";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, request_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            for (int i=0;i<=jsonArray.length();i++) {
                                SliderUtils sliderUtils = new SliderUtils();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                sliderUtils.setSliderImageUrl(jsonObject.getString("URL"));
                                sliderImg.add(sliderUtils);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        viewPagerAdapter = new ViewPagerAdapter(sliderImg,MainActivity1.this);
                        viewPager.setAdapter(viewPagerAdapter);
                        dotscount = viewPagerAdapter.getCount();
                        dots = new ImageView[dotscount];

                        for(int i = 0; i < dotscount; i++){

                            dots[i] = new ImageView(MainActivity1.this);
                            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            params.setMargins(8, 0, 8, 0);

                            sliderDotspanel.addView(dots[i], params);

                        }

                        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error ha",Toast.LENGTH_LONG).show();

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
        rq.add(jsonObjectRequest );
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}

