package serguma.buenosdias;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.util.Log;
import android.widget.Toast;

import serguma.buenosdias.preferens.Preferen;
import serguma.buenosdias.receiver.Reciver;

/**
 * Created by sergu on 23/05/2017.
 */

public class Alarma {


    public static Long configuraAlarma(Context ctx, int hora, int minuto, int control){

        long nuevoMilisegundo = 0;
        Calendar ahora = Calendar.getInstance();

        //Lo programo para el día siguiente día, PERO PARA PROBAR SOLO CAMBIO LA HORA Y LOS MINUTOS

/*        if(control == 1){
            ahora.add(Calendar.DAY_OF_WEEK, 1);
        }*/
        if(control == 1) {
            ahora.add(Calendar.DAY_OF_WEEK, 1);
            Log.d(ctx.getClass().getCanonicalName(), "He entrado: ");
            ahora.set(Calendar.HOUR_OF_DAY, hora);
            ahora.set(Calendar.MINUTE, minuto);
        }else{
            ahora.set(Calendar.HOUR_OF_DAY, hora);
            ahora.set(Calendar.MINUTE, minuto);
        }

        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.format(ahora.getTime());
        String fechaCadena = simpleDateFormat.format(ahora.getTime());

        Preferen.guardarFecha(ctx, fechaCadena.toString());
        Preferen.guardarTiempo(ctx, hora, minuto);

        nuevoMilisegundo = ahora.getTimeInMillis();

        Log.d(ctx.getClass().getCanonicalName(), "Tiempo futuro: " + nuevoMilisegundo);

        return nuevoMilisegundo;
    }


    public static void programarAlarma(Context ctx, long nuevoTiempo){

        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ctx.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        long tiempoActual = calendar.getTimeInMillis();
        long tiempoAlarma = nuevoTiempo;

        Toast.makeText(ctx, "SIGO ACTIVO", Toast.LENGTH_LONG).show();

        Log.d(ctx.getClass().getCanonicalName(), "Tiempo Actual: " + tiempoActual);
        Log.d(ctx.getClass().getCanonicalName(), "Tiempo Alarma: " + tiempoAlarma);

        Intent intent = new Intent(ctx, Reciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,tiempoAlarma,pendingIntent);
    }

    public static void desprogramarAlarma(Context ctx){

        Intent intentAlarm = new Intent(ctx, Reciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 100, intentAlarm, PendingIntent.FLAG_NO_CREATE);
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ctx.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        Log.d(ctx.getClass().getCanonicalName(), "Alarma Parada");
    }

}
