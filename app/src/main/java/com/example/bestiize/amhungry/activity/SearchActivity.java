package com.example.bestiize.amhungry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestiize.amhungry.R;

/**
 * Created by Bestiize on 19/9/2558.
 */
public class SearchActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextInputLayout inputLayoutSearch;
    private EditText inputSearch;
    private Button btnSearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Search");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputLayoutSearch = (TextInputLayout)findViewById(R.id.input_layout_search);
        inputSearch = (EditText)findViewById(R.id.input_search);
        inputSearch.addTextChangedListener(new MyTextWatcher(inputSearch));
        btnSearch=(Button)findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


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

    private void submitForm() {
        if (!validateSearch()) {
            return;
        }



        Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT).show();
    }
    private boolean validateSearch() {
        if (inputSearch.getText().toString().trim().isEmpty()) {
            inputLayoutSearch.setError("Enter Your Search");
            requestFocus(inputSearch);
            return false;
        } else {
            inputLayoutSearch.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_search:
                    validateSearch();
                    break;

            }
        }

}


}
