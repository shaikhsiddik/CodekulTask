package www.square.codekul.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import www.square.codekul.Model.ResourceList;
import www.square.codekul.R;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder> {
    Context context;
    List<ResourceList> mResourceLists;

    public ResourceAdapter(Context context, List<ResourceList> mResourceLists) {
        this.context = context;
        this.mResourceLists = mResourceLists;
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_layout, parent, false);

        return new ResourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceViewHolder holder, int position) {
        ResourceList resourceList=mResourceLists.get(position);
        holder.ResourceName.setText(resourceList.getName());
        holder.ResourceYear.setText(String.valueOf(resourceList.getYear()));
        holder.ResourceValue.setText(resourceList.getPantone());
        holder.ResourceColor.setText(resourceList.getColor());

    }

    @Override
    public int getItemCount() {
        return mResourceLists.size();
    }

    public class ResourceViewHolder extends RecyclerView.ViewHolder{

        TextView ResourceName,ResourceValue,ResourceColor,ResourceYear;
        public ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            ResourceColor=itemView.findViewById(R.id.txt_resorce_color);
            ResourceName=itemView.findViewById(R.id.txt_resource_name);
            ResourceValue=itemView.findViewById(R.id.txt_resource_value);
            ResourceYear=itemView.findViewById(R.id.txt_resource_year);
        }
    }
}
