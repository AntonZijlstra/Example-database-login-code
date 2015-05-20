//This is an example activity to show how you connect to the login database
//I will finish commenting this when I have the time

package com.claritas.anton.socbox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondDBTestActivity extends Activity {

    Button loginButton;
    Button createAccountButton;
    EditText usernameTextField;
    EditText passwordTextField;
    TextView statusText;

    // JSON parser class
    JSONParserForDatabase jsonParser = new JSONParserForDatabase();

    // url of server php files
    private static final String url_login = "http://192.168.1.85/Database_connection_php/db_check_userlogin.php";
    private static final String url_create_account = "http://192.168.1.85/Database_connection_php/db_login_create_account.php";

    // JSON Node names
    private static final String TAG_SUCCEEDED = "succeeded";
    private static final String TAG_USERDATA = "user_data";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_DEBUGMESSAGE = "debugMessage";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_dbtest_layout);

        loginButton = (Button)findViewById(R.id.loginButton);

        // save button click event
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // start a background task to access the login DB
                new Login().execute("LOGIN");
            }
        });

        createAccountButton = (Button)findViewById(R.id.createAccountButton);

        // save button click event
        createAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // starting background task to access the login DB
                new Login().execute("CREATE");
            }
        });
    }

    /**
     * Background Async Task to Get complete product details
     * */
    class Login extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updateStatusMessage("Accessing Database");
        }

        /**
         * Accessing DB in background thread
         * */
        protected String doInBackground(String... params) {


            int succeeded;

            usernameTextField = (EditText) findViewById(R.id.usernameTextField);
            passwordTextField = (EditText) findViewById(R.id.passwordTextField);

            try {
                // Building Parameters
                String debugMessage;
                JSONObject json;
                String username = usernameTextField.getText().toString();
                String password = passwordTextField.getText().toString();

                List<NameValuePair> dataToSend = new ArrayList<NameValuePair>();
                dataToSend.add(new BasicNameValuePair("username", username));
                dataToSend.add(new BasicNameValuePair("password", password));


                if(params[0].equals("CREATE"))
                {
                    // Send the new username and password to the database by making HTTP POST request
                    // A POST request is used as we are writing to the database
                    json = jsonParser.makeHttpRequest(url_create_account, "POST", dataToSend);
                    // check your log for json response
                    Log.d("User login status", json.toString());
                }
                else
                {
                    // Check the username and password against the database by making HTTP GET request
                    // A GET request is used as we are merely retrieving information and not writing to the database
                    json = jsonParser.makeHttpRequest(url_login, "GET", dataToSend);
                    // check your log for json response
                    Log.d("User login status", json.toString());
                }

                //The returned json onject should be an array in the form [Succeeded: (value), debugMessage (message) Login details: [username: (username string), password: (password string)]]

                // json success tag
                succeeded = json.getInt(TAG_SUCCEEDED);
                if (succeeded == 1) {

                    debugMessage= json.getString(TAG_DEBUGMESSAGE);
                    updateStatusMessage(debugMessage);
                    // successfully received login details
                    JSONArray userDataArray = json.getJSONArray(TAG_USERDATA); // JSON Array

                    // get first user login details from JSON Array
                    JSONObject userData = userDataArray.getJSONObject(0);

                    SharedPreferences userLoginDetails = getSharedPreferences("userlogindetails", 0);
                    SharedPreferences.Editor editor = userLoginDetails.edit();
                    editor.putString("username", userData.getString(TAG_USERNAME));
                    editor.putString("password", userData.getString(TAG_PASSWORD));
                    editor.commit();

                    Intent intent = new Intent(SecondDBTestActivity.this, ExampleLoggedInActivity.class);
                    //Start the activity
                    startActivity(intent);

                }else{
                    // user login details incorrect
                    debugMessage= json.getString(TAG_DEBUGMESSAGE);

                    updateStatusMessage(debugMessage);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                updateStatusMessage("Error: connection problems");
            }

            return null;

        }

        //This is the function to update UI objects from the background thread. If you try to do so directly from
        //a background thread it will crash
        private void updateStatusMessage(final String statusMessage) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                statusText = (TextView)findViewById(R.id.statusText);
                                statusText.setText(statusMessage);
                            }
                        });
                    } catch (final Exception ex) {
                        Log.i("Error:","Exception when updating status message in UI thread");
                    }
                }
            }.start();

        }


    }

}
