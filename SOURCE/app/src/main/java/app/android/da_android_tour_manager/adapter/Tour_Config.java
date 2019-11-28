package app.android.da_android_tour_manager.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.fragment.TourDetailFragment;
import app.android.da_android_tour_manager.model.Tour;

public class Tour_Config{
    public Context context;
    public TourAdapter tourAdapter;
    public void setConfig(RecyclerView recyclerViewTour, Context context, List<Tour> tours, List<String> keys) {
            this.context = context;
            this.tourAdapter = new TourAdapter(tours, keys);
            recyclerViewTour.setLayoutManager(new LinearLayoutManager(context));
            recyclerViewTour.setAdapter(tourAdapter);
    }

    public class TourViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgTour;
        public TextView tourName;
        public String key;
        public TourDetailFragment tourDetailFragment = new TourDetailFragment();

        public TourViewHolder(ViewGroup viewGroup) {
            super(LayoutInflater.from(context).inflate(R.layout.item_row_tour, viewGroup, false));
            imgTour = itemView.findViewById(R.id.imgTour);
            tourName = itemView.findViewById(R.id.txtTourName);
        }

        public void bind(final Tour tour, final String key)
        {
            Picasso.get().load(tour.getHinhAnh()).into(imgTour);
            tourName.setText(tour.getName());
            this.key = key;

            this.imgTour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TourCommon.tourId = key;
                    ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, tourDetailFragment).commit();
                }
            });
        }


    }

    public class TourAdapter extends RecyclerView.Adapter<Tour_Config.TourViewHolder> {

        private List<Tour> tourList;
        private List<String> keys;


        public TourAdapter(List<Tour> tourList, List<String> keys) {
            this.tourList = tourList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public Tour_Config.TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TourViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Tour_Config.TourViewHolder holder, int position) {
            holder.bind(tourList.get(position),keys.get(position));
        }

        @Override
        public int getItemCount() {
            return tourList.size();
        }

    }
}
