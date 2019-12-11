package com.example.listamedicamentos.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.listamedicamentos.DBContract.MedicamentosContract;
import com.example.listamedicamentos.entities.HORARIO;
import com.example.listamedicamentos.entities.Medicamento;

import java.util.ArrayList;

/*
* Helper para obtener informacion de las base de dats
* */
public class MedicamentosDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Medicamentos.db";
    public static MedicamentosDbHelper instance;


    public static MedicamentosDbHelper GetInstance(){
        return  instance;
    }

    public MedicamentosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        instance = this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creamos la base de datos
        db.execSQL("CREATE TABLE " + MedicamentosContract.MedicamentoEntry.TABLE_NAME + " ("
                + MedicamentosContract.MedicamentoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MedicamentosContract.MedicamentoEntry.ID + " TEXT NOT NULL,"
                + MedicamentosContract.MedicamentoEntry.NAME + " TEXT NOT NULL,"
                + MedicamentosContract.MedicamentoEntry.DOSIS + " TEXT NOT NULL,"
                + MedicamentosContract.MedicamentoEntry.HORARIO + " TEXT NOT NULL,"
                + MedicamentosContract.MedicamentoEntry.NOTIFICACIONES + " BOOLEAN NOT NULL,"
                + MedicamentosContract.MedicamentoEntry.OBSERVACIONES+ " TEXT)"
             );

        ///Introducimos un valor
        // Contenedor de valores
        ContentValues row = new ContentValues();

        // Pares clave-valor
        row.put(MedicamentosContract.MedicamentoEntry.ID, "001");
        row.put(MedicamentosContract.MedicamentoEntry.NAME, "TEST");
        row.put(MedicamentosContract.MedicamentoEntry.DOSIS, "1L/mg");
        row.put(MedicamentosContract.MedicamentoEntry.HORARIO, "MAÑANA");
        row.put(MedicamentosContract.MedicamentoEntry.NOTIFICACIONES, "FALSE");
        row.put(MedicamentosContract.MedicamentoEntry.OBSERVACIONES, " ");

        // Insertar...
        db.insert(MedicamentosContract.MedicamentoEntry.TABLE_NAME, null, row);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveMedicamento(Medicamento medicamento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                MedicamentosContract.MedicamentoEntry.TABLE_NAME,
                null,
                medicamento.toContentValues());

    }

    public long updateMedicamento(Medicamento medicamento) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.update(
                MedicamentosContract.MedicamentoEntry.TABLE_NAME,
                medicamento.toContentValues(),
                MedicamentosContract.MedicamentoEntry._ID + "=" + medicamento.id,
                null
                );

    }

    public  Medicamento getMedicamentoByName(String _name)
    {
        Cursor cursor = getReadableDatabase().query(
                MedicamentosContract.MedicamentoEntry.TABLE_NAME,
                null,
                MedicamentosContract.MedicamentoEntry.NAME + " LIKE ?",
                new String[]{_name},
                null,
                null,
                null);

        if(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NAME));
            String dosis  = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.DOSIS));
            HORARIO horario =  HORARIO.valueOf( cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.HORARIO)));
            boolean notifications = cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NOTIFICACIONES)) > 0;
            String observaciones =  cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.OBSERVACIONES));
            Medicamento med = new Medicamento(name, dosis, horario, notifications, observaciones);
            int id = (cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry._ID)));
            med.id = Integer.toString(id);
            return (med);
        }

        return  null;

    }

    public  Medicamento getMedicamentoById(String Id)
    {
        Cursor cursor = getReadableDatabase().query(
                MedicamentosContract.MedicamentoEntry.TABLE_NAME,
                null,
                MedicamentosContract.MedicamentoEntry._ID + " LIKE ?",
                new String[]{Id},
                null,
                null,
                null);

        if(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NAME));
            String dosis  = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.DOSIS));
            HORARIO horario =  HORARIO.valueOf( cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.HORARIO)));
            boolean notifications = cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NOTIFICACIONES)) > 0;
            String observaciones =  cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.OBSERVACIONES));
            Medicamento med = new Medicamento(name, dosis, horario, notifications, observaciones);
            int id = (cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry._ID)));
            med.id = Integer.toString(id);
            return (med);
        }

        return  null;

    }

    public ArrayList<Medicamento> getAllMedicamentos() {

        Cursor cursor = getReadableDatabase()
                .query(
                        MedicamentosContract.MedicamentoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

        ArrayList<Medicamento> list = new ArrayList<>();

        while(cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NAME));
            String dosis  = cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.DOSIS));
            HORARIO horario =  HORARIO.valueOf( cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.HORARIO)));
            boolean notifications = cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.NOTIFICACIONES)) > 0;
            String observaciones =  cursor.getString(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry.OBSERVACIONES));
            Medicamento med = new Medicamento(name, dosis, horario, notifications, observaciones);
            int id = (cursor.getInt(cursor.getColumnIndex(MedicamentosContract.MedicamentoEntry._ID)));
            med.id = Integer.toString(id);

            list.add(med);
        }

        return list;

    }

}
