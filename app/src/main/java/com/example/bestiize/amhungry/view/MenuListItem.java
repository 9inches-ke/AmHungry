package com.example.bestiize.amhungry.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.CategoryMenu;
import com.example.bestiize.amhungry.models.Menu;
import com.example.bestiize.amhungry.models.Navigation;
import com.example.bestiize.amhungry.models.News;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class MenuListItem extends RelativeLayout {
    private Context context;
    private ImageView icon;
    private TextView textView;
    private  View view;
    public MenuListItem(Context context){
        super(context);
        this.context = context;
        initView();

    }
    public void initView(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_list_menu,this,true);
        icon = (ImageView) findViewById(R.id.icon_menu);
        textView=(TextView)findViewById(R.id.text_menu);


    }
    public void fill(Menu menuData){
        this.icon.setImageResource(menuData.getIcon());
        this.textView.setText(menuData.getTitle());
        view.setBackgroundResource(R.drawable.backgroundmenu);





    }
    public void fillCategory(CategoryMenu categoryMenu){


        this.textView.setText(categoryMenu.getNamemenu());

    }



}
