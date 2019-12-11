package com.example.listamedicamentos.DBContract;

import android.provider.BaseColumns;

/*
* Contrato para la base de datos
* */
public class MedicamentosContract {

    public static abstract class MedicamentoEntry implements BaseColumns {

        public static final String TABLE_NAME ="medicamentoInfo";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String DOSIS = "dosis";
        public static final String NOTIFICACIONES = "notificaciones";
        public static final String OBSERVACIONES = "observaciones";
        public static final String HORARIO = "horario";

    }

}
