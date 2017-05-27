package serguma.buenosdias.listener;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import serguma.buenosdias.Alarma;
import serguma.buenosdias.preferens.Preferen;
import serguma.buenosdias.R;

/**
 * Created by sergu on 25/05/2017.
 */

public class EscuchaGuardar implements View.OnClickListener {

    private Context ctx;

    public EscuchaGuardar(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onClick(View v) {

        Activity activity = (Activity) ctx;
        ToggleButton activo = (ToggleButton) activity.findViewById(R.id.activo);
        TimePicker timepicker = (TimePicker) activity.findViewById(R.id.Hora);

        if (activo.isChecked()) {
            Preferen.cambiarEstado(ctx,true);
            //Asigno el nuevo tiempo a la alarma
            long nuevoTiempo = Alarma.configuraAlarma(ctx, timepicker.getCurrentHour(), timepicker.getCurrentMinute(), 1);
            Alarma.programarAlarma(ctx, nuevoTiempo);
        } else {
            Alarma.desprogramarAlarma(ctx);
            Preferen.cambiarEstado(ctx,false);
        }

        Toast toast = Toast.makeText(ctx, "Configuración Guardada", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
        toast.show();



    }
}
