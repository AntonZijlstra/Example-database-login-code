package com.claritas.anton.socbox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity that starts when the user is logged in
 */
public class ExampleLoggedInActivity extends Activity {
    TextView usernameDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logged_in_layout);
        SharedPreferences userLoginDetails = getSharedPreferences("userlogindetails", 0);
        String username = userLoginDetails.getString("username", "Error: no login data stored");
        usernameDisplay = (TextView)findViewById(R.id.usernameDisplay);
        usernameDisplay.setText(username);
    }
}
