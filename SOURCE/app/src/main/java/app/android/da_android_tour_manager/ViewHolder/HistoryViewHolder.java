package app.android.da_android_tour_manager.ViewHolder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.android.da_android_tour_manager.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public ImageButton imgTour;
    public TextView tourName;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imgTour = itemView.findViewById(R.id.imgHistory);
        tourName = itemView.findViewById(R.id.txtTourName);
    }
}
