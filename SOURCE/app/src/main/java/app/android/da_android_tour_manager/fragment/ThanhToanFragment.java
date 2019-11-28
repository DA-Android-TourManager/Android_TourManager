package app.android.da_android_tour_manager.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.adapter.DichVuAdapter;
import app.android.da_android_tour_manager.common.DatVeCommon;
import app.android.da_android_tour_manager.common.TourCommon;
import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.model.BangGiaTour;
import app.android.da_android_tour_manager.model.CTCungCapDV;
import app.android.da_android_tour_manager.model.KhachHang;
import app.android.da_android_tour_manager.model.LoaiPhuongTien;
import app.android.da_android_tour_manager.model.PhuongTien;
import app.android.da_android_tour_manager.model.Tour;
import app.android.da_android_tour_manager.model.TourDichVu;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThanhToanFragment extends Fragment {

    // viewholder
    TextView txtLoaiPT, txtPT, txtSucChua;
    static TextView txtTongThanhToan;
    TextView txtThoiGian;
    EditText edtName, edtSDT, edtDiaChi, edtEmail, edtSLNguoiLon, edtSLTreEm;
    Button btnThanhToan;
    RecyclerView recyclerViewDichVu;

    // firebase
    DatabaseReference tourRef, bangGiaTourRef, dichVuRef, phuongTienRef;

    // model
    BangGiaTour bangGiaTour = new BangGiaTour();
    ArrayList<TourDichVu> tourDichVuArrayList;
    ArrayList<CTCungCapDV> ctCungCapDVArrayList;

    // Adapter
    DichVuAdapter dichVuAdapter;

    public ThanhToanFragment() {
        // Required empty public constructor
    }

    public static void loadData(){
        txtTongThanhToan.setText(DatVeCommon.thanhTien + "");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thanh_toan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtLoaiPT = view.findViewById(R.id.loaiPT);
        txtPT = view.findViewById(R.id.chuyenBay);
        txtSucChua = view.findViewById(R.id.sucChua);
        txtTongThanhToan = view.findViewById(R.id.tongThanhToan);
        edtName = view.findViewById(R.id.editName);
        edtDiaChi = view.findViewById(R.id.editDiaChi);
        edtEmail = view.findViewById(R.id.editEmail);
        edtSDT = view.findViewById(R.id.editSDT);
        edtSLNguoiLon = view.findViewById(R.id.edt_SL_NguoiLon);
        edtSLTreEm = view.findViewById(R.id.edt_SL_TreEm);
        btnThanhToan = view.findViewById(R.id.btnThanhToan);
        txtThoiGian = view.findViewById(R.id.thoiGianBay);
        recyclerViewDichVu = view.findViewById(R.id.recyclerDichVu);
        recyclerViewDichVu.setHasFixedSize(true);
        recyclerViewDichVu.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Load data dich vu
        dichVuRef = FirebaseDatabase.getInstance().getReference();
        dichVuRef.child("TourDichVu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tourDichVuArrayList = new ArrayList<>();
                ctCungCapDVArrayList = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    TourDichVu tourDichVu = item.getValue(TourDichVu.class);
                    if(tourDichVu.getTourKey().equals(TourCommon.tourId)) {
                        tourDichVuArrayList.add(tourDichVu);
                    }
                }
                dichVuAdapter = new DichVuAdapter(tourDichVuArrayList, getContext());
                recyclerViewDichVu.setAdapter(dichVuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Data Tour
       tourRef = FirebaseDatabase.getInstance().getReference("Tour");
       tourRef.child(TourCommon.tourId).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Tour tourDetail = dataSnapshot.getValue(Tour.class);
               txtThoiGian.setText(tourDetail.getNgayDi() + " - " + tourDetail.getNgayVe());
               TourCommon.bangGiaTourKey = tourDetail.getBangGiatourKey();
               TourCommon.phuongTienKey = tourDetail.getPhuongTienKey();

               // Kiểm tra khóa của bảng giá => Lấy giá đang áp dụng
               bangGiaTourRef = FirebaseDatabase.getInstance().getReference("BanggiaTour");
               bangGiaTourRef.child(TourCommon.bangGiaTourKey).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       bangGiaTour = dataSnapshot.getValue(BangGiaTour.class);
                       txtTongThanhToan.setText(bangGiaTour.getGiaTour());
                       DatVeCommon.thanhTien = Integer.parseInt(txtTongThanhToan.getText().toString());
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

               // load data phuong tien
               phuongTienRef = FirebaseDatabase.getInstance().getReference("PhuongTien");
               phuongTienRef.child(tourDetail.getPhuongTienKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        PhuongTien pt = dataSnapshot.getValue(PhuongTien.class);
                        txtPT.setText(pt.getName());
                        txtSucChua.setText(pt.getSucChua() + "");

                        DatabaseReference loaiPTRef = FirebaseDatabase.getInstance().getReference("LoaiPhuongTien");
                        loaiPTRef.child(pt.getLoaiPhuongTienKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                LoaiPhuongTien loaiPhuongTien = dataSnapshot.getValue(LoaiPhuongTien.class);
                                txtLoaiPT.setText(loaiPhuongTien.getName());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

       // loadData khách hàng
        DatabaseReference khRef = FirebaseDatabase.getInstance().getReference("KhachHang");
        khRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    KhachHang kh = item.getValue(KhachHang.class);
                    if(kh.getEmail().equals(UserCommon.email))
                    {
                        edtEmail.setText(kh.getEmail());
                        edtName.setText(kh.getName());
                        edtDiaChi.setText(kh.getDiaChi());
                        edtSDT.setText(kh.getSdt());
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       btnThanhToan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DatVeCommon.email = edtEmail.getText().toString();
               DatVeCommon.phuongTien = txtPT.getText().toString();
               DatVeCommon.loaiPT = txtLoaiPT.getText().toString();
               DatVeCommon.slNL = edtSLNguoiLon.getText().toString();
               DatVeCommon.slTE = edtSLTreEm.getText().toString();
               DatVeCommon.sucChua = Integer.parseInt(txtSucChua.getText().toString());
               loadFragment(new XacNhanFragment());
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
