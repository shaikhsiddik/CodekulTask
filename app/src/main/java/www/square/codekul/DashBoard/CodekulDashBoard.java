package www.square.codekul.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import www.square.codekul.R;
import www.square.codekul.Resources.CodekulResource;
import www.square.codekul.UserList.CodekulUserList;

public class CodekulDashBoard extends AppCompatActivity {

    ConstraintLayout UserList,Resource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codekul_dash_board);
        UserList=findViewById(R.id.btn_get_userlist);
        Resource=findViewById(R.id.btn_resource);
        UserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userlist =new Intent(CodekulDashBoard.this, CodekulUserList.class);
                startActivity(userlist);
            }
        });
        Resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resIntent=new Intent(CodekulDashBoard.this, CodekulResource.class);
                startActivity(resIntent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
