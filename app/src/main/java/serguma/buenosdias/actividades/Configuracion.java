package serguma.buenosdias.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import serguma.buenosdias.listener.EscuchaGuardar;
import serguma.buenosdias.preferens.Preferen;
import serguma.buenosdias.R;
import serguma.buenosdias.services.Servicio;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        ToggleButton activo = (ToggleButton) findViewById(R.id.activo);
        TimePicker timepicker = (TimePicker) findViewById(R.id.Hora);
        timepicker.setIs24HourView(true);

        Button guardar = (Button) findViewById(R.id.guardar);

        EscuchaGuardar escuchaGuardar = new EscuchaGuardar(this);
        guardar.setOnClickListener(escuchaGuardar);

        //cargamos los valores de las preferen
        //Utilizo current en el Timepicker, porque es compatible con versiones inferiores a la 23
        activo.setChecked( Preferen.activada(this) );

        if(activo.isChecked()){
            Intent intent = new Intent(this, Servicio.class);
            startService(intent);
        }

        timepicker.setCurrentHour(Integer.parseInt(Preferen.obtenerTiempo(this)[0]));
        timepicker.setCurrentMinute(Integer.parseInt(Preferen.obtenerTiempo(this)[1]));

        Log.d(getClass().getCanonicalName(), "TIEMPO :" + Preferen.obtenerTiempo(this)[0]);
        Log.d(getClass().getCanonicalName(), "ESTADO :" + Preferen.activada(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }
}
