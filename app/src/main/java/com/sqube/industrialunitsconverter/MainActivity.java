package com.sqube.industrialunitsconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import adapters.UnitsAdapter;
import converters.GeneralUnitConverter;
import models.Unit;
import models.User;
import utils.FirebaseUtil;

import static models.Names.AREA;
import static models.Names.CURRENT;
import static models.Names.DATA;
import static models.Names.DENSITY;
import static models.Names.ENERGY;
import static models.Names.FEET;
import static models.Names.LENGTH;
import static models.Names.UNITS_AREA;
import static models.Names.UNITS_CURRENT;
import static models.Names.UNITS_DATA;
import static models.Names.UNITS_DENSITY;
import static models.Names.UNITS_ENERGY;
import static models.Names.UNITS_LENGTH;
import static models.Names.MASS;
import static models.Names.POWER;
import static models.Names.PRESSURE;
import static models.Names.SPEED;
import static models.Names.TEMPERATURE;
import static models.Names.UNITS_MASS;
import static models.Names.UNITS_POWER;
import static models.Names.UNITS_PRESSURE;
import static models.Names.UNITS_SPEED;
import static models.Names.UNITS_TEMPERATURE;
import static models.Names.UNITS_VOLUME;
import static models.Names.USERS;
import static models.Names.VOLUME;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText edtUnit, edtInput;
    private Spinner spnUnit;
    private View viewholder;
    private TextView txtTitle;
    private String[] stringArray=null;
    private RecyclerView lstUnits;
    private GeneralUnitConverter generalConverter;
    private String metric = "length";
    private String convertFrom = "feet";
    private double value = 0;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generalConverter = new GeneralUnitConverter();

        //initialize actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        //initialize DrawerLayout and NavigationView
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView txtName = header.findViewById(R.id.txtName);

        lstUnits = findViewById(R.id.lstUnits);
        edtInput = findViewById(R.id.edtInput);
        edtUnit = findViewById(R.id.edtUnit);
        spnUnit = findViewById(R.id.spnUnits);
        edtUnit.setOnClickListener(v -> spnUnit.performClick());
        txtTitle = findViewById(R.id.txtTitle);
        viewholder = findViewById(R.id.lnrLength);
        lstUnits.setLayoutManager(new LinearLayoutManager(this));
        convert();

        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                value = s.toString().isEmpty()? 0: Double.parseDouble(s.toString());
                convert();
            }
        });

        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String unit = spnUnit.getSelectedItem().toString();
                if(unit.equals(convertFrom))
                    return;
                edtUnit.setText(unit);
                switch (metric.toLowerCase()){
                    case LENGTH:
                        convertFrom = UNITS_LENGTH[position]; break;
                    case AREA:
                        convertFrom = UNITS_AREA[position]; break;
                    case MASS:
                        convertFrom = UNITS_MASS[position]; break;
                    case DENSITY:
                        convertFrom = UNITS_DENSITY[position]; break;
                    case VOLUME:
                        convertFrom = UNITS_VOLUME[position]; break;
                    case PRESSURE:
                        convertFrom = UNITS_PRESSURE[position]; break;
                    case POWER:
                        convertFrom = UNITS_POWER[position]; break;
                    case SPEED:
                        convertFrom = UNITS_SPEED[position]; break;
                    case DATA:
                        convertFrom = UNITS_DATA[position]; break;
                    case ENERGY:
                        convertFrom = UNITS_ENERGY[position]; break;
                    case CURRENT:
                        convertFrom = UNITS_CURRENT[position]; break;
                    case TEMPERATURE:
                        convertFrom = UNITS_TEMPERATURE[position]; break;
                }
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FirebaseUtil.getAuth().addAuthStateListener(firebaseAuth -> {
            if(firebaseAuth.getCurrentUser()==null)
                txtName.setText("Name");
            else
                FirebaseUtil.getDatabase().collection(USERS).document(FirebaseUtil.getAuth().getCurrentUser().getUid())
                        .get().addOnCompleteListener(task -> {
                            if(task.isSuccessful()&&task.getResult()!=null){
                                User profile = task.getResult().toObject(User.class);
                                txtName.setText(String.format("%s %s", profile.getFirstName(), profile.getLastName()));
                            }
                        });

        });
    }

    private void openProfile(){
        if(FirebaseUtil.getAuth().getCurrentUser()==null)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, ProfileActivity.class));
    }

    private void convert(){
        ArrayList<Unit> units = generalConverter.convertLength(convertFrom, value);
        lstUnits.setAdapter(new UnitsAdapter(units));
    }

    public void lnrClick(View view){
        if(metric.equals(view.getTag().toString().toLowerCase()))
            return;
        metric = view.getTag().toString().toLowerCase();
        if (viewholder !=view)
            viewholder.setBackgroundResource(0);
        view.setBackgroundResource(R.drawable.bg_lnr);
        txtTitle.setText(String.format("Convert %s", view.getTag().toString()));
        viewholder = view;
        edtInput.setText("0");
        value = 0;
        setSpinner(metric);
    }

    private void setSpinner(String metric){
        switch (metric){
            case LENGTH:
            case AREA:
            case MASS:
            case DENSITY:
            case VOLUME:
            case PRESSURE:
                stringArray = getResources().getStringArray(R.array.lenght_units);
                break;
            case POWER:
            case SPEED:
            case DATA:
            case ENERGY:
            case CURRENT:
            case TEMPERATURE:
                stringArray = getResources().getStringArray(R.array.energy_units);
                break;
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUnit.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                mDrawerLayout.closeDrawer(GravityCompat.START);
            else
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_profile:
                openProfile(); break;
            case R.id.nav_logout:
                showLogoutPrompt(); break;
        }
        return false;
    }

    private void showLogoutPrompt() {
        if (FirebaseUtil.getAuth().getCurrentUser() != null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getResources().getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            GoogleSignInClient mGoogleSignInClient= GoogleSignIn.getClient(MainActivity.this, gso);

            FirebaseUtil.getAuth().signOut();
            mGoogleSignInClient.signOut();
            finish();
        }
    }
}
