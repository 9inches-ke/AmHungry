package com.example.bestiize.amhungry.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.activity.MenuListAdapter;
import com.example.bestiize.amhungry.view.MenuListItem;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerFragment extends Fragment{
    private CircleImageView profileImage;
    private Profile profile;
    private RelativeLayout relativeLayout;
    private RelativeLayout.LayoutParams relativeLayoutParams;
    private ListView listView;
    private MenuListAdapter menuListAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profile = Profile.getCurrentProfile();
        Uri uriProfileImage = profile.getProfilePictureUri(200,200);

        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
        profileImage = (CircleImageView)rootView.findViewById(R.id.image_profile);
        Picasso.with(getActivity()).load(uriProfileImage.toString()).into(profileImage);
        relativeLayout = (RelativeLayout)rootView.findViewById(R.id.nav_header_container);
        relativeLayoutParams=(RelativeLayout.LayoutParams)relativeLayout.getLayoutParams();
        relativeLayoutParams.setMargins(0,getStatusBarHeight(),0,0);


        listView = (ListView) rootView.findViewById(R.id.listview_menu);
        menuListAdapter = new MenuListAdapter(getActivity().getApplicationContext());
        listView.setAdapter(menuListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==2){
                    Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                    startActivity(launchIntent);

                }
            }
        });

        return rootView;
    }
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
