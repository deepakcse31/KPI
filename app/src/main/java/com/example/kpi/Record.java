package com.example.kpi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class Record extends AppCompatActivity {
    TextView kpi_name,Level,Leval_name,Period,Update,Value,Target_value,Target_result,Taget_details;
    String kpiname,leval,leval_name,period,update,value,targetvalue,targetresult,targetdetails,targetstatus,trendstatus;
    String Trend_value1,Trend_details1,Trend_result1;
    TextView Target_Status;
    TextView Trend_value,Trend_result,Trend_details;
    TextView Updated,dataowner,signoffe,signoffo;
    String updated1,dataowner1,signoffe1,signoff1c1;
    ImageView Trend_Status;
    Toolbar toolbar1;
    private Context mcontext1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        toolbar1=findViewById(R.id.toolbar3);
        toolbar1.setTitle("Bedstate");

        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        kpi_name=findViewById(R.id.kpi);
        Level=findViewById(R.id.level);
        Leval_name=findViewById(R.id.levelname);
        Period=findViewById(R.id.period);
        Update=findViewById(R.id.update);
        Value=findViewById(R.id.value);

        Target_value=findViewById(R.id.target_value);
        Target_result=findViewById(R.id.target_result);
        Taget_details=findViewById(R.id.target_details);
        Target_Status=findViewById(R.id.target_status);
        Target_Status.setBackgroundResource(R.drawable.circle4);
       GradientDrawable gd = (GradientDrawable) Target_Status.getBackground().getCurrent();





        Trend_value=findViewById(R.id.trend_value);
        Trend_result=findViewById(R.id.trend_result);
        Trend_details=findViewById(R.id.trend_details);
        Trend_Status=findViewById(R.id.trend_status);

        Updated=findViewById(R.id.update1);
        dataowner=findViewById(R.id.owner);
        signoffe=findViewById(R.id.signoff1);
        signoffo=findViewById(R.id.signoff2);

        kpiname=getIntent().getStringExtra("kpiname");
        leval=getIntent().getStringExtra("level");
        leval_name=getIntent().getStringExtra("levelname");
        period=getIntent().getStringExtra("period");
        value=getIntent().getStringExtra("value");
        update=getIntent().getStringExtra("update");

        targetvalue=getIntent().getStringExtra("target_value");
        targetresult=getIntent().getStringExtra("target_result");
        targetdetails=getIntent().getStringExtra("taget_details");
        targetstatus=getIntent().getStringExtra("target_status");

        gd.setColor(Color.parseColor(targetstatus));
        //(Target_Status.getBackground()).setColorFilter(Color.parseColor(targetstatus), PorterDuff.Mode.SRC_IN);


        Trend_value1=getIntent().getStringExtra("trend_value");
        Trend_result1=getIntent().getStringExtra("Trendresult");
        Trend_details1=getIntent().getStringExtra("trenddetails");
        trendstatus=getIntent().getStringExtra("trendStatus");

        updated1=getIntent().getStringExtra("updatedby");
        dataowner1=getIntent().getStringExtra("dataowner");
        signoffe1=getIntent().getStringExtra("signoffops");
        signoff1c1=getIntent().getStringExtra("signofeps");
        Log.d("updated->","updated->"+updated1);
        kpi_name.setText(kpiname);
        Level.setText(leval);
        Leval_name.setText(leval_name);
        Period.setText(period);
        Value.setText(value);
        Update.setText(update);
        Target_Status.setBackgroundColor(Color.parseColor(targetstatus));

        Picasso.with(this.mcontext1)
                .load(trendstatus)
                .into(Trend_Status);



        Target_value.setText(targetvalue);
        Target_result.setText(targetresult);
        Taget_details.setText(targetdetails);

        Trend_value.setText(Trend_value1);
        Trend_result.setText(Trend_result1);
        Trend_details.setText(Trend_details1);


        Updated.setText(updated1);
        dataowner.setText(dataowner1);
        signoffe.setText(signoffe1);
        signoffo.setText(signoff1c1);
    }
}
