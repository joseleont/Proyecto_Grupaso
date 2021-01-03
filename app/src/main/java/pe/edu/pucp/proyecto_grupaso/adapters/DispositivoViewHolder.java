package pe.edu.pucp.proyecto_grupaso.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pe.edu.pucp.proyecto_grupaso.R;

public class DispositivoViewHolder extends RecyclerView.ViewHolder {

    private ImageView devicePhoto;
    private TextView deviceName;
    private TextView deviceBrand;
    private TextView deviceType;
    private TextView deviceCount;

    public DispositivoViewHolder(@NonNull View itemView) {
        super(itemView);
        devicePhoto = itemView.findViewById(R.id.deviceImage);
        deviceName = itemView.findViewById(R.id.deviceName);
        deviceBrand = itemView.findViewById(R.id.deviceBrand);
        deviceType = itemView.findViewById(R.id.deviceType);
        deviceCount = itemView.findViewById(R.id.deviceCount);
    }

    public ImageView getDevicePhoto() {
        return devicePhoto;
    }

    public TextView getDeviceName() {
        return deviceName;
    }

    public TextView getDeviceBrand() {
        return deviceBrand;
    }

    public TextView getDeviceType() {
        return deviceType;
    }

    public TextView getDeviceCount() {
        return deviceCount;
    }
}
