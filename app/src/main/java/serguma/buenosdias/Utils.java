package serguma.buenosdias;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import java.util.concurrent.ExecutionException;
import serguma.buenosdias.actividades.VerNotificacion;
import serguma.buenosdias.modelo.BuenosDias;

/**
 * Created by sergu on 21/05/2017.
 */

public class Utils {

    public static void lanzarNotiImagen(BuenosDias buenosDias, Context ctx) throws ExecutionException, InterruptedException {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("GMD");
        builder.setContentText("BUENOS DÍAS");
        builder.setAutoCancel(true);


        //paso la imagena bitmap
        Bitmap bitmap = convertirImagen(buenosDias.getFoto());

        Intent intent = new Intent(ctx, VerNotificacion.class);
        intent.putExtra("FOTO", bitmap);

//            byte[] decodedString = Base64.decode(buenosDias.getFoto(), Base64.DEFAULT);
//            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, (int)System.currentTimeMillis(),intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

        //lanzo la notificación
        NotificationManager notificationManager = (NotificationManager)  ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        notificationManager.notify(200, notification);

    }

    public static Bitmap resizeFoto(Bitmap bitmap){

        Bitmap bitmapResize = null;
        float ratio = (float) bitmap.getWidth() / (float) bitmap.getHeight();
        int width = 800;
        int height = Math.round(width / ratio);

        bitmapResize = Bitmap.createScaledBitmap(bitmap, width, height, false);

        return bitmapResize;

    }

    public static Bitmap convertirImagen(String base64){
        Bitmap bitmap = null;

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return bitmap;
    }




}
