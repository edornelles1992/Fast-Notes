package eduardodornelles.fastnotes;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eduardo on 10/03/2017.
 */

public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<ItemListView> itens;

    public AdapterListView(Context context, ArrayList<ItemListView> itens) {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount() {
        return itens.size();
    }


    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public ItemListView getItem(int position) {
        return itens.get(position);
    }

    /**
     * Sem implementaÃ§Ã£o
     *
     * @param position
     * @return
     */
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        //Pega o item de acordo com a posÃ§Ã£o.
        ItemListView item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_list, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informaÃ§Ãµes.
        ((TextView) view.findViewById(R.id.text)).setText(item.getTexto());
        (view.findViewById(R.id.text)).setBackgroundColor(Color.parseColor(item.getCor()));
      //  TextView view_instance = (TextView) view.findViewById(R.id.text);
       // view_instance.getLayoutParams().width = -200;
     //  view_instance.requestLayout();

      //  ((TextView) view.findViewById(R.id.text)).setLayoutParams(new FrameLayout.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 0));
    //    ((ImageView) view.findViewById(R.id.imagemview)).setImageResource(item.getIconeRid());

        return view;
    }
}

