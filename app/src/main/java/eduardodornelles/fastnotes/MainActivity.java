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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int PICK_CONTACT_REQUEST = 1;
    private ArrayList<Nota> listaNotas;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
          if(lerDados()!=null)
          {  listaNotas = lerDados();
              PreencherListV(); }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void PreencherListV(){ //chama caso tenha dados salvos para abrir

        ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(this,
                android.R.layout.simple_list_item_1, listaNotas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                ((TextView) view).setBackgroundColor(Color.parseColor("#e7eecc"));


                return view;
            }

        };


        ListView listaDeNotasView = (ListView) findViewById(R.id.list_notas);
        listaDeNotasView.setAdapter(adapter);

        listaDeNotasView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //clicar em uma nota para editar no listview
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Nota notaAtual = listaNotas.get(position);
                editarNota(notaAtual, position);
            }
        });

    }


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


            ArrayAdapter<Nota> adapter = new ArrayAdapter<Nota>(this,
                    android.R.layout.simple_list_item_1, listaNotas) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);

                    // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                    ((TextView) view).setBackgroundColor(Color.parseColor("#e7eecc"));


                    return view;
                }

            };


            ListView listaDeNotasView = (ListView) findViewById(R.id.list_notas);
            listaDeNotasView.setAdapter(adapter);

            listaDeNotasView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //clicar em uma nota para editar no listview
                @Override
                public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                    Nota notaAtual = listaNotas.get(position);
                    editarNota(notaAtual, position);
                }
            });

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
        posicao = position;
        Intent intent = new Intent(this, EditarNotaActivity.class);
        intent.putExtra("Objeto", notaAtual);

        startActivityForResult(intent,2);

    }

    /*
     Adicionar um novo lembrete
     vai para NovaNotaActivity para preencher os dados da nova nota
      */

    public void adicionarNota(View view) {
        Intent NovaNota = new Intent(this, NovaNotaActivity.class);

        startActivityForResult(NovaNota,1);
    }



   public void gravarDados() throws IOException {
       String FILENAME = "savenotas_file";
       File file =getFileStreamPath(FILENAME);

       FileOutputStream fos = new FileOutputStream(file);
       ObjectOutputStream oos = new ObjectOutputStream(fos);
       oos.writeObject(listaNotas);
       oos.close();
       fos.close();
   }

   public ArrayList<Nota> lerDados() throws IOException, ClassNotFoundException {
       String FILENAME = "savenotas_file";
       File file =getFileStreamPath(FILENAME);

       FileInputStream fis = new FileInputStream(file);
       ObjectInputStream ois = new ObjectInputStream(fis);
       ArrayList<Nota> lista = (ArrayList<Nota>) ois.readObject();
       fis.close();
       ois.close();

       return lista;
   }

}
