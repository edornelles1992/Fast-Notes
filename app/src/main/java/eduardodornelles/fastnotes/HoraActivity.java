package eduardodornelles.fastnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class HoraActivity extends AppCompatActivity {

    private Nota nova;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);
        Intent intent = getIntent();
        nova = (Nota) intent.getSerializableExtra("Objeto");
        TimePicker horaTP = (TimePicker) findViewById(R.id.horario_lembrete);
        horaTP.setIs24HourView(true); //trocar para horario de 00:00 a 24:00
        boolean editado = intent.getBooleanExtra("Editado",false);
        if(editado){
            TextView titulo = (TextView) findViewById(R.id.titulo_txtv);
            titulo.setText("Editar Lembrete");
        }

    }

    public void salvar(View view) {

        TimePicker horaTP = (TimePicker) findViewById(R.id.horario_lembrete);
        String horarioSelecionado;

        if (horaTP.getHour()<10 && horaTP.getMinute()<10){
             horarioSelecionado = "0" + Integer.toString(horaTP.getHour()) + ":0" + Integer.toString(horaTP.getMinute());
        } else if (horaTP.getHour()<10){
             horarioSelecionado = "0" + Integer.toString(horaTP.getHour()) + ":" + Integer.toString(horaTP.getMinute());
        } else if (horaTP.getMinute()<10){
             horarioSelecionado = Integer.toString(horaTP.getHour()) + ":0" + Integer.toString(horaTP.getMinute());
        } else {
             horarioSelecionado = Integer.toString(horaTP.getHour()) + ":" + Integer.toString(horaTP.getMinute());
        }

        nova.setHorario(horarioSelecionado);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Objeto", nova);
        setResult(RESULT_OK,intent);
        finish();

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtra("Objeto", nova);
        setResult(RESULT_CANCELED,intent);
        finish();
        return;
    }


}
