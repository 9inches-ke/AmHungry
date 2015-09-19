package com.example.bestiize.amhungry.activity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.example.bestiize.amhungry.AmhungryApplication;
import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.CategoryMenu;
import com.example.bestiize.amhungry.models.Menu;
import com.example.bestiize.amhungry.models.Navigation;
import com.example.bestiize.amhungry.view.MenuListItem;
import com.example.bestiize.amhungry.view.NewsListItem;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class MenuListAdapter extends BaseAdapter {
    private AmhungryApplication amhungryApplication;
    private Context context;
    private RelativeLayout.LayoutParams relativeLayoutParams;
    public MenuListAdapter(Context context){
        this.amhungryApplication=(AmhungryApplication)context;
        this.context = context;




    }
    @Override
    public int getCount() {
        return amhungryApplication.getMenuManager().getMenuList().size();
    }

    @Override
    public Object getItem(int position) {
        return amhungryApplication.getMenuManager().getMenuList().get(position);
    }
    @Override
    public long getItemId(int position) {

        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        MenuListItem item= createListItem(convertView);
        //item.fill(amhungryApplication.getNewsManager().getNewsList().get(position));
        Navigation menu = amhungryApplication.getMenuManager().getMenuList().get(position);
        if(menu instanceof Menu){
            item.fill((Menu) menu);


        }
        else if(menu instanceof CategoryMenu){

            item.fillCategory((CategoryMenu) menu);


        }
        return item;
    }
    public MenuListItem createListItem(View convertView){

        if(convertView == null || !(convertView instanceof  MenuListItem)){
            return new  MenuListItem(this.context);
        }

        return ( MenuListItem)convertView;

    }
}
