package com.example.kpi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

public class SecondActivity extends AppCompatActivity {
    Toolbar toolbar1;
    TextView filte1,filte2,filte3;
    RequestQueue rq2,rq3,rq5;
    RecyclerView mRecycleView;
    ExampleAdapter mExampleAdapter;
    ArrayList<ExampleItem> mExampleList;
    EditText ed1;
    ViewPager viewPager;
    SwipeController swipeController = null;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ImageView action,new1,all;
    SharedPreferences sharedPreferences1,sharedPreferences2;
    SharedPreferences.Editor editor1,editor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar1=findViewById(R.id.toolbar);
        mRecycleView=findViewById(R.id.recycle_view);
        viewPager=findViewById(R.id.viewPager1);
        filte1=findViewById(R.id.textView);
        action=findViewById(R.id.imageView4);

        rq5= Volley.newRequestQueue(this);



        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList=new ArrayList<>();
        setSupportActionBar(toolbar1);
        rq3 = Volley.newRequestQueue(SecondActivity.this);
        rq2 = Volley.newRequestQueue(SecondActivity.this);
        parseJson();
        applicationName5();
        applicationName1();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rq2 = Volley.newRequestQueue(SecondActivity.this);
        Intent intent=getIntent();
        int x =intent.getIntExtra("message",0);
        ed1=findViewById(R.id.e1);



        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots1);

        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
        //Toast.makeText(getApplicationContext(),"dekho jara"+x,Toast.LENGTH_LONG).show();


        //swipeController = new SwipeController();

        swipeController = new SwipeController(new SwipeControllerAction() {
            @Override
            public void onRightClicked(int position) {
                Toast.makeText(getApplicationContext(),"rightButton click",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onLeftClicked(int position) {
                SharedPreferences result1=getSharedPreferences("save_data2", Context.MODE_PRIVATE);
                String c=result1.getString("Report_id1","data not found");
                Log.d("onbutton->","onbutton->"+c);

                Toast.makeText(getApplicationContext(),"Left Button Click",Toast.LENGTH_LONG).show();
                Intent graph=new Intent(getApplicationContext(),Graph.class);
                startActivity(graph);

                super.onLeftClicked(position);
            }

            @Override
            public void onLeftClicked1(int position) {
                Toast.makeText(getApplicationContext(),"button click1",Toast.LENGTH_LONG).show();
                super.onLeftClicked1(position);
            }

            @Override
            public void onRightClicked1(int position) {
                Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                super.onRightClicked1(position);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecycleView);

        mRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot1));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot_1));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        ArrayList<ExampleItem> filteredlist = new ArrayList<>();
        for (ExampleItem item : mExampleList) {
            if (item.getKpi_name().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        mExampleAdapter.filterList(filteredlist);


    }


    public void applicationName1()
        {
            SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
            String a=result.getString("Record_id","data not found");
            String url2="https://deva.ncs-it.co.uk/tinav3/App_Summary?$filter=User_Information_Record_Id%20eq%20"+a;
            JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, url2, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray=response.getJSONArray("value");
                                for (int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject values=jsonArray.getJSONObject(i);
                                    String Name=values.getString("Application_Name");
                                    toolbar1.setTitle(Name);
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
            rq2.add(jsonObjectRequest1);
        }
    private void parseJson()
    {
        SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
        String y=result.getString("Record_id","data not found");
        String url2="https://deva.ncs-it.co.uk/tinav3/Items?$filter=Application_Information_Record_Id%20eq%209%20and%20User_Information_Record_Id%20eq%20"+y;
        final JsonObjectRequest jsonObjectRequest4=new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            JSONArray jsonArraysubject;
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                Log.d("values","values"+values);
                                Gson gson = new Gson();
                                String jsonOutput = values.getString("Item_JSON");
                                String jsonOutput1=values.getString("Item_Chart_JSON");
                                jsonOutput=jsonOutput.replace('\n','\\');
                                jsonOutput=jsonOutput.replace('\t','\\');
                                jsonOutput=jsonOutput.replace('\r','\\');
                                jsonOutput=jsonOutput.replace('\\',' ');
                                Type listType = new TypeToken<Item_JSON>(){}.getType();
                                Item_JSON posts = gson.fromJson(jsonOutput, listType);
                                String  x=posts.reportID;
                                String y=posts.reportStatusID;
                                String z=posts.period;
                                sharedPreferences1=getSharedPreferences("save_data1",MODE_PRIVATE);
                                editor1=sharedPreferences1.edit();
                                editor1.putString("Report_id",x);
                                editor1.apply();

                                Log.d("dekhlo","  "+posts.targetStatus);

                                mExampleList.add(new ExampleItem(posts.kPIName,posts.level,posts.levelName,posts.period1,posts.target,posts.value,posts.dataOwner,posts.period,posts.trendStatus,posts.targetStatus,posts.updated,posts.targetValue,posts.targetResult,posts.targetDetail,posts.trendValue,posts.trendDetail,posts.updatedBy,posts.signoffOps,posts.signoffExec,posts.trendResult,posts.actionStatus,posts.reportID));

                            }
                            mExampleAdapter=new ExampleAdapter(getApplicationContext(),mExampleList);
                            mRecycleView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),"kuch galat ha",Toast.LENGTH_LONG).show();
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
        rq3.add(jsonObjectRequest4);
    }

    public void applicationName5()
    {
        SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
        String z=result.getString("Record_id","data not found");
        String url4="https://deva.ncs-it.co.uk/tinav3/App_Summary?$filter=User_Information_Record_Id%20eq%20"+z;
        JsonObjectRequest jsonObjectRequest5=new JsonObjectRequest(Request.Method.GET, url4, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                String Name=values.getString("Application_Name");
                                String filter1=values.getString("Filter1");
                                String filter2=values.getString("Filter2");
                                String filter3=values.getString("Filter3");
                                ViewpagerAdapter1.filter1=filter1;
                                ViewpagerAdapter1.filter2=filter2;
                                ViewpagerAdapter1.filter3=filter3;
                                Log.d("Second","dekh"+filter1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ViewpagerAdapter1 viewPagerAdapter1=new ViewpagerAdapter1(SecondActivity.this);
                        viewPager.setAdapter(viewPagerAdapter1);

                        dotscount = viewPagerAdapter1.getCount();
                        dots = new ImageView[dotscount];

                        for(int i = 0; i < dotscount; i++){

                            dots[i] = new ImageView(SecondActivity.this);
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
        rq5.add(jsonObjectRequest5);
    }

}
