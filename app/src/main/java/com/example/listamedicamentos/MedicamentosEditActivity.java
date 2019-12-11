package com.example.listamedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.listamedicamentos.DBHelper.MedicamentosDbHelper;
import com.example.listamedicamentos.entities.HORARIO;
import com.example.listamedicamentos.entities.Medicamento;

public class MedicamentosEditActivity extends AppCompatActivity {

    private Medicamento medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        medicamento = (Medicamento) i.getSerializableExtra("medicamento");


        setContentView(R.layout.activity_medicamentos_edit);

        final Button saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = ((TextView)findViewById(R.id.Name)).getText().toString();
                String Dosis = ((TextView)findViewById(R.id.Dosis)).getText().toString();
                Spinner spn = (Spinner)findViewById(R.id.Horario);
                HORARIO Horario = HORARIO.valueOf( spn.getSelectedItem().toString() );
                Boolean Notificaciones = findViewById(R.id.Notificaciones).toString() == "SI" ? true : false;
                String Comentarios = ((TextView)findViewById(R.id.Observaciones)).getText().toString();

                if(medicamento == null)
                    MedicamentosDbHelper.GetInstance().saveMedicamento(new Medicamento(Name, Dosis, Horario, Notificaciones, Comentarios));
                else{

                    Medicamento med = new Medicamento(Name, Dosis, Horario, Notificaciones, Comentarios);
                    med.id = medicamento.id;
                    MedicamentosDbHelper.GetInstance().updateMedicamento(med);

                }


                finish();
            }
        });

        if(medicamento != null)
        {
            ((TextView)findViewById(R.id.Name)).setText(medicamento.getName());
            ((TextView)findViewById(R.id.Dosis)).setText(medicamento.getDosis());
            ((TextView)findViewById(R.id.Observaciones)).setText(medicamento.getObservaciones());
            Spinner spn = (Spinner)findViewById(R.id.Horario);
            spn.setSelection(medicamento.getHorarioEnum()); // 0 SI

            spn = (Spinner)findViewById(R.id.Notificaciones);
            spn.setSelection(medicamento.getNotificaciones() == "Activado" ? 0 : 1); // 0 SI

        }
    }
}
