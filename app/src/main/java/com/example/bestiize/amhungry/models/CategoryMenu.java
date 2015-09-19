package com.example.bestiize.amhungry.models;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class CategoryMenu implements Navigation{
    private String namemenu;

    public CategoryMenu(String namemenu){
          this.namemenu=namemenu;

    }

    public String getNamemenu() {
        return namemenu;
    }

    public void setNamemenu(String namemenu) {
        this.namemenu = namemenu;
    }
}
