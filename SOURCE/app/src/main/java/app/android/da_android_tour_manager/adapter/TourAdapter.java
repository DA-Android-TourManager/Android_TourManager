package app.android.da_android_tour_manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.ViewHolder.TourViewHolder;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.fragment.TourDetailFragment;
import app.android.da_android_tour_manager.model.Tour;

public class TourAdapter extends RecyclerView.Adapter<TourViewHolder> {

    Context context;
    ArrayList<Tour> tourArrayList;
    ArrayList<String> tourKeys;

    public TourAdapter(Context context, ArrayList<Tour> tourArrayList, ArrayList<String> tourKeys) {
        this.context = context;
        this.tourArrayList = tourArrayList;
        this.tourKeys = tourKeys;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_tour, parent, false);
        final TourViewHolder tourViewHolder = new TourViewHolder(view);
        return tourViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, final int position) {
        Picasso.get().load(tourArrayList.get(position).getHinhAnh()).into(holder.imageButton);
        holder.txtName.setText(tourArrayList.get(position).getName());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TourCommon.tourId = tourKeys.get(position);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TourDetailFragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourArrayList.size();
    }

    public void searchTour(ArrayList<Tour> arrayList, ArrayList<String> newKeys){
        tourArrayList = new ArrayList<>();
        tourArrayList.addAll(arrayList);
        tourKeys = new ArrayList<>();
        tourKeys.addAll(newKeys);
        notifyDataSetChanged();
    }


}
