package com.vocabwin5.vocabwin5;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static final String EXTRA_MESSAGE = "com.vocabwin5.vocabwin5.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickDownload(View view) {
        String id = "0";
        final View tempView = view;

        switch (view.getId()) {
            case R.id.button_unit1:
                id = "1";
                break;
            case R.id.button_unit2:
                id = "2";
                break;
            case R.id.button_unit3:
                id = "3";
                break;
            case R.id.button_unit4:
                id = "4";
                break;
            case R.id.button_unit5:
                id = "5";
                break;
            case R.id.button_unit6:
                id = "6";
                break;
        }

        TextView buttonUnit1 = findViewById(R.id.button_unit1);
        TextView buttonUnit2 = findViewById(R.id.button_unit2);
        TextView buttonUnit3 = findViewById(R.id.button_unit3);
        TextView buttonUnit4 = findViewById(R.id.button_unit4);
        TextView buttonUnit5 = findViewById(R.id.button_unit5);
        TextView buttonUnit6 = findViewById(R.id.button_unit6);

        buttonUnit1.setEnabled(false);
        buttonUnit2.setEnabled(false);
        buttonUnit3.setEnabled(false);
        buttonUnit4.setEnabled(false);
        buttonUnit5.setEnabled(false);
        buttonUnit6.setEnabled(false);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://vocabwin.herokuapp.com/units5/" + id;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(tempView.getContext(), LangSelectActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, response);

                        TextView buttonUnit1 = findViewById(R.id.button_unit1);
                        TextView buttonUnit2 = findViewById(R.id.button_unit2);
                        TextView buttonUnit3 = findViewById(R.id.button_unit3);
                        TextView buttonUnit4 = findViewById(R.id.button_unit4);
                        TextView buttonUnit5 = findViewById(R.id.button_unit5);
                        TextView buttonUnit6 = findViewById(R.id.button_unit6);

                        buttonUnit1.setEnabled(true);
                        buttonUnit2.setEnabled(true);
                        buttonUnit3.setEnabled(true);
                        buttonUnit4.setEnabled(true);
                        buttonUnit5.setEnabled(true);
                        buttonUnit6.setEnabled(true);

                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        queue.add(stringRequest);

    }

    public void fromToPages(View view) {

        final View tempView = view;
        int fromTextInt, toTextInt;

        TextView buttonUnit1 = findViewById(R.id.button_unit1);
        TextView buttonUnit2 = findViewById(R.id.button_unit2);
        TextView buttonUnit3 = findViewById(R.id.button_unit3);
        TextView buttonUnit4 = findViewById(R.id.button_unit4);
        TextView buttonUnit5 = findViewById(R.id.button_unit5);
        TextView buttonUnit6 = findViewById(R.id.button_unit6);
        TextView buttonPages = findViewById(R.id.startButton);

        buttonUnit1.setEnabled(false);
        buttonUnit2.setEnabled(false);
        buttonUnit3.setEnabled(false);
        buttonUnit4.setEnabled(false);
        buttonUnit5.setEnabled(false);
        buttonUnit6.setEnabled(false);
        buttonPages.setEnabled(false);

        RequestQueue queue = Volley.newRequestQueue(this);

        EditText from = findViewById(R.id.editTextFrom);
        EditText to = findViewById(R.id.editTextTo);

        String fromText = from.getText().toString();
        String toText = to.getText().toString();


        try {
            fromTextInt = Integer.parseInt(fromText);
            toTextInt = Integer.parseInt(toText);
        } catch (NumberFormatException e) {
            fromTextInt = toTextInt = 0;
        }

        if(fromTextInt < 192 || toTextInt > 235 || fromTextInt > toTextInt) {
            Context context = getApplicationContext();
            CharSequence text = "Please check your input!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.TOP, 0, 5);
            toast.show();

        } else {

            String url = "https://vocabwin.herokuapp.com/pages5?from=" + fromText + "&to=" + toText;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(tempView.getContext(), LangSelectActivity.class);
                            intent.putExtra(EXTRA_MESSAGE, response);
                            startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v(TAG, error + "");
                }

            });

            queue.add(stringRequest);

        }
        buttonUnit1.setEnabled(true);
        buttonUnit2.setEnabled(true);
        buttonUnit3.setEnabled(true);
        buttonUnit4.setEnabled(true);
        buttonUnit5.setEnabled(true);
        buttonUnit6.setEnabled(true);
        buttonPages.setEnabled(true);
    }

}
