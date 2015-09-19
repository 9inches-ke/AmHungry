package com.example.bestiize.amhungry.manager;

import android.content.Context;

import com.example.bestiize.amhungry.AmhungryApplication;
import com.example.bestiize.amhungry.models.Menu;
import com.example.bestiize.amhungry.models.Navigation;
import com.example.bestiize.amhungry.models.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class MenuManager {
    private List<Navigation> menuList ;
    private AmhungryApplication amhungryApplication;
    public MenuManager (Context context){
        this.menuList = new ArrayList<>();
        this.amhungryApplication = (AmhungryApplication) context;

    }

    public List<Navigation> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Navigation> newsList) {
        this.menuList = newsList;
    }
    public void addMenu(Navigation menu){
        this.menuList.add(menu);

    }
}