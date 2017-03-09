package eduardodornelles.fastnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EditarNotaActivity extends AppCompatActivity {
    private Nota atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_nota);

        Intent intent = getIntent();
        atual = (Nota) intent.getSerializableExtra("Objeto");

        EditText nomeEditText = (EditText) findViewById(R.id.nome_lembrete);
        nomeEditText.setText(atual.getNome());
        EditText detalheEditText = (EditText) findViewById(R.id.detalhes_lembrete);
        detalheEditText.setText(atual.getObservacao());

        String relevancia = atual.getNivelRelevancia();

        RadioButton normal = (RadioButton) findViewById(R.id.normal_radio);
        RadioButton importante = (RadioButton) findViewById(R.id.importante_radio);
        RadioButton muitoImportante = (RadioButton) findViewById(R.id.muito_importante_radio);

        if (relevancia.equals("Normal")) {
            normal.setChecked(true);
        } else if(relevancia.equals("Importante")){
            importante.setChecked(true);

        } else {
            muitoImportante.setChecked(true);
        }
    }


 /*
    ativado quando clicado em 'Confirmar'
    passa as alterações de volta para a MainActivity
     */
    public void salvarAlteracao(View view) {

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

        atual.setNome(nome);
        atual.setObservacao(detalhe);
        atual.setNivelRelevancia(importancia);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Objeto", atual);

        setResult(2,intent);
        finish();

    }

    public void cancelarAlteracao(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void editarDataHora(View view) {
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

        atual.setNome(nome);
        atual.setObservacao(detalhe);
        atual.setNivelRelevancia(importancia);

        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtra("Objeto", atual);
        intent.putExtra("Editado", true);
        startActivityForResult(intent,1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            setResult(2, data);
            finish();
        }
        else {
            Intent intent = getIntent();
            atual = (Nota) intent.getSerializableExtra("Objeto");

            EditText nomeEditText = (EditText) findViewById(R.id.nome_lembrete);
            nomeEditText.setText(atual.getNome());
            EditText detalheEditText = (EditText) findViewById(R.id.detalhes_lembrete);
            detalheEditText.setText(atual.getObservacao());

            String relevancia = atual.getNivelRelevancia();

            RadioButton normal = (RadioButton) findViewById(R.id.normal_radio);
            RadioButton importante = (RadioButton) findViewById(R.id.importante_radio);
            RadioButton muitoImportante = (RadioButton) findViewById(R.id.muito_importante_radio);

            if (relevancia.equals("Normal")) {
                normal.setChecked(true);
            } else if(relevancia.equals("Importante")){
                importante.setChecked(true);

            } else {
                muitoImportante.setChecked(true);
            }
        }
    }

        public void removerNota(View view) {
            setResult(3,getIntent());
            finish();
        }
    }

