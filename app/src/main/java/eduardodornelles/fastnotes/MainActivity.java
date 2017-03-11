package eduardodornelles.fastnotes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static final int PICK_CONTACT_REQUEST = 1;
    private ArrayList<Nota> listaNotas;
    private int posicao;
    ArrayList<ItemListView> itens;
    private ListView listView;
    private AdapterListView adapterListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
          if(lerDados()!=null)
          {  listaNotas = lerDados();
             Nota.setId(listaNotas.size());
              }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.list);
        //Define o Listener quando alguem clicar no item.
        listView.setOnItemClickListener(this);
        createListView();




    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        if (listaNotas==null) { return;} // lista vazia

            itens = new ArrayList<ItemListView>();
            for (Nota atual: listaNotas){
                ItemListView item = new ItemListView(atual.toString(),atual.getCor());
                item.setIdentificador(atual.getIdentificador());
                itens.add(item);
            }


            //Cria o adapter
            adapterListView = new AdapterListView(this, itens);

            //Define o Adapter
            listView.setAdapter(adapterListView);
            //Cor quando a lista Ã© selecionada para ralagem.
            listView.setCacheColorHint(Color.TRANSPARENT);

    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        ItemListView item = adapterListView.getItem(arg2); // arg2 = posicao
        //DemostraÃ§Ã£o
    //    Toast.makeText(this, "VocÃª Clicou em: " + item.getTexto(), Toast.LENGTH_LONG).show();
        posicao = arg2;
       Nota atual =  listaNotas.get(posicao);
        editarNota(atual,posicao);

    }
   /*
       RESULT_OK - nova nota
        2 - Edição de nota
        3 - Remoção de nota

      */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK ||resultCode == 2 || resultCode == 3 ) {

            if (listaNotas == null) {
                listaNotas = new ArrayList();
            }

            Nota nova = (Nota) data.getSerializableExtra("Objeto"); //recebe a nota nova ou editada

            if (resultCode == RESULT_OK) { //testa se é nova ou editada
                listaNotas.add(nova);
            } else if (resultCode == 2) {
                listaNotas.set(posicao, nova);
                }
                else { //3 - remover nota (veio do EditarNota)
                       listaNotas.remove(posicao);
                     }
            createListView(); //atualizar listview
         }
        try {
            gravarDados();
            } catch (IOException e) {

        }
    }

   /*
   Editar a nota recebida por parametra
   vai para EditarNotaActivity para ser feito as alterações
    */
    public void editarNota(Nota notaAtual, int position){
        Intent intent = new Intent(this, EditarNotaActivity.class);
        intent.putExtra("Objeto", notaAtual);
        startActivityForResult(intent,2);
    }

    public void adicionarNota(View view) {
        Intent NovaNota = new Intent(this, NovaNotaActivity.class);
        startActivityForResult(NovaNota,1);
    }

   public void gravarDados() throws IOException {
       String FILENAME = "savenotas1_file";
       File file =getFileStreamPath(FILENAME);
       FileOutputStream fos = new FileOutputStream(file);
       ObjectOutputStream oos = new ObjectOutputStream(fos);
       oos.writeObject(listaNotas);
       oos.close();
       fos.close();
   }

   public ArrayList<Nota> lerDados() throws IOException, ClassNotFoundException {
       String FILENAME = "savenotas1_file";
       File file =getFileStreamPath(FILENAME);
       FileInputStream fis = new FileInputStream(file);
       ObjectInputStream ois = new ObjectInputStream(fis);
       ArrayList<Nota> lista = (ArrayList<Nota>) ois.readObject();
       fis.close();
       ois.close();
       return lista;
   }

}
