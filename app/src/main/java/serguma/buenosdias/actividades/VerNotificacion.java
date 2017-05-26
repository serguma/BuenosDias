package serguma.buenosdias.actividades;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import serguma.buenosdias.R;
import serguma.buenosdias.Utils;


public class VerNotificacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notificacion);

        Bitmap bitmap = (Bitmap) getIntent().getParcelableExtra("FOTO");
        ImageView foto = (ImageView) findViewById(R.id.foto);
        Bitmap bitmapEscalado = Utils.resizeFoto(bitmap);
        foto.setImageBitmap(bitmapEscalado);

    }
}
