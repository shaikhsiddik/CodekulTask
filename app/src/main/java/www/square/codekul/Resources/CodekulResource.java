package www.square.codekul.Resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

import java.util.ArrayList;
import java.util.List;

import www.square.codekul.Adapter.ResourceAdapter;
import www.square.codekul.Adapter.UserListAdapter;
import www.square.codekul.Constant.CodekulConstant;
import www.square.codekul.Model.ResourceList;
import www.square.codekul.Model.UserList;
import www.square.codekul.R;

public class CodekulResource extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ResourceAdapter resourceAdapter;
    List<ResourceList> resourceLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codekul_resource);
        resourceLists=new ArrayList<>();
        mRecyclerView=findViewById(R.id.resource_RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);

        FetchResourceList();


    }

    private void FetchResourceList()
    {
        sendTCPRequestFetch(CodekulConstant.ResourceAPI);
    }

    private void sendTCPRequestFetch(String listUserAPI) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, listUserAPI,new Response.Listener<String>() {

            @Override
            public void onResponse(String result) {
                //Toast.makeText(getApplicationContext(), "Response:  " + result, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray dataArray  = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++){
                        ResourceList list=new ResourceList();
                        JSONObject dataobj = dataArray.getJSONObject(i);
                        list.setName(dataobj.getString("name"));
                        list.setYear(dataobj.getInt("year"));
                        list.setColor(dataobj.getString("color"));
                        list.setPantone(dataobj.getString("pantone_value"));

                        resourceLists.add(list);
                    }
                    resourceAdapter=new ResourceAdapter(getApplicationContext(),resourceLists);
                    mRecyclerView.setAdapter(resourceAdapter);
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
