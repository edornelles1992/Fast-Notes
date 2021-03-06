package eduardodornelles.fastnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class NovaNotaActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);

    }

    /*
      Salva os dados informados pelo usuario  ao pressionar 'Avançar' e os repassa para próxima activity (DataActivity)
     */
    public void avancar(View view) {
        //salvando nome
        EditText nomeEditText = (EditText) findViewById(R.id.nome_lembrete);
        Editable nomeEdit = nomeEditText.getText();
        String nome =  nomeEdit.toString();

        EditText detalheEditText = (EditText) findViewById(R.id.detalhes_lembrete);
        Editable detalheEdit = detalheEditText.getText();
        String detalhe =  detalheEdit.toString();

        RadioButton normal = (RadioButton) findViewById(R.id.normal_radio);
        RadioButton importante = (RadioButton) findViewById(R.id.importante_radio);
        RadioButton muitoImportante = (RadioButton) findViewById(R.id.muito_importante_radio);
        String importancia;
        if (normal.isChecked()) {
            CharSequence impEdit = normal.getText();
            importancia =  impEdit.toString();
        } else if(importante.isChecked()){
            CharSequence impEdit = importante.getText();
            importancia =  impEdit.toString();
        } else {
            CharSequence impEdit = muitoImportante.getText();
            importancia =  impEdit.toString();
        }
        Nota novaNota = new Nota();
        novaNota.setNome(nome);
        novaNota.setObservacao(detalhe);
        novaNota.setNivelRelevancia(importancia);


        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtra("Objeto", novaNota);
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) { //caso tenha completado a nota
        setResult(RESULT_OK,data);
       finish();}
        else { //caso o usuario voltou de tela

            Intent intent = data;
            Nota nova = (Nota) intent.getSerializableExtra("Objeto");

            EditText nomeEditText = (EditText) findViewById(R.id.nome_lembrete);
            nomeEditText.setText(nova.getNome());

            EditText detalheEditText = (EditText) findViewById(R.id.detalhes_lembrete);
            detalheEditText.setText(nova.getObservacao());

            RadioButton normal = (RadioButton) findViewById(R.id.normal_radio);
            RadioButton importante = (RadioButton) findViewById(R.id.importante_radio);
            RadioButton muitoImportante = (RadioButton) findViewById(R.id.muito_importante_radio);
            String importancia = nova.getNivelRelevancia();

            if (importancia.equals("Normal")) {
                normal.setChecked(true);
            } else if(importancia.equals("Importante")){
                importante.setChecked(true);

            } else {
                muitoImportante.setChecked(true);
            }

        }
    }
}
