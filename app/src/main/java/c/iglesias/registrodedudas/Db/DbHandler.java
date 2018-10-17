package c.iglesias.registrodedudas.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.Modelo.DeudasDb;
import c.iglesias.registrodedudas.Db.Response.ResponseBalance;
import c.iglesias.registrodedudas.Db.Response.ResponseDeudas;

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


    public List<ResponseDeudas> obtenerListDeudas() {
        List<ResponseDeudas> list = new ArrayList<>();

        Cursor c = null;
        String sql = "SELECT \n" +
                "nombre_acreedor,\n" +
                "id_deuda,\n" +
                "valor, \n" +
                "fecha,\n" +
                "valor - abonos pendientes\n" +
                "FROM (\n" +
                "SELECT\td.nombre_acreedor, d.id_deuda,d.Valor,  d.fecha, SUM(ifnull(a.valor,0)) abonos\n" +
                "FROM deudas d\n" +
                "LEFT JOIN abonos a \n" +
                "\t\t\tON(d.id_Deuda = a.id_deuda)\n" +
                "WHERE d.estado = '" + DeudasDb.ESTADO_PENDIENTE + "'" +
                "GROUP BY d.nombre_acreedor,d.id_deuda, d.Valor, d.fecha) AS t";
        try {
            c = dbHelper.execSql(sql);

            if (c != null && c.moveToFirst()) {
                do {
                    int total = 0, pendiente = 0, idDeuda;
                    String nombre = "", fecha = "";

                    if (!c.isNull(c.getColumnIndex("nombre_acreedor"))) {
                        nombre = c.getString(c.getColumnIndex("nombre_acreedor"));
                    }
                    if (!c.isNull(c.getColumnIndex("id_deuda"))) {
                        idDeuda = c.getInt(c.getColumnIndex("id_deuda"));
                    }
                    if (!c.isNull(c.getColumnIndex("valor"))) {
                        total = c.getInt(c.getColumnIndex("valor"));
                    }
                    if (!c.isNull(c.getColumnIndex("fecha"))) {
                        fecha = c.getString(c.getColumnIndex("fecha"));
                    }
                    if (!c.isNull(c.getColumnIndex("pendientes"))) {
                        pendiente = c.getInt(c.getColumnIndex("pendientes"));
                    }

                    ResponseDeudas responseDeudas = new ResponseDeudas();

                    responseDeudas.setPendiente(pendiente);
                    responseDeudas.setTotal(total);
                    responseDeudas.setNombre(nombre);
                    responseDeudas.setFecha(fecha);

                    list.add(responseDeudas);

                } while (c.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();

            if (c != null && !c.isClosed()) {
                c.close();
            }
        }


        return list;
    }

    public ResponseBalance obtenerBalance(String fechaInicio, String fechaFin) {
        ResponseBalance response = new ResponseBalance();

        Cursor c = null;
        String sql = "SELECT \n" +
                "total as total,\n" +
                "saldados as saldados,\n" +
                "total - saldados AS pendiente\n" +
                "FROM (\n" +
                "SELECT \n" +
                "\n" +
                "(SELECT \n" +
                "SUM(valor)\n" +
                "FROM deudas d\n" +

                "WHERE d.fecha between '" + fechaInicio + "' AND  '" + fechaFin + "') Total,\n" +
                "(\n" +
                "SELECT \n" +
                "SUM(ifnull(a.valor,0)) AS saldados\n" +
                "FROM deudas d\n" +
                "LEFT JOIN abonos a\n" +
                "\t\tON(a.id_Deuda = d.id_Deuda AND a.fecha between '" + fechaInicio + "' AND  '" + fechaFin + "')\n" +
                "WHERE d.fecha between '" + fechaInicio + "' AND   '" + fechaFin + "'\n" +
                "\t   ) saldados) AS t";


        try {
            c = dbHelper.execSql(sql);
            Log.e(TAG, sql);
            if (c != null && c.moveToFirst()) {
                do {
                    int total = -1, saldado = -1, pendiente = -1;

                    if (!c.isNull(c.getColumnIndex("total"))) {
                        total = c.getInt(c.getColumnIndex("total"));
                    }
                    if (!c.isNull(c.getColumnIndex("saldados"))) {
                        saldado = c.getInt(c.getColumnIndex("saldados"));
                    }

                    if (!c.isNull(c.getColumnIndex("pendiente"))) {
                        pendiente = c.getInt(c.getColumnIndex("pendiente"));
                    }


                    response.setPendiente(pendiente);
                    response.setSaldado(saldado);
                    response.setTotal(total);

                } while (c.moveToNext());
            }
        } catch (Exception e) {

            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error cargando balance: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

}
