package serguma.buenosdias.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import serguma.buenosdias.Alarma;
import serguma.buenosdias.async.DescargaNotificacion;
import serguma.buenosdias.preferens.Preferen;

public class Servicio extends Service {
    public Servicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(getClass().getCanonicalName(), "Se ha ejecutado el servicio");

        DescargaNotificacion descargaNotificacion = new DescargaNotificacion(this);
        descargaNotificacion.execute();

        try {
            int anio = descargaNotificacion.get().getAnio();
            int mes = descargaNotificacion.get().getMes();
            int dia = descargaNotificacion.get().getDia();

            Preferen.guardarFecha(this, dia+"/"+mes+"/"+anio );


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        long nuevoTiempo = Alarma.configuraAlarma(this, Integer.parseInt(Preferen.obtenerTiempo(this)[0]), Integer.parseInt(Preferen.obtenerTiempo(this)[1]), 1);
        Alarma.programarAlarma(this,nuevoTiempo);
        super.onDestroy();
    }
}
