package columbia.pbluc.thecloset.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import columbia.pbluc.thecloset.R;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ClosetItemViewHolder> {

    private ArrayList<Uri> closetItemImages;

    public RecylerViewAdapter(ArrayList<Uri> closetItemImages) {
        this.closetItemImages = closetItemImages;
    }

    @NonNull
    @Override
    public ClosetItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.closet_item_view, parent, false);
        return new ClosetItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClosetItemViewHolder holder, int position) {
        Picasso.get()
                .load(closetItemImages.get(position))
                .centerCrop()
                .fit()
                .into(holder.ivClosetItem);
    }

    @Override
    public int getItemCount() {
        return closetItemImages.size();
    }

    public class ClosetItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ivClosetItem;

        public ClosetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivClosetItem = itemView.findViewById(R.id.ivClosetItem);
        }
    }
}
