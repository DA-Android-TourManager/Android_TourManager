package app.android.da_android_tour_manager.fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.android.da_android_tour_manager.FirebaseManagerStatus.TourStatus;
import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.ViewHolder.TourViewHolder;
import app.android.da_android_tour_manager.adapter.LoaiTourAdapter;
import app.android.da_android_tour_manager.adapter.TourAdapter;
import app.android.da_android_tour_manager.adapter.Tour_Config;
import app.android.da_android_tour_manager.model.LoaiTour;
import app.android.da_android_tour_manager.model.Tour;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener{

    RecyclerView recyclerViewTour, recyclerViewLoaiTour;

    DatabaseReference searchRef, loaiTourRef, tourRef;

    SearchView searchView;

    ArrayList<LoaiTour> loaiTourArrayList;
    ArrayList<String> loaiTourKey;


    ArrayList<Tour> tourArrayList;
    ArrayList<String> tourKeys;
    TourAdapter tourAdapter;

    LoaiTourAdapter loaiTourAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerViewTour = view.findViewById(R.id.listTour);
        recyclerViewTour.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewLoaiTour = view.findViewById(R.id.listLoaiTour);
        LinearLayoutManager layoutManager  = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewLoaiTour.setLayoutManager(layoutManager);

        searchView = view.findViewById(R.id.timKiem);
        searchView.setOnQueryTextListener(this);

        tourRef = FirebaseDatabase.getInstance().getReference("Tour");
        tourRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tourArrayList = new ArrayList<>();
                tourKeys = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    Tour tour = item.getValue(Tour.class);
                    tourArrayList.add(tour);
                    String key = item.getKey();
                    tourKeys.add(key);
                }
                tourAdapter = new TourAdapter(getContext(), tourArrayList, tourKeys);
                recyclerViewTour.setAdapter(tourAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        loaiTourRef = FirebaseDatabase.getInstance().getReference("LoaiTour");
        loaiTourRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                loaiTourArrayList = new ArrayList<>();
                loaiTourKey = new ArrayList<>();

                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    LoaiTour loaiTour = item.getValue(LoaiTour.class);
                    loaiTourArrayList.add(loaiTour);
                    String key = item.getKey();
                    loaiTourKey.add(key);
                }
               // Toast.makeText(getContext(), loaiTourKey.size() + "", Toast.LENGTH_LONG).show();
                loaiTourAdapter = new LoaiTourAdapter(loaiTourArrayList, loaiTourKey, getContext(), recyclerViewTour);
                recyclerViewLoaiTour.setAdapter(loaiTourAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        ArrayList<Tour> newList = new ArrayList<>();
        ArrayList<String> newKeys = new ArrayList<>();
        int position = 0;
        for (Tour tour : tourArrayList)
        {
            if(tour.getName().toLowerCase().contains(userInput.toLowerCase()))
            {
                newList.add(tour);
                newKeys.add(tourKeys.get(position));
            }
            position++;
        }
        tourAdapter.searchTour(newList, newKeys);
        return true;
    }
}
