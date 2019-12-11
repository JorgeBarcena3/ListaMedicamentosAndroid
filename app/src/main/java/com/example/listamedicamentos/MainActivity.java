package com.example.listamedicamentos;

import android.content.Intent;
import android.os.Bundle;

import com.example.listamedicamentos.DBHelper.MedicamentosDbHelper;
import com.example.listamedicamentos.ListAdapter.MedicamentoAdapter;
import com.example.listamedicamentos.entities.Medicamento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Items de la lista
    private ListView LVItems;

    //Adaptador de los medicamentos
    private MedicamentoAdapter adaptador;

    //Helper de la base de datos
    MedicamentosDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent activity = new Intent(getApplicationContext(), MedicamentosEditActivity.class);
               startActivity(activity);
            }
        });


        dbHelper = new MedicamentosDbHelper(this);
        ArrayList<Medicamento> med = dbHelper.getAllMedicamentos();

        LVItems = (ListView) findViewById(R.id.LVItems);
        adaptador = new MedicamentoAdapter(this, dbHelper.getAllMedicamentos() );
        LVItems.setAdapter(adaptador);

    }


    @Override
    protected void onResume() {

        super.onResume();
        adaptador = new MedicamentoAdapter(this, dbHelper.getAllMedicamentos() );
        LVItems.setAdapter(adaptador);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar layout_list_view_row_items clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
