package com.example.kpi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button b2;
    EditText ed1,ed2;
    RequestQueue rq5;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b2=findViewById(R.id.b1);
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        rq5= Volley.newRequestQueue(this);

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            applicationName10();
        }
    });
    }
    public void applicationName10()
    {
        String url2="https://deva.ncs-it.co.uk/tinav3/User_Information?$filter=AD_Login%20eq%20%27"+ed1.getText().toString()+"%27%20and%20Passcode%20eq%20%27"+ed2.getText().toString()+"%27";
        String url3="https://deva.ncs-it.co.uk/tinav3/User_Information";
        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, url3, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String username1=ed1.getText().toString();
                            String password1=ed2.getText().toString();
                            JSONArray jsonArray=response.getJSONArray("value");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject values=jsonArray.getJSONObject(i);
                                String username=values.getString("AD_Login");
                                String password=values.getString("Passcode");
                                Log.d("usernaem","username"+username);
                                Log.d("password","password"+password);

                                if ((username.equals(username1))&&(password.equals(password1)))
                                {
                                    String x=values.getString("Record_Id");
                                    sharedPreferences=getSharedPreferences("save_data",MODE_PRIVATE);
                                    editor=sharedPreferences.edit();
                                    editor.putString("AD_Login",username);
                                    editor.putString("Passcode",password);
                                    editor.putString("Record_id",x);
                                    editor.apply();

                                   Log.d("Record_id","Recode_id->"+x);
                                    Intent i10=new Intent(getApplicationContext(),MainActivity1.class);
                                    //i10.putExtra("message1",x);
                                    startActivity(i10);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Invalid username and password",Toast.LENGTH_LONG).show();
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
        rq5.add(jsonObjectRequest1);
    }
}
