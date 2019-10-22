package www.square.codekul;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import www.square.codekul.Constant.CodekulConstant;
import www.square.codekul.DashBoard.CodekulDashBoard;
import www.square.codekul.Register.CodekulRegister;

public class MainActivity extends AppCompatActivity {

    EditText UserName,Password;
    Button Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        UserName=findViewById(R.id.edt_username);
        Password=findViewById(R.id.edt_password);
        //button
        Login=findViewById(R.id.btn_login);
        Register=findViewById(R.id.btn_register);
        //event
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Validation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(MainActivity.this, CodekulRegister.class);
                intentRegister.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentRegister);
            }
        });
    }

    private void Validation() throws JSONException {
        if (UserName.getText().toString().equalsIgnoreCase(""))
        {
            UserName.setError("Please Enter User Name");
        }
        else if (Password.getText().toString().equalsIgnoreCase("")){
            Password.setError("Please Enter Password");
        }
        else
        {
            UserName.setError(null);
            Password.setError(null);
            SendToServer();
        }
    }

    private void SendToServer() throws JSONException {
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put("email",UserName.getText().toString());
        jsonBody.put("password",Password.getText().toString());
        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, CodekulConstant.LoginUserAPI, jsonBody, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {

                if (response!=null){
                    Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    Intent backtoLogin=new Intent(MainActivity.this,CodekulDashBoard.class);
                    backtoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(backtoLogin);
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                onBackPressed();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("email",UserName.getText().toString());
                headers.put("password",Password.getText().toString());
                return headers;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonOblect);
    }


}

