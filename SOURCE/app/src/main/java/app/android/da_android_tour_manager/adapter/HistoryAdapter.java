package app.android.da_android_tour_manager.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.ViewHolder.HistoryViewHolder;
import app.android.da_android_tour_manager.model.DatTour;
import app.android.da_android_tour_manager.model.KhachHang;
import app.android.da_android_tour_manager.model.PhuongTien;
import app.android.da_android_tour_manager.model.Tour;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    Context context;
    ArrayList<DatTour> datTourArrayList;
    ArrayList<String> arrayListKey;

    // show dialog thong tin dat tour
    Button buttonOk;
    TextView txtName, txtSLSNL, txtSSLTE, txtThanhTien, txtNgayDat, txtPhuongTien, txtNgayKhoiHanh;

    public HistoryAdapter(Context context, ArrayList<DatTour> datTourArrayList, ArrayList<String> arrayListKey) {
        this.context = context;
        this.datTourArrayList = datTourArrayList;
        this.arrayListKey = arrayListKey;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        final HistoryViewHolder historyViewHolder = new HistoryViewHolder(view);

        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, final int position) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tour");
        ref.child(datTourArrayList.get(position).getTourKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Tour tour = dataSnapshot.getValue(Tour.class);
                Picasso.get().load(tour.getHinhAnh()).into(holder.imgTour);
                holder.tourName.setText(tour.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.imgTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_history);
                buttonOk = dialog.findViewById(R.id.dialog_ok);
                txtName = dialog.findViewById(R.id.txtName);
                txtSLSNL = dialog.findViewById(R.id.txtSLNL);
                txtSSLTE = dialog.findViewById(R.id.txtSLTE);
                txtThanhTien = dialog.findViewById(R.id.txtThanhTien);
                txtNgayDat = dialog.findViewById(R.id.txtNgayDat);
                txtNgayKhoiHanh = dialog.findViewById(R.id.txtNgayDi);
                txtPhuongTien = dialog.findViewById(R.id.txtPhuongTien);

                DatabaseReference datTourRef = FirebaseDatabase.getInstance().getReference("DatTour");
                datTourRef.child(arrayListKey.get(position)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        DatTour dt = dataSnapshot.getValue(DatTour.class);
                        txtNgayDat.setText(dt.getNgayDat());
                        txtSLSNL.setText(dt.getSoLuongNL());
                        txtSSLTE.setText(dt.getSoLuongTE());
                        txtThanhTien.setText(dt.getThanhTien());

                        DatabaseReference khRef = FirebaseDatabase.getInstance().getReference("KhachHang");
                        khRef.child(dt.getMaKH()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                KhachHang kh = dataSnapshot.getValue(KhachHang.class);
                                txtName.setText(kh.getName());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        DatabaseReference tourRef = FirebaseDatabase.getInstance().getReference("Tour");
                        tourRef.child(dt.getTourKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Tour tour = dataSnapshot.getValue(Tour.class);
                                txtNgayKhoiHanh.setText(tour.getNgayDi());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        DatabaseReference phuongTienRef = FirebaseDatabase.getInstance().getReference("PhuongTien");
                        phuongTienRef.child(dt.getPhuongTienKey()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                PhuongTien phuongTien = dataSnapshot.getValue(PhuongTien.class);
                                txtPhuongTien.setText(phuongTien.getName());
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

                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datTourArrayList.size();
    }
}
