package app.android.da_android_tour_manager.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import app.android.da_android_tour_manager.LoginActivity;
import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.model.ChiTietTour;
import app.android.da_android_tour_manager.model.Tour;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourDetailFragment extends Fragment {

    TextView txtName, moTa, txtMoTa1, txtMoTa2, txtMoTa3;
    ImageView imgTour, hinh1, hinh2;
    Button btnDatTour, btnReview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, chiTietRef;

    Button btnOk, btnCancel;

    Tour tourDetail;

    public TourDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toast.makeText(getContext(), TourCommon.tourId,Toast.LENGTH_SHORT).show();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tour");

        txtName = view.findViewById(R.id.txtTourDetailName);
        moTa = view.findViewById(R.id.txtMoTa);
        imgTour = view.findViewById(R.id.imgTourDetail);
        btnDatTour = view.findViewById(R.id.btnDatTour);
        btnReview = view.findViewById(R.id.btnReview);

        hinh1 = view.findViewById(R.id.hinh1);
        hinh2 = view.findViewById(R.id.hinh2);
        txtMoTa1 = view.findViewById(R.id.txtMoTa1);
        txtMoTa2 = view.findViewById(R.id.txtMoTa2);
        txtMoTa3 = view.findViewById(R.id.txtMoTa3);

        String idTour = TourCommon.tourId;
        databaseReference.child(idTour).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tourDetail = dataSnapshot.getValue(Tour.class);
                txtName.setText(tourDetail.getName());
                moTa.setText(tourDetail.getMoTa());
                Picasso.get().load(tourDetail.getHinhAnh()).into(imgTour);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        chiTietRef = FirebaseDatabase.getInstance().getReference("ChiTietTour");
        chiTietRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    ChiTietTour chiTietTour = item.getValue(ChiTietTour.class);
                    if(chiTietTour.getMaTour().equals(TourCommon.tourId))
                    {
                        txtMoTa1.setText(chiTietTour.getMoTa1());
                        txtMoTa2.setText(chiTietTour.getMoTa2());
                        txtMoTa3.setText(chiTietTour.getMoTa3());
                        Picasso.get().load(chiTietTour.getHinh1()).into(hinh1);
                        Picasso.get().load(chiTietTour.getHinh2()).into(hinh2);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnReview.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view)
            {

                FeedBack frament = new FeedBack();
                loadFragment(frament);
            }
        });


        btnDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserCommon.checkDangNhap != 1) {
                    final Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_dangnhap);
                    btnOk = dialog.findViewById(R.id.btn_ok);
                    btnCancel = dialog.findViewById(R.id.btn_cancel);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });

                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }
                else
                {
                    ThanhToanFragment fragment = new ThanhToanFragment();
                    //Toast.makeText(getContext(),tourDetail.getPhuongTien(), Toast.LENGTH_LONG).show();
                    loadFragment(fragment);
                }
            }
        });

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

}
