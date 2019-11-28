package app.android.da_android_tour_manager.FirebaseManagerStatus;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.android.da_android_tour_manager.model.Tour;

public class TourStatus {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    List<Tour> tourArrayList = new ArrayList<>();

    public TourStatus() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tour");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Tour> tours, List<String> keys);
        void DataIsInserted();
        void DataUpdated();
        void DataDeleted();
    }

    public void readDataTour(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tourArrayList.clear();
                List<String> keyTours = new ArrayList<>();
                for(DataSnapshot keyTour : dataSnapshot.getChildren()){
                    keyTours.add(keyTour.getKey());
                    Tour tour = keyTour.getValue(Tour.class);
                    tourArrayList.add(tour);
                }
                dataStatus.DataIsLoaded(tourArrayList,keyTours);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Toast.makeText(getContext(),"Opss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
