package c.iglesias.registrodedudas.Db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.Modelo.DeudasDb;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHandler {

    @Inject
    public DbHelper dbHelper;

    private static DbHandler instance = null;
    public String TAG = getClass().getName();

    protected DbHandler(Context context) {
        Log.e("DbHandler", "creo instancia handler");
        RegistroDeudasApplication.getApp().getDiComponent().inject(this);
    }

    public static DbHandler getInstance(Context context) {

        if (instance == null) {
            instance = new DbHandler(context);
        }
        Log.e("DbHandler", "uso instancia handler");
        return instance;
    }

    public long insertDeuda(ContentValues cv) {
        return dbHelper.insert(DeudasDb.TABLE, cv);
    }
   /*


    public List<Formulario> getFormularios() {
        ArrayList<Formulario> list = new ArrayList<>();
        Cursor c = null;

        try {

            String query = "select\n" +
                    "  f." + FormularioDb.KEY_ID + ",\n" +
                    "  f." + FormularioDb.KEY_FECHA_HORA + ",\n" +
                    "  f." + FormularioDb.KEY_ID_LUGAR + ",\n" +
                    "  f." + FormularioDb.KEY_ID_ESTACION + ",\n" +
                    "  e." + EstacionDb.KEY_NOMBRE + " as nombre_estacion,\n" +
                    "  f." + FormularioDb.KEY_ID_TREN + ",\n" +
                    "  t." + TrenDb.KEY_NOMBRE + " as nombre_tren,\n" +
                    "  f." + FormularioDb.KEY_ID_PROCESO + ",\n" +
                    "  p." + ProcesoDb.KEY_NOMBRE + " as nombre_proceso,\n" +
                    "  f." + FormularioDb.KEY_ID_NOVEDAD + ",\n" +
                    "  n." + NovedadDb.KEY_NOMBRE + " as nombre_novedad,\n" +
                    "  f." + FormularioDb.KEY_ESTADO + "\n" +
                    "\n" +
                    "from " + FormularioDb.TABLE + " as f\n" +
                    "left join " + EstacionDb.TABLE + " as e\n" +
                    "  on(e." + EstacionDb.KEY_ID + " = f." + FormularioDb.KEY_ID_ESTACION + ")\n" +
                    "left join " + TrenDb.TABLE + " as t\n" +
                    "  on(t." + TrenDb.KEY_ID + " = f." + FormularioDb.KEY_ID_TREN + ")\n" +
                    "left join " + ProcesoDb.TABLE + " as p\n" +
                    "  on(p." + ProcesoDb.KEY_ID + " = f." + FormularioDb.KEY_ID_PROCESO + ")\n" +
                    "left join " + NovedadDb.TABLE + " as n\n" +
                    "  on(n." + NovedadDb.KEY_ID + " = f." + FormularioDb.KEY_ID_NOVEDAD + ")\n" +
                    "\n" +
                    "order by f." + FormularioDb.KEY_ESTADO + ",case when f." + FormularioDb.KEY_ID_LUGAR + " = 1 then e." + EstacionDb.KEY_NOMBRE + " else t." + TrenDb.KEY_NOMBRE + " end";


            c = dbHelper.execSql(query);

            if (c.moveToFirst()) {
                do {
                    Opcion opLugar = null, opEstacion = null, opTren = null, opProceso = null, opNovedad = null;
                    int estado = 0, idFormulario = 0, idLugar = 0, idEstacion = 0, idTren = 0, idProceso = 0, idNovedad = 0;
                    String fechaCreacion = "", nombreEstacion = "", nombreTren = "", nombreProceso = "", nombreNovedad = "";

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID))) {
                        idFormulario = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID));
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_FECHA_HORA))) {
                        fechaCreacion = c.getString(c.getColumnIndex(FormularioDb.KEY_FECHA_HORA));
                    }


                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ESTADO))) {
                        estado = c.getInt(c.getColumnIndex(FormularioDb.KEY_ESTADO));
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID_LUGAR))) {
                        idLugar = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID_LUGAR));

                        opLugar = new Opcion(idLugar, "");
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID_TREN))) {
                        idTren = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID_TREN));

                        if (!c.isNull(c.getColumnIndex("nombre_tren"))) {
                            nombreTren = c.getString(c.getColumnIndex("nombre_tren"));


                        }
                        opTren = new Opcion(idTren, nombreTren);
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID_ESTACION))) {
                        idEstacion = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID_ESTACION));

                        if (!c.isNull(c.getColumnIndex("nombre_estacion"))) {
                            nombreEstacion = c.getString(c.getColumnIndex("nombre_estacion"));


                        }
                        opEstacion = new Opcion(idEstacion, nombreEstacion);
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID_PROCESO))) {
                        idProceso = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID_PROCESO));

                        if (!c.isNull(c.getColumnIndex("nombre_proceso"))) {
                            nombreProceso = c.getString(c.getColumnIndex("nombre_proceso"));


                        }
                        opProceso = new Opcion(idProceso, nombreProceso);
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_ID_NOVEDAD))) {
                        idNovedad = c.getInt(c.getColumnIndex(FormularioDb.KEY_ID_NOVEDAD));

                        if (!c.isNull(c.getColumnIndex("nombre_novedad"))) {
                            nombreNovedad = c.getString(c.getColumnIndex("nombre_novedad"));


                        }
                        opNovedad = new Opcion(idNovedad, nombreNovedad);
                    }

                    Formulario f = new Formulario(opEstacion, opTren, opLugar, opProceso, opNovedad, fechaCreacion, idFormulario, estado);

                    list.add(f);

                } while (c.moveToNext());

            }


        } catch (Exception e) {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error cargando procesos: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
*/


}
