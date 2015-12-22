package com.example.bestiize.amhungry.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.News;
import com.example.bestiize.amhungry.models.Restaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

/**
 * Created by Bestiize on 15/11/2558.
 */
public class ResultListItem extends RelativeLayout{
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private GoogleMap mMap;
    private FragmentActivity fragmentActivity;
    public ResultListItem(Context context) {
        super(context);
        this.context = context;
        initView();

    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_list_result, this, true);

        imageView = (ImageView) view.findViewById(R.id.imageNews);
        textView = (TextView) view.findViewById(R.id.textView);
       // setUpMapIfNeeded();

    }

    public void fill(Restaurant newsData, int position) {
         fragmentActivity =new FragmentActivity();
        position = position+1;
        Picasso.with(this.context).load(newsData.getPic()).into(imageView);
        textView.setText("Rank: " + position + "\n" + "Name: " + newsData.getName()
                        + "\nPrice: " + newsData.getPrice()
                        + "\nType: " + newsData.getType()
                        + "\nOpen: " + newsData.getOpen_time()
                        + "\nClose:" + newsData.getClose_time()
                        + "\nLocation X: " + newsData.getLocation_x()
                        + "\nLocation Y: " + newsData.getLocation_y()
                        + "\nVote: " + newsData.getVote()

        );


        // imageView.setImageResource(R.mipmap.ic_launcher);

    }




}

