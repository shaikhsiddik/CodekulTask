package www.square.codekul.UserList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import www.square.codekul.Adapter.UserListAdapter;
import www.square.codekul.Constant.CodekulConstant;
import www.square.codekul.Model.UserList;
import www.square.codekul.R;

public class CodekulUserList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Toolbar toolbar;
    UserListAdapter mUserListAdapter;
    List<UserList> MuserLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codekul_user_list);
        //init
        mRecyclerView=findViewById(R.id.userlist_RecyclerView);

        MuserLists=new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        FetchUserList();

    }

    private void FetchUserList()
    {
        sendTCPRequestFetch(CodekulConstant.ListUserAPI);
    }

    private void sendTCPRequestFetch(String listUserAPI)
    {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, listUserAPI,new Response.Listener<String>() {

            @Override
            public void onResponse(String result) {
                //Toast.makeText(getApplicationContext(), "Response:  " + result, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray dataArray  = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++){
                        UserList userList=new UserList();
                        JSONObject dataobj = dataArray.getJSONObject(i);
                        userList.setFirstname(dataobj.getString("first_name"));
                        userList.setLastname(dataobj.getString("last_name"));
                        userList.setImage(dataobj.getString("avatar"));
                        userList.setEmail(dataobj.getString("email"));

                        MuserLists.add(userList);
                    }
                    mUserListAdapter=new UserListAdapter(getApplicationContext(),MuserLists);
                    mRecyclerView.setAdapter(mUserListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {

                return null;
            }

            public String getBodyContentType()
            {
                return "application/text; charset=utf-8";
            }
        };
        RetryPolicy policy = new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }
}
