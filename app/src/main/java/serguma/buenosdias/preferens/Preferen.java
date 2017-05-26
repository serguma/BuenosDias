package serguma.buenosdias.preferens;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sergu on 24/05/2017.
 */

public class Preferen {

    private Context ctx;

    public static boolean activada(Context ctx){

        boolean activa = false;

        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("noti_activa", Context.MODE_PRIVATE);
        activa = sharedPreferences.getBoolean("noti_activa", false);

        return activa;
    }

    public static void cambiarEstado(Context ctx, boolean estado){

        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("noti_activa", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("noti_activa", estado);
        editor.commit();

    }

    public static void guardarTiempo(Context ctx, int hora, int minuto){

        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tiempo", hora+":"+minuto);
        editor.commit();

    }

    public static String[] obtenerTiempo(Context ctx){

        String[] tiempo = null;
        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("tiempo", Context.MODE_PRIVATE);
        tiempo = sharedPreferences.getString("tiempo", "12:00").split(":");

        return tiempo;
    }

    public static void guardarFecha(Context ctx, String fecha){

        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("fecha", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fecha", fecha);
        editor.commit();

    }

    public static String obtenerFecha(Context ctx){

        String fecha = null;
        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("fecha", Context.MODE_PRIVATE);
        fecha = sharedPreferences.getString("fecha", "18/05/2017");

        return fecha;
    }

    public static String[] obtenerImagenes(Context ctx){

        String[] imagenes = null;
        SharedPreferences sharedPreferences =  ctx.getSharedPreferences("imagenes", Context.MODE_PRIVATE);
        imagenes = sharedPreferences.getString("imagenes", "").split("--");

        return imagenes;
    }


}
