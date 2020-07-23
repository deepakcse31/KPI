package com.example.kpi;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<SliderUtils> sliderImg;
    private ImageLoader imageLoader;

        private Context context;
        private String[] texts = {"text1", "text2", "text3"};
        private LayoutInflater layoutInflater;
       // private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4};

        public ViewPagerAdapter(List<SliderUtils> sliderImg, Context context) {
            this.sliderImg=sliderImg;
            this.context = context;
        }

        @Override
        public int getCount() {
            return sliderImg.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_layout, null);
            SliderUtils utils=sliderImg.get(position);

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
           // imageView.setImageResource(images[position]);
            TextView textView=(TextView) view.findViewById(R.id.textview);
            imageLoader= CustomVolleyRequest.getInstance(context).getImageLoader();
            imageLoader.get(utils.getSliderImageUrl(),ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,android.R.drawable.ic_dialog_alert));


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
