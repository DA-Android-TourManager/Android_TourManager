package app.android.da_android_tour_manager.ViewHolder;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.android.da_android_tour_manager.R;

public class LoaiTourViewHolder extends RecyclerView.ViewHolder {

    public Button btnLoaiTour;

    public LoaiTourViewHolder(@NonNull View itemView) {
        super(itemView);

        btnLoaiTour = itemView.findViewById(R.id.btnLoaiTour);
    }
}
