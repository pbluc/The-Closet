package columbia.pbluc.thecloset.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import columbia.pbluc.thecloset.R;
import columbia.pbluc.thecloset.models.ClosetItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ClosetItemViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<ClosetItem> closetItems;

    public RecyclerViewAdapter(ArrayList<ClosetItem> closetItems) {
        this.closetItems = closetItems;
    }

    @NonNull
    @Override
    public ClosetItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.closet_item_view, parent, false);
        return new ClosetItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClosetItemViewHolder holder, int position) {
        holder.bind(closetItems.get(position));
    }

    @Override
    public int getItemCount() {
        return closetItems.size();
    }

    public class ClosetItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivClosetItem;

        public ClosetItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivClosetItem = itemView.findViewById(R.id.ivClosetItem);
        }

        public void bind(final ClosetItem closetItem) {
            Picasso.get()
                    .load(closetItem.getImageUri())
                    .centerCrop()
                    .fit()
                    .into(ivClosetItem);

            itemView.setOnClickListener(view -> {
                if (!getSelected().isEmpty()) {
                    closetItem.setSelected(!closetItem.isSelected());
                    itemView.setBackgroundResource(closetItem.isSelected() ? R.drawable.bg_closet_item_selected : 0);
                }
            });

            itemView.setOnLongClickListener(view -> {
                if (getSelected().isEmpty()) {
                    closetItem.setSelected(true);
                    itemView.setBackgroundResource(R.drawable.bg_closet_item_selected);
                }
                return true;
            });
        }
    }

    public ArrayList<ClosetItem> getSelected() {
        ArrayList<ClosetItem> selected = new ArrayList<>();
        for (int i = 0; i < closetItems.size(); i++) {
            if (closetItems.get(i).isSelected()) {
                selected.add(closetItems.get(i));
            }
        }
        return selected;
    }
}
