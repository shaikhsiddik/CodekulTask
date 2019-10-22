package www.square.codekul.Register;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import www.square.codekul.Constant.CodekulConstant;
import www.square.codekul.MainActivity;
import www.square.codekul.MySingleton;
import www.square.codekul.R;

public class CodekulRegister extends AppCompatActivity {

    EditText RegisterEmail,RegisterPassword;
    Button RegisterSuccess;
    RequestQueue MyRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codekul_register);
        //init
        RegisterEmail=findViewById(R.id.edt_reg_email);
        RegisterPassword=findViewById(R.id.edt_reg_password);
        //button event
        RegisterSuccess=findViewById(R.id.btn_reg_success);
        RegisterSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterValidation();
            }
        });

        MyRequestQueue=Volley.newRequestQueue(this);

    }

    private void RegisterValidation()
    {
        if (RegisterEmail.getText().toString().equalsIgnoreCase("")){
            RegisterEmail.setError("Please Enter Email");
        }
        else if (RegisterPassword.getText().toString().equalsIgnoreCase(""))
        {
            RegisterPassword.setError("Please Enter Password");
        }
        else{
            try {
                SendServer();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /*Intent backLogin=new Intent(CodekulRegister.this, MainActivity.class);
            backLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(backLogin);*/
        }
    }



    private void SendServer() throws JSONException {
        final JSONObject jsonBody = new JSONObject();
        jsonBody.put("email",RegisterEmail.getText().toString());
        jsonBody.put("password",RegisterPassword.getText().toString());
        JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, CodekulConstant.RegisterUserAPI, jsonBody, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {

                if (response!=null){
                    Toast.makeText(getApplicationContext(), "Response:  " + response.toString(), Toast.LENGTH_SHORT).show();
                    Intent backtoLogin=new Intent(CodekulRegister.this,MainActivity.class);
                    backtoLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(backtoLogin);
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
                headers.put("email",RegisterEmail.getText().toString());
                headers.put("password",RegisterPassword.getText().toString());
                return headers;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonOblect);


    }

}
