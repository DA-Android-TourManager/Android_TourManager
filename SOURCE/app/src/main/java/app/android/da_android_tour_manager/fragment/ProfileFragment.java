package app.android.da_android_tour_manager.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.common.UserCommon;
import app.android.da_android_tour_manager.model.KhachHang;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    DatabaseReference userRef, updateRef;
    ImageView imgAvartar;
    TextView txtEmail ;
    EditText txtName, txtDiaChi,  txtSDT;

    Button btnLichSu, btnUpdate;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userRef = FirebaseDatabase.getInstance().getReference("KhachHang");

        txtDiaChi = view.findViewById(R.id.profile_diachi);
        txtEmail = view.findViewById(R.id.profile_email);
        txtName = view.findViewById(R.id.profile_name);
        txtSDT = view.findViewById(R.id.profile_sdt);
        imgAvartar = view.findViewById(R.id.imgKH);
        btnLichSu = view.findViewById(R.id.btnHistory);
        btnUpdate = view.findViewById(R.id.updateProfile);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren())
                {
                    KhachHang kh = item.getValue(KhachHang.class);
                    if(kh.email.equals(UserCommon.email))
                    {
                        Picasso.get().load(kh.getAvartar()).into(imgAvartar);
                        txtName.setText(kh.getName());
                        txtEmail.setText(kh.getEmail());
                        txtDiaChi.setText(kh.getDiaChi());
                        txtSDT.setText(kh.getSdt());
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HistoryFragment()).commit();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile(updateRef, txtName.getText().toString(), txtDiaChi.getText().toString(), txtSDT.getText().toString());
                Toast.makeText(getContext(), "Cập nhập thông tin thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateProfile(DatabaseReference updateRef, String name, String diaChi, String sdt)
    {
        updateRef = FirebaseDatabase.getInstance().getReference("KhachHang");
        updateRef.child(UserCommon.maKhachHang).child("name").setValue(name);
        updateRef.child(UserCommon.maKhachHang).child("diaChi").setValue(diaChi);
        updateRef.child(UserCommon.maKhachHang).child("sdt").setValue(sdt);
    }

}
