package com.example.listamedicamentos.ListAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listamedicamentos.DBHelper.MedicamentosDbHelper;
import com.example.listamedicamentos.MedicamentosEditActivity;
import com.example.listamedicamentos.R;
import com.example.listamedicamentos.entities.Medicamento;

import java.util.ArrayList;

/*
* Adaptador del medicamento
* */
public class MedicamentoAdapter extends BaseAdapter {

    //Contexto de la aplicacion
    private Context context;

    //Lista de elementos
    private ArrayList<Medicamento> listItems;

    //Connstructor
    public MedicamentoAdapter(Context _context, ArrayList<Medicamento> _list){
        context = _context;
        listItems = _list;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Medicamento item = (Medicamento) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.layout_list_view_row_items, null);
        TextView name = (TextView) view.findViewById(R.id.nombrelbl);
        TextView dosis = (TextView) view.findViewById(R.id.Dosislbl);
        TextView horario = (TextView) view.findViewById(R.id.Horariolb);
        TextView notificaciones = (TextView) view.findViewById(R.id.Notificacioneslbl);
        TextView comentarios = (TextView) view.findViewById(R.id.Comentarioslbl);


        name.setText(item.getName());
        dosis.setText(item.getDosis());
        horario.setText(item.getHorario());
        notificaciones.setText(item.getNotificaciones());
        comentarios.setText(item.getObservaciones());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this part is important, it lets ListView handle the clicks
                String name = ((TextView) v.findViewById(R.id.nombrelbl)).getText().toString();
                Medicamento medicamento = MedicamentosDbHelper.GetInstance().getMedicamentoByName(name);

                Intent activity = new Intent(context, MedicamentosEditActivity.class);
                activity.putExtra("medicamento", medicamento);
                context.startActivity(activity);

                Log.d("Click", "Click");
            }
        });
        return view;
    }
}
