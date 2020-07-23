package com.example.kpi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

public class ViewpagerAdapter1 extends PagerAdapter   {

    private List<SliderUtils> sliderImg;
    private ImageLoader imageLoader;
    private Context context;
    private String[] texts = {"text1", "text2", "text3"};
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4};
    TextView tx1,tx2,tx3;
    public static String filter1,filter2,filter3;

    public ViewpagerAdapter1(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return texts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout1, null);
        tx1=view.findViewById(R.id.te1);
        tx2=view.findViewById(R.id.te2);
        tx3=view.findViewById(R.id.te3);
        tx1.setText(filter1);
        Log.d("filter1 value",""+filter1);
        tx2.setText(filter2);
        tx3.setText(filter3);



        //tx1.setText(texts[position]);
        //tx1.setBackgroundColor(Color.GREEN);
        //ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        //imageView.setImageResource(images[position]);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
