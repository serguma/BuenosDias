package serguma.buenosdias.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import serguma.buenosdias.Utils;
import serguma.buenosdias.modelo.BuenosDias;
import serguma.buenosdias.preferens.Preferen;

/**
 * Created by sergu on 22/05/2017.
 */

public class DescargaNotificacion extends AsyncTask<Void, Void, BuenosDias> {

    private static final String URL_SERVER = "http://femxa-ebtm.rhcloud.com/BuenosDiasBebe?fecha=";
    private Context ctx;

    public DescargaNotificacion(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected BuenosDias doInBackground(Void... params) {
        BuenosDias notificacion = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        int response = 0;
        Gson gson = new Gson();

        try{

            url = new URL(URL_SERVER);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            response = httpURLConnection.getResponseCode();


            switch (response){

                case HttpURLConnection.HTTP_NO_CONTENT:

                    Log.d(getClass().getCanonicalName(), "No hay notificación");

                    break;

                case HttpURLConnection.HTTP_OK:

                    Log.d(getClass().getCanonicalName(), "OK");
                    inputStream = httpURLConnection.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream, "iso-8859-1");

                    notificacion = gson.fromJson(inputStreamReader, BuenosDias.class);

                    inputStream.close();

                    break;

                default:
                    Log.e(getClass().getCanonicalName(), "ERROR!!!!");

            }


        }catch(Throwable t){
            //Toast.makeText(ctx, "Ha ocurrido un problema", Toast.LENGTH_LONG).show();
            Log.e(getClass().getCanonicalName(), "Error "+ t);
        }finally {

            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }

        }

        return notificacion;
    }

    @Override
    protected void onPostExecute(BuenosDias notificacion) {

        //Lanzamos la notificación
        try {

            //comprobamos la fecha
            String fechaNoti = Preferen.obtenerFecha(ctx);

            //TODO hacerlo método
            //Convierto el String En fecha
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date datePref = simpleDateFormat.parse(fechaNoti , new ParsePosition(0));

            Calendar fechaActual = Calendar.getInstance();
            Calendar fechaPref = Calendar.getInstance();
            fechaPref.setTime(datePref);

            if ( !fechaPref.before(fechaActual) ){
                Log.d(getClass().getCanonicalName(), "Lanzo");
                Utils.lanzarNotiImagen(notificacion,ctx);
            }else{
                Log.d(getClass().getCanonicalName(), "No lanzo");
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onPostExecute(notificacion);
    }
}
