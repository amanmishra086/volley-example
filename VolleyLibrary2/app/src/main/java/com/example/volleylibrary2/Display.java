package com.example.volleylibrary2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Display extends AppCompatActivity {

    ListView list;
   // String url = "https://azizsheikh.com/volley/public/userapi";
    String url = "http://192.168.43.140:8080/sqltesting/sqltest2.php";
    String array[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        list=findViewById(R.id.list);
        sendData();

    }
    public void sendData(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Display.this, ""+response, Toast.LENGTH_SHORT).show();
                JSONObject obj= null;
                try {
                    obj = new JSONObject(response);
                    array =new String[1];
                    JSONObject s=obj.getJSONObject("data");

                    array[0]=""+s.getString("student_first_name")+" "+s.getString("student_mobile");
                    ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,array);
                    list.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Display.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }
        ) ;
        requestQueue.add(stringRequest);


    }
}
