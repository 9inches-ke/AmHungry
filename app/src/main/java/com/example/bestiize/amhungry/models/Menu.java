package com.example.bestiize.amhungry.models;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class Menu implements Navigation{
    private int icon;
    private String title;

    public Menu(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
