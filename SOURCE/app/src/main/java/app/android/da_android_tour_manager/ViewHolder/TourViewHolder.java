package app.android.da_android_tour_manager.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.android.da_android_tour_manager.R;

public class TourViewHolder extends RecyclerView.ViewHolder {

    public ImageButton imageButton;
    public TextView txtName;

    public TourViewHolder(@NonNull View itemView) {
        super(itemView);

        imageButton = itemView.findViewById(R.id.imgTour);
        txtName = itemView.findViewById(R.id.txtTourName);
    }
}
