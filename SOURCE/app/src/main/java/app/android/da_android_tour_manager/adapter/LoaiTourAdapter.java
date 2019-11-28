package app.android.da_android_tour_manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.ViewHolder.LoaiTourViewHolder;
import app.android.da_android_tour_manager.model.LoaiTour;
import app.android.da_android_tour_manager.model.Tour;

public class LoaiTourAdapter extends RecyclerView.Adapter<LoaiTourViewHolder> {

    ArrayList<LoaiTour> loaiTourArrayList;
    ArrayList<String> loaiTourKey;
    Context context;

    // load tour theo loai tour
    RecyclerView recyclerView;
    ArrayList<Tour> tourArrayList;
    ArrayList<String> tourKeys;
    TourAdapter tourAdapter;

    public LoaiTourAdapter(ArrayList<LoaiTour> loaiTourArrayList, ArrayList<String> loaiTourKey, Context context, RecyclerView recyclerViewTour) {
        this.loaiTourArrayList = loaiTourArrayList;
        this.loaiTourKey = loaiTourKey;
        this.context = context;
        this.recyclerView = recyclerViewTour;
    }

    @NonNull
    @Override
    public LoaiTourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaitour, parent, false);
        final LoaiTourViewHolder loaiTourViewHolder = new LoaiTourViewHolder(view);

        return loaiTourViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiTourViewHolder holder, final int position) {
        holder.btnLoaiTour.setText(loaiTourArrayList.get(position).getName());

        holder.btnLoaiTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference tourRef = FirebaseDatabase.getInstance().getReference();
                tourRef.child("Tour").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tourArrayList = new ArrayList<>();
                        tourKeys = new ArrayList<>();
                        for(DataSnapshot item : dataSnapshot.getChildren())
                        {
                            Tour tour = item.getValue(Tour.class);
                            //Toast.makeText(context, tour.getLoaiTourKey(), Toast.LENGTH_LONG).show();
                            if(loaiTourKey.get(position).equals(tour.getLoaiTourKey()))
                            {
                                tourArrayList.add(tour);
                                String key = item.getKey();
                                tourKeys.add(key);
                            }
                        }

                        tourAdapter = new TourAdapter(context, tourArrayList, tourKeys);
                        recyclerView.setAdapter(tourAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return loaiTourArrayList.size();
    }
}
