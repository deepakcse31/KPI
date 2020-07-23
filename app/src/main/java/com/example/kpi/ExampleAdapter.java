package com.example.kpi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    SharedPreferences sharedPreferences2;
    SharedPreferences.Editor editor2;
    public ExampleAdapter(Context context,ArrayList<ExampleItem> exampleList){

        mContext=context;
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.example_item,viewGroup,false);
        return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleViewHolder exampleViewHolder, final int i) {
            ExampleItem currentItem=mExampleList.get(i);
            String kpi_name=currentItem.getKpi_name();
            String upd=currentItem.getUpd();
            String level=currentItem.getLevel();
            String level_name=currentItem.getLevel_name();
            String period=currentItem.getPeriod();
            String Target=currentItem.getTarget();
            String value=currentItem.getValue();
            String owner=currentItem.getOwner();
            String target=currentItem.getTarget_Status();
            String Image_url="https://deva.ncs-it.co.uk/tinaV3/image/"+currentItem.getTrend_Status();

            Log.d("Image_url","Image_url"+Image_url);
            exampleViewHolder.te1.setText(kpi_name);
            exampleViewHolder.te2.setText(level);
            exampleViewHolder.te3.setText(level_name);
            exampleViewHolder.te4.setText(period);
            exampleViewHolder.te5.setText(Target);
            exampleViewHolder.te6.setText(value);
            exampleViewHolder.te7.setText(owner);
            exampleViewHolder.te8.setText(upd);
            Log.d("color","color"+target);
            exampleViewHolder.te9.setBackgroundColor(Color.parseColor(target));
            exampleViewHolder.te10.setBackgroundColor(Color.parseColor("#808080"));

            Picasso.with(this.mContext)
                .load(Image_url)
                    .into(exampleViewHolder.i4);
            exampleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String name=mExampleList.get(i).getKpi_name();
                    String kpi_name=mExampleList.get(i).getKpi_name();
                    String level=mExampleList.get(i).getLevel();
                    String level_name=mExampleList.get(i).getLevel_name();
                    String period=mExampleList.get(i).getPeriod();
                    String upd1=mExampleList.get(i).getUpd();
                    String update=mExampleList.get(i).getUpdated();
                    String value=mExampleList.get(i).getValue();
                    String target_value=mExampleList.get(i).getTarget_value();
                    String target_result=mExampleList.get(i).getTarget_Result();
                    String target_status=mExampleList.get(i).getTarget_Status();
                    String taget_details=mExampleList.get(i).getTarget_details();
                    String Trend_value=mExampleList.get(i).getTrend_value();
                    String Trend_Result=mExampleList.get(i).getTrend_Result();
                    String Trend_status="https://deva.ncs-it.co.uk/tinaV3/image/"+mExampleList.get(i).getTrend_Status();
                    String Trend_details=mExampleList.get(i).getTrend_details();
                    String updated_by=mExampleList.get(i).getUpdatedby();
                    String Data_owner=mExampleList.get(i).getOwner();
                    String signoff_ops=mExampleList.get(i).getSignOff_ops();
                    String signoff_eps=mExampleList.get(i).getSignOff_exec();
                    String report_id=mExampleList.get(i).getReport_id();
                    Log.d("report_id->","report_id"+report_id);
                    sharedPreferences2=mContext.getSharedPreferences("save_data2",mContext.MODE_PRIVATE);
                    editor2=sharedPreferences2.edit();
                    editor2.putString("Report_id1",report_id);
                    editor2.apply();
                    Intent in=new Intent(mContext,Record.class);
                    in.putExtra("kpiname",kpi_name);
                    in.putExtra("level",level);
                    in.putExtra("levelname",level_name);
                    in.putExtra("period",period);
                    in.putExtra("value",value);
                    in.putExtra("update",upd1);

                    in.putExtra("target_value",target_value);
                    in.putExtra("target_result",target_result);
                    in.putExtra("taget_details",taget_details);
                    in.putExtra("target_status",target_status);

                    in.putExtra("trend_value",Trend_value);
                    in.putExtra("trenddetails",Trend_details);
                    in.putExtra("Trendresult",Trend_Result);
                    in.putExtra("trendStatus",Trend_status);


                    in.putExtra("updatedby",updated_by);
                    in.putExtra("dataowner",Data_owner);
                    in.putExtra("signoffops",signoff_ops);
                    in.putExtra("signofeps",signoff_eps);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(in);

                    //Log.d("User-->","user_id->"+name);
                }
            });
        Log.d("link","check->"+upd);
            Log.d("link","check->"+Image_url);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public void filterList(ArrayList<ExampleItem> filteredList)
    {
        mExampleList=filteredList;
        notifyDataSetChanged();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView te1,te2,te3,te4,te5,te6,te7,te8,te9,te10;
        public ImageView i4;

        public ExampleViewHolder(@NonNull final View itemView) {
            super(itemView);
            te1=itemView.findViewById(R.id.t1);
            te2=itemView.findViewById(R.id.t2);
            te3=itemView.findViewById(R.id.t3);
            te4=itemView.findViewById(R.id.t4);
            te5=itemView.findViewById(R.id.t5);
            te6=itemView.findViewById(R.id.t6);
            te7=itemView.findViewById(R.id.t7);
            te8=itemView.findViewById(R.id.t8);
            i4=itemView.findViewById(R.id.i3);
            te9=itemView.findViewById(R.id.target);
            te10=itemView.findViewById(R.id.action);
            // on item click

        }
    }
}
