package serguma.buenosdias.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import serguma.buenosdias.services.Servicio;

public class ReciverEnciendo extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, Servicio.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent1);

    }
}
