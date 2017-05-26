package serguma.buenosdias.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import serguma.buenosdias.R;

public class Historial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        //TODO hacer historial

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }
}
