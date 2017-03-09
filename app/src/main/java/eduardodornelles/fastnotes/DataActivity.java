package eduardodornelles.fastnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Date;

public class DataActivity extends AppCompatActivity {
    private Nota nova;
    private boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Intent intent = getIntent();
       nova = (Nota) intent.getSerializableExtra("Objeto");
        boolean editado = intent.getBooleanExtra("Editado",false);
        if(editado){
            edit = true;
            TextView titulo = (TextView) findViewById(R.id.titulo_txtv);
            titulo.setText("Editar Lembrete");
        }
    }

    public void avancar(View view) {
        CalendarView dataCV = (CalendarView) findViewById(R.id.calendarView);
     //   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //formatar a data padrão PT-BR
     //   String dataSelecionada = sdf.format(new Date(dataCV.getDate()));
        Date dataSelecionada = new Date(dataCV.getDate());
        nova.setData(dataSelecionada);

        Intent intent = new Intent(this, HoraActivity.class);
        intent.putExtra("Objeto", nova);
        intent.putExtra("Editado", edit);
        startActivityForResult(intent,1);



    }

     public void onBackPressed() {

         Intent intent = new Intent(this, NovaNotaActivity.class);
         intent.putExtra("Objeto", nova);

         setResult(RESULT_CANCELED,intent);
                 finish();
        return;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            finish();
        }
        else {

        }
    }


}
