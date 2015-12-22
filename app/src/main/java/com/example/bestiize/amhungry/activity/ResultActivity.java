package com.example.bestiize.amhungry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.Restaurant;

import java.util.List;

/**
 * Created by Bestiize on 15/11/2558.
 */
public class ResultActivity extends AppCompatActivity {
    private ListView listView;
    private Toolbar mToolbar;
    private List<Restaurant> result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Your Restaurant");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        Bundle bundle=intent.getExtras();
        result = (List<Restaurant>) bundle.getSerializable("result");
        listView = (ListView) findViewById(R.id.list_result);
        ResultListAdapter adapter = new ResultListAdapter(getApplicationContext(),result);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                intent.putExtra("x",result.get(position).getLocation_x());
                intent.putExtra("y",result.get(position).getLocation_y());
                startActivity(intent);
            }
        });
       // Log.d("result",result.get(0).getName());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==android.R.id.home){
            Log.d("home_id","gggg");
            finish();
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
