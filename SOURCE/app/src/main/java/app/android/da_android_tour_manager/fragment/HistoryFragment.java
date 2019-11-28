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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.adapter.HistoryAdapter;
import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.model.DatTour;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    DatabaseReference historyRef;
    ArrayList<DatTour> datTourArrayList;
    ArrayList<String> arrayListKey;

    HistoryAdapter historyAdapter;
    RecyclerView recyclerViewHistory;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewHistory = view.findViewById(R.id.recyclerHistory);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        historyRef = FirebaseDatabase.getInstance().getReference("DatTour");
        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datTourArrayList = new ArrayList<>();
                arrayListKey = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    DatTour dt = item.getValue(DatTour.class);
                    if(dt.getMaKH().equals(UserCommon.maKhachHang))
                    {
                        datTourArrayList.add(dt);
                        String key = item.getKey();
                        arrayListKey.add(key);
                    }
                }

                historyAdapter = new HistoryAdapter(getContext(), datTourArrayList, arrayListKey);
                recyclerViewHistory.setAdapter(historyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
