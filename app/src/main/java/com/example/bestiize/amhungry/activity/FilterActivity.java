package com.example.bestiize.amhungry.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bestiize.amhungry.AmhungryApplication;
import com.example.bestiize.amhungry.R;
import com.example.bestiize.amhungry.models.Filter;
import com.example.bestiize.amhungry.models.Restaurant;
import com.example.bestiize.amhungry.services.http.AmhungryHTTPService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bestiize on 15/11/2558.
 */
public class FilterActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    protected AmhungryApplication application;
    private Toolbar mToolbar;
    private Button btnStart;
    private EditText inputPrice,inputDis;
    private TextInputLayout inputLayoutPrice,inputLayoutDis;
    private Spinner spinner;
    private ArrayList<String> typeData = new ArrayList<String>();
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location location;
    private Filter filter;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        this.application = (AmhungryApplication) this.getApplicationContext();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Find Restaurant for You");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // Build the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Location Services Not Active");
            builder.setMessage("Please enable Location Services and GPS");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }

        inputLayoutPrice = (TextInputLayout)findViewById(R.id.input_layout_price);
        inputLayoutDis = (TextInputLayout)findViewById(R.id.input_layout_dis);
        inputPrice = (EditText)findViewById(R.id.input_price);
        inputDis = (EditText)findViewById(R.id.input_dis);
        inputPrice.addTextChangedListener(new MyTextWatcher(inputPrice));
        inputDis.addTextChangedListener(new MyTextWatcher(inputDis));
        btnStart=(Button)findViewById(R.id.btn_start);
        spinner = (Spinner) findViewById(R.id.type);
        typeData.add("All");
        typeData.add("Restaurant");
        typeData.add("Steak");
        typeData.add("General");
        typeData.add("Northeast thai food");
        typeData.add("Food court");
        typeData.add("Noodle");
        filter = new Filter();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, typeData);
        spinner.setAdapter(adapter);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000);





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
        if(!validateDis()){
            return;
        }

        filter.setPrice(inputPrice.getText().toString());
        if(spinner.getSelectedItem().toString().equals("All")){
            filter.setRes_type("");
        }else {
            filter.setRes_type(spinner.getSelectedItem().toString());
        }
//        filter.setUser_x("13.8462674");
//        filter.setUser_y("100.5685339");
        filter.setDistance(inputDis.getText().toString());

        this.application.getAmhungryHTTPService().getRestaurant(filter, new AmhungryHTTPService.OnResponseCallBack<List<Restaurant>>() {
            @Override
            public void onResponse(boolean success, Throwable error, List<Restaurant> data, String errorMessage) {
                if (success) {
                    Log.d("status", data.get(0).getName());
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("result", (Serializable) data);
                    intent.putExtras(b);
                    startActivity(intent);


                } else {


                    AlertDialog alertDialog = new AlertDialog.Builder(FilterActivity.this).create();
                    alertDialog.setTitle("Sad");
                    alertDialog.setMessage("No Restaurant For You");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();


                }
            }
        });




    }
    private boolean validateSearch() {
        if (inputPrice.getText().toString().trim().isEmpty()) {
            inputLayoutPrice.setError("Enter Your Price");
            requestFocus(inputPrice);
            return false;
        } else {
            inputLayoutPrice.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateDis() {
        if (inputDis.getText().toString().trim().isEmpty()) {
            inputLayoutDis.setError("Enter Your Distance");
            requestFocus(inputDis);
            return false;
        } else {
            inputLayoutDis.setErrorEnabled(false);
        }

        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else {
            //handleNewLocation(location);
            Log.d("Location", location.getLongitude() + "");
            //application.getUsermanager().setLatitude(location.getLatitude());
            //application.getUsermanager().setLongitude(location.getLongitude());
            filter.setUser_x("" + location.getLatitude());
            filter.setUser_y("" + location.getLongitude());
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Location", location.getLongitude() + "");
        filter.setUser_x(""+location.getLatitude());
        filter.setUser_y("" + location.getLongitude());

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            //Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
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

