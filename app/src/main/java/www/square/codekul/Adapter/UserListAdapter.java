package www.square.codekul.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import www.square.codekul.Model.UserList;
import www.square.codekul.R;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder>
{
    private Context context;
    private List<UserList> mUserLists;

    public UserListAdapter(Context context, List<UserList> mUserLists) {
        this.context = context;
        this.mUserLists = mUserLists;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_layout, parent, false);

        return new UserListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        UserList aUserList=mUserLists.get(position);
        Picasso.get().load(aUserList.getImage()).into(holder.UserImage);
        holder.UserListEmail.setText(aUserList.getEmail());
        String name=aUserList.getFirstname()+" "+aUserList.getLastname();
        holder.UserListName.setText(name);

    }

    @Override
    public int getItemCount() {
        return mUserLists.size();
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder{

        CircleImageView UserImage;
        TextView UserListName,UserListEmail;
        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            UserImage=itemView.findViewById(R.id.profile_image);
            UserListName=itemView.findViewById(R.id.txt_resource_name);
            UserListEmail=itemView.findViewById(R.id.txt_resource_year);
        }
    }
}
