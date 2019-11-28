package app.android.da_android_tour_manager.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.android.da_android_tour_manager.MainActivity;
import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.common.DatVeCommon;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.model.DatTour;
import app.android.da_android_tour_manager.model.KhachHang;
import app.android.da_android_tour_manager.model.PhuongTien;
import app.android.da_android_tour_manager.model.Tour;

/**
 * A simple {@link Fragment} subclass.
 */
public class XacNhanFragment extends Fragment {

    TextView  tenTourXacNhan, tenKHXacNhan, SLNguoiLon, SLTreEm, tongTienXacNhan, txtPhuongTien, txtLoaiPhuongTien;
    Button xacNhan, comeback, closeDialog, btnOk_Huytour;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, datTourRef, khachHangRef;

    Tour tour;

    public XacNhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xac_nhan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tenTourXacNhan = view.findViewById(R.id.tenTourXacNhan);
        tenKHXacNhan = view.findViewById(R.id.tenKHXacNhan);
        SLNguoiLon = view.findViewById(R.id.SLNguoiLon);
        SLTreEm = view.findViewById(R.id.SLTreEm);
        tongTienXacNhan = view.findViewById(R.id.tongTienXacNhan);
        xacNhan = view.findViewById(R.id.xacnhan);
        comeback = view.findViewById(R.id.comeback);
        txtLoaiPhuongTien = view.findViewById(R.id.loaiPT);
        txtPhuongTien = view.findViewById(R.id.phuongTienName);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Tour");

        tour = new Tour();

        databaseReference.child(TourCommon.tourId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tour = dataSnapshot.getValue(Tour.class);
                tenTourXacNhan.setText(tour.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SLNguoiLon.setText(DatVeCommon.slNL);
        SLTreEm.setText(DatVeCommon.slTE);
        tongTienXacNhan.setText(DatVeCommon.thanhTien + "");
        txtPhuongTien.setText(DatVeCommon.phuongTien);
        txtLoaiPhuongTien.setText(DatVeCommon.loaiPT);



        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sendEmail();

                // Lấy ngày hiện tại
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);

                //Toast.makeText(getContext(), UserCommon.maKhachHang, Toast.LENGTH_LONG).show();

                // cập nhật sức chứa của phương tiện

                final DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("PhuongTien");
                final String key = tour.getPhuongTienKey();
                int sucChuaUpdate = DatVeCommon.sucChua - (Integer.parseInt(DatVeCommon.slNL) + Integer.parseInt(DatVeCommon.slTE));
                if(sucChuaUpdate <= 0)
                {
                    updateSucChua(updateRef, key, DatVeCommon.sucChua);
                    // LOAD THONG BAO HUY DO VUOT SO LUONG
                    final Dialog dialog_huyTour = new Dialog(getContext());
                    dialog_huyTour.setContentView(R.layout.dialog_huytour);
                    btnOk_Huytour = dialog_huyTour.findViewById(R.id.btn_ok_huyTour);

                    btnOk_Huytour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadFragment(new HomeFragment());
                            dialog_huyTour.cancel();
                        }
                    });

                    dialog_huyTour.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog_huyTour.show();

                }
                else
                {
                    updateSucChua(updateRef, key, sucChuaUpdate);
                    DatTour datTour =  new DatTour(TourCommon.tourId,
                            date.toString(),
                            SLNguoiLon.getText().toString(),
                            SLTreEm.getText().toString(),
                            tongTienXacNhan.getText().toString(),
                            TourCommon.phuongTienKey,
                            UserCommon.maKhachHang
                    );

                    datTourRef = FirebaseDatabase.getInstance().getReference("DatTour");
                    datTourRef.child(datTourRef.push().getKey()).setValue(datTour);

                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog);
                    closeDialog = dialog.findViewById(R.id.close_dialog);
                    closeDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadFragment(new HomeFragment());
                            dialog.cancel();
                        }
                    });

                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                }
            }

        });

        comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

/*    public void sendEmail(){
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Thông tin đặt Tour"+ "&body=" + "Thời gian chuyến đi: " + tour.getNgayDi() + "->" + tour.getNgayVe() + "&to=" + emailXacNhan.getText().toString());
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));

    }*/

    public void updateSucChua(DatabaseReference ref, String key, int value){
        ref = FirebaseDatabase.getInstance().getReference("PhuongTien");
        ref.child(key).child("sucChua").setValue(value);
    }
}
