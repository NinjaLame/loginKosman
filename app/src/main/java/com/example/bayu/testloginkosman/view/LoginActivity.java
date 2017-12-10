package com.example.bayu.testloginkosman.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bayu.testloginkosman.R;
import com.example.bayu.testloginkosman.Static;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText inp_email,inp_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inp_email = (EditText)findViewById(R.id.inp_email);
        inp_pass = (EditText)findViewById(R.id.inp_pass);
    }

    public void login(EditText inp_email, EditText inp_pass) {
        final String email = inp_email.getText().toString().trim();
        final String password = inp_pass.getText().toString().trim();
        //Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Static.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                        JSONObject object;
                        if(response.contains("id")){
                            Toast.makeText(LoginActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                            String id = "";
                            try {
                                object = new JSONObject(response);
                                id = object.getString("id");
                                Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } 
                        }else{

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this, "The server unreachable", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Static.KEY_EMAIL, email);
                params.put(Static.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void loginButton(View view) {
        //Toast.makeText(this, inp_email.getText().toString().trim(), Toast.LENGTH_SHORT).show();
        login(inp_email,inp_pass);
    }
}