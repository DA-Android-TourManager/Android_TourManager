package app.android.da_android_tour_manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.android.da_android_tour_manager.R;
import app.android.da_android_tour_manager.ViewHolder.DichVuViewHolder;
import app.android.da_android_tour_manager.common.DatVeCommon;
import app.android.da_android_tour_manager.fragment.ThanhToanFragment;
import app.android.da_android_tour_manager.model.TourDichVu;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuViewHolder> {

    ArrayList<TourDichVu> tourDichVuArrayList;
    Context context;

    public DichVuAdapter(ArrayList<TourDichVu> tourDichVuArrayList, Context context) {
        this.tourDichVuArrayList = tourDichVuArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DichVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_dichvu, parent, false);
        final DichVuViewHolder dichVuViewHolder = new DichVuViewHolder(view);

        return dichVuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final DichVuViewHolder holder, int position) {
        holder.checkBox.setText(tourDichVuArrayList.get(position).getName());
        holder.txtPrice.setText(tourDichVuArrayList.get(position).getGiaDichVu());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    // tinh thanh tien o day :))))
                    DatVeCommon.thanhTien += Integer.parseInt(holder.txtPrice.getText().toString());
                    DatVeCommon.slDV += 1;
                    ThanhToanFragment.loadData();
                }
                else {
                    DatVeCommon.thanhTien -= Integer.parseInt(holder.txtPrice.getText().toString());
                    DatVeCommon.slDV -= 1;
                    ThanhToanFragment.loadData();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return tourDichVuArrayList.size();
    }
}
