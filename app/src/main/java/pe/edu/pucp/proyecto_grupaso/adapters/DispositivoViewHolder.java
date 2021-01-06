package pe.edu.pucp.proyecto_grupaso.adapters;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pe.edu.pucp.proyecto_grupaso.R;

public class DispositivoViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener{

    private ImageView devicePhoto;
    private TextView deviceName;
    private TextView deviceBrand;
    private TextView deviceType;
    private TextView deviceCount;

    public DispositivoViewHolder(@NonNull View itemView) {
        super(itemView);
        devicePhoto = itemView.findViewById(R.id.deviceImage);
        deviceName = itemView.findViewById(R.id.deviceName);
        deviceBrand = itemView.findViewById(R.id.caracDispositivo);
        deviceType = itemView.findViewById(R.id.tipoDispositivo);
        deviceCount = itemView.findViewById(R.id.cantidadDispositivo);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem reservar = menu.add(Menu.NONE, 1, 1, "Reservar dispositivo");
        reservar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("myapp", "onMenuItemClick: " + menuInfo.toString());
                return true;
            }
        });
    }
}
