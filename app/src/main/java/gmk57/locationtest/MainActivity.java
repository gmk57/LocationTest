package gmk57.locationtest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLocation = findViewById(R.id.btn_location);
        btnLocation.setOnClickListener(v -> getLastLocation());

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        Log.v(TAG, "getLastLocation");

        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

        client.getLastLocation()
                .addOnSuccessListener(location -> Log.v(TAG, "onSuccess: " + location))
                .addOnFailureListener(e -> Log.v(TAG, "onFailure: " + e))
                .addOnCompleteListener(task -> Log.v(TAG, "onComplete: " + task))
                .addOnCanceledListener(() -> Log.v(TAG, "onCanceled"))
        ;

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        Log.v(TAG, "available: " + available);
    }
}
