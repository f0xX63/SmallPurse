package com.example.user.studentpurse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }
    public int[] slide_images={
            R.drawable.saw,
            R.drawable.code,
            R.drawable.eat
    };
    public String[] slide_headings = {
            "HELLO",
            "EASY",
            "GOOD LUCK"
    };

    public String[] slide_description = {
            "Привет, пользователь. Мы рады видеть тебя в нашем приложении.",
            "Молодец! А теперь немного о приложении. Оно создано специально для тебя.",
            "Ну вот и все. Надеемся тебе понравится работать с нами. Удачи тебе!"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slideLinearLayout);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHead = (TextView) view.findViewById(R.id.slide_head);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);


        slideImageView.setImageResource(slide_images[position]);
        slideHead.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
       container.removeView((LinearLayout)object);
    }


}
