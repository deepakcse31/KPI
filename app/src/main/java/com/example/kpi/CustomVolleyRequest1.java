package com.example.kpi;

import android.content.Context;

import com.android.volley.RequestQueue;

public class CustomVolleyRequest1 {

    private static CustomVolleyRequest customVolleyRequest;
    private static Context context;
    private RequestQueue requestQueue;

    public CustomVolleyRequest1(RequestQueue requestQueue) {
        this.context=context;
        this.requestQueue = requestQueue;
    }
}
