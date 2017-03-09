package eduardodornelles.fastnotes;

import android.app.Activity;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eduardo on 07/03/2017.
 */

public class AdapterPersonalizado extends BaseAdapter {

    private final List<Nota> listaNotas;
    private final Activity act;

    public AdapterPersonalizado(List<Nota> listaNotas, Activity act) {
        this.listaNotas = listaNotas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return listaNotas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaNotas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        Nota nova = listaNotas.get(position);



        TextView campo  = (TextView)convertView;

        campo.setText(nova.getNome() + "\n" + nova.toString());


        return convertView;
    }
}
