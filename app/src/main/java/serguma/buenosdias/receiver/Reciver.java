package serguma.buenosdias.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import serguma.buenosdias.services.Servicio;

public class Reciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, Servicio.class);
        context.startService(intent1);

    }
}
