package com.fitness.thusithgym.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fitness.thusithgym.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSupplementActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Spinner mSpinner;
    private Map<String, List<LatLng>> mLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_supplement);

        // initialize locations map with 5 locations for each item
        mLocations = new HashMap<>();
        mLocations.put("Soy Protein", Arrays.asList(
                new LatLng(6.909517669034412, 79.86636548919121),
                new LatLng(6.874759031172432, 79.89161750698898),
                new LatLng(6.867298627638545, 79.89319740141383),
                new LatLng(6.913317669734946, 79.86260502408134),
                new LatLng(6.918465424225914, 79.84857876481489)
        ));
        mLocations.put("Whey Protein", Arrays.asList(
                new LatLng(6.879697072203887, 79.86891938578933),
                new LatLng(6.902603276424864, 79.85756770823648),
                new LatLng(6.898366682843134, 79.86112398994143),
                new LatLng(6.880847097278705, 79.86907262477475),
                new LatLng(6.892301972740188, 79.8692877648536)
        ));
        mLocations.put("Glutamine", Arrays.asList(
                new LatLng(6.887337052654365, 79.86963658493388),
                new LatLng(6.896846458751156, 79.86273767274025),
                new LatLng(6.907108542010345, 79.87617539891473),
                new LatLng(6.883832026471012, 79.87222579805117),
                new LatLng(6.902206096622529, 79.87215625519015)
        ));

        mLocations.put("Caffeine", Arrays.asList(
                new LatLng(7.237148142407837, 79.83924398451511),
                new LatLng(7.096729047298699, 79.89479419210656),
                new LatLng(6.907108542010345, 79.87617539891473),
                new LatLng(6.883832026471012, 79.87222579805117),
                new LatLng(6.999413663097641, 79.92486480807877)
        ));

        mLocations.put("Electrolytes", Arrays.asList(
                new LatLng(6.86090021748296,  79.8685455343557),
                new LatLng(6.896846458751156, 79.86273767274025),
                new LatLng(6.907108542010345, 79.87617539891473),
                new LatLng(6.887486885457543, 79.89189148178288),
                new LatLng(6.902206096622529, 79.87215625519015)
        ));

        mSpinner = findViewById(R.id.location_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mLocations.keySet().toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = mSpinner.getSelectedItem().toString();
                List<LatLng> selectedLocations = mLocations.get(selectedItem);
                mMap.clear();
                for (LatLng latLng : selectedLocations) {
                    mMap.addMarker(new MarkerOptions().position(latLng));
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedLocations.get(0), 12.0f));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // to close the current activity
    }
}
