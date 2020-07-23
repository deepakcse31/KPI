package com.example.kpi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Graph extends AppCompatActivity {
    BarChart b1;
    private GestureDetectorCompat gestureDetectorCompat;
    RequestQueue rq3;
   ArrayList<BarEntry> barentry;
    ArrayList<BarEntry> xAxisLabel;
   XAxis xAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        b1=findViewById(R.id.barchart);
        b1.setDrawBarShadow(true);
        b1.setDrawValueAboveBar(true);
        b1.setMaxVisibleValueCount(50);
        b1.setPinchZoom(false);
        b1.setDrawGridBackground(true);

        rq3= Volley.newRequestQueue(Graph.this);
       // barentry=new ArrayList<>();
        //barentry.add(new BarEntry(1,40f));
        //barentry.add(new BarEntry(2,50f));
        //barentry.add(new BarEntry(3,60f));
        //barentry.add(new BarEntry(4,70f));
        //barentry.add(new BarEntry(5,90f));

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");
        parseJson10();



        //BarDataSet barDataSet=new BarDataSet(barentry,"Data set1");



        gestureDetectorCompat = new GestureDetectorCompat(this, new MyGestureListener());


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe left' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if(event2.getX() < event1.getX()){
                Toast.makeText(getBaseContext(),
                        "Swipe left - startActivity()",
                        Toast.LENGTH_SHORT).show();

                //switch another activity
                Intent intent = new Intent(
                        Graph.this, SlideData.class);
                startActivity(intent);
            }

            return true;
        }
    }
    private void parseJson10()
    {
        SharedPreferences result=getSharedPreferences("save_data", Context.MODE_PRIVATE);
        String z=result.getString("Record_id","data not found");
        SharedPreferences result1=getSharedPreferences("save_data2", Context.MODE_PRIVATE);
        String c=result1.getString("Report_id1","data not found");
        Log.d("c->","c->"+c);
        String url2="https://deva.ncs-it.co.uk/tinav3/Items?$filter=Application_Information_Record_Id%20eq%209%20and%20User_Information_Record_Id%20eq%20"+z;
        Log.d("url->","url->"+url2);
        final JsonObjectRequest jsonObjectRequest5=new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("value");
                            JSONArray jsonArraysubject;
                            String[] graph;

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                Log.d("values1","values1"+values);
                                Gson gson = new Gson();
                                Log.d("Gson","Gson"+gson);
                                //String jsonOutput = values.getString("Item_JSON");
                                String jsonOutput1=values.getString("Item_Chart_JSON");
                                Log.d("Item_Chart_JSON","Item_Chart_JSON"+jsonOutput1);
                                jsonOutput1=jsonOutput1.replace('\n','\\');
                                jsonOutput1=jsonOutput1.replace('\t','\\');
                                jsonOutput1=jsonOutput1.replace('\r','\\');
                                jsonOutput1=jsonOutput1.replace('\\',' ');
                                Type listType = new TypeToken<Item_Chart_JSON[]>(){}.getType();
                                Item_Chart_JSON[] posts = gson.fromJson(jsonOutput1, listType);
                                Log.d("posts","posts"+posts);
                                float[] mStrings =new float[posts.length];
                                final String[] date=new String[posts.length];
                                final ArrayList<String> xEntrys = new ArrayList<>(posts.length);

                                for (int j=0;j<posts.length;j++)
                                {
                                    barentry= new ArrayList<>();
                                    SharedPreferences result=getSharedPreferences("save_data1", Context.MODE_PRIVATE);
                                    String b=result.getString("Report_id","data not found");
                                    Log.d("b","b"+b);
                                    Item_Chart_JSON objJobj =  posts[j];
                                    //graph=objJobj.sERIES01VALUE;
                                    //Log.d("take","take"+graph);
                                    mStrings[j]=objJobj.sERIES01VALUE;
                                        date[j]=objJobj.kPIDATELABEL;
                                    //xEntrys=objJobj.kPIDATELABEL;
                                   //Log.d("dekho1->","dekho1->"+x);
                                  //new float[(int) objJobj.sERIES01VALUE];

                                    Log.d("take","take"+mStrings);
                                   // barentry=new ArrayList<>();




                                   // Log.d("Json","json->"+x);
                                    //Log.d("Json1","json->"+new Item_Chart_JSON().sERIES01VALUE);
                                    //Log.d("Json2","json->"+new Item_Chart_JSON().sERIES02VALUE);
                                    //Log.d("Json3","json->"+new Item_Chart_JSON().sERIES02NAME);
                                }
                                barentry.add(new BarEntry(i,mStrings));
                                xEntrys.add(date[i]);
                                // Log.d("xentry","xentry"+xEntrys.add(date[i]));
                               xAxisLabel.add(new BarEntry(i, Float.parseFloat(Arrays.toString(date))));
                                Log.d("xentry1","xentry1"+  xAxisLabel.add(new BarEntry(i, Float.parseFloat(Arrays.toString(date)))));
                                //b1.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xEntrys));
                                XAxis xAxis=b1.getXAxis();
                                //xAxis.setValueFormatter(new IndexAxisValueFormatter(date));
                                xAxis.setValueFormatter(new ValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value) {
                                        // return the string va
                                        return  date[(int) value-1];
                                    }
                                });
                             //xAxis.setValueFormatter(new IndexAxisValueFormatter(date));
                               // xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(date));


                            }
                            BarDataSet barDataSet=new BarDataSet(barentry,"Data set1");
                            BarData data=new BarData(barDataSet);
                            data.setBarWidth(0.9f);
                            b1.setData(data);
                            b1.invalidate();
                            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


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
        rq3.add(jsonObjectRequest5);
    }
}
