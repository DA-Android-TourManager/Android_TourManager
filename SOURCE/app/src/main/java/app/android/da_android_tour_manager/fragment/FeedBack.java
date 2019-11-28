package app.android.da_android_tour_manager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.model.FeedBack_Model;
import app.android.da_android_tour_manager.model.Tour;

public class FeedBack extends Fragment {
    TextView tvTenTour;
    LinearLayout ABCD;
    RatingBar A;
    Button btnSend;
    EditText edtEmail,edtPhanHoi;
    DatabaseReference databaseReference;
    DatabaseReference _databaseReference;



    Button QuayLai;
    Fragment fragment;

    public FeedBack() {
        // Required empty public constructor
    }
    public void Send_FeedBack(Float A,String edtEmail,String edtPhanHoi)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("PhanHoi");
        String PhanHoiID = databaseReference.push().getKey();
        FeedBack_Model FB_ABCD =new FeedBack_Model(A,edtEmail,edtPhanHoi);
        databaseReference.child(PhanHoiID).setValue(FB_ABCD);
    }
    public void Load_TenTour()
    {

        _databaseReference = FirebaseDatabase.getInstance().getReference("Tour");
        String idTour = TourCommon.tourId;
        //Toast.makeText(getContext(),idTour,  Toast.LENGTH_SHORT).show();
        _databaseReference.child(idTour).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tour tourDetail = dataSnapshot.getValue(Tour.class);
                tvTenTour.setText(tourDetail.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed_back, container, false);
        ////
    }
    public boolean loadFragment(Fragment fragment) {
        if (fragment !=null)
        {
            ((AppCompatActivity)getContext()).getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout,fragment)
                    .commit();
            return true;
        }
        return false;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toast.makeText(getContext(), TourCommon.tourId,Toast.LENGTH_SHORT).show();

        Load_TenTour();
        tvTenTour = view.findViewById(R.id.tvTenTour);
        A = (RatingBar) view.findViewById(R.id.rating_feedback);
        btnSend = view.findViewById(R.id.btnGui);
        edtEmail = view.findViewById(R.id.edtUser);
        edtPhanHoi = view.findViewById(R.id.edtMoTa);
        ABCD = view.findViewById(R.id.Linear_FeedBack);
        QuayLai = view.findViewById(R.id.btnQuayLai);



        QuayLai.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view1)
            {

                fragment = new HomeFragment();
                loadFragment(fragment);
            }
        });

        //Toast.makeText(getContext(),idTour,  Toast.LENGTH_SHORT).show();



        btnSend.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view)
            {
                Float _rating = A.getRating();
                String _email = edtEmail.getText().toString();
                String _phanhoi = edtPhanHoi.getText().toString();
                Send_FeedBack(_rating,_email,_phanhoi);
                ABCD.setVisibility(View.GONE);

            }
        });






    }
}
