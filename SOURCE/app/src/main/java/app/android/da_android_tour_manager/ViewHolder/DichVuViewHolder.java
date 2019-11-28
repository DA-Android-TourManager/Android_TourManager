package app.android.da_android_tour_manager.ViewHolder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.android.da_android_tour_manager.R;

public class DichVuViewHolder extends RecyclerView.ViewHolder {

    public CheckBox checkBox;
    public TextView txtPrice;

    public DichVuViewHolder(@NonNull View itemView) {
        super(itemView);

        checkBox = itemView.findViewById(R.id.nameDichVu);
        txtPrice = itemView.findViewById(R.id.giaDV);
    }
}
