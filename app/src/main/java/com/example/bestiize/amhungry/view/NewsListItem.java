package com.example.bestiize.amhungry.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.News;
import com.squareup.picasso.Picasso;

/**
 * Created by Bestiize on 9/17/2015.
 */
public class NewsListItem extends RelativeLayout {
    private Context context;
    private ImageView imageView;
    public NewsListItem(Context context){
        super(context);
        this.context = context;
        initView();

    }
    public void initView(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_list_news,this,true);
        imageView = (ImageView)view.findViewById(R.id.imageNews);


    }
    public void fill(News newsData){

        Picasso.with(this.context).load(newsData.getNewsURL()).into(imageView);
       // imageView.setImageResource(R.mipmap.ic_launcher);

    }
}
