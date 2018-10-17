package c.iglesias.registrodedudas.Db.Modelo;

/**
 * Created by Ciglesias on 19/09/2018.
 */

public class DeudasDb {
    public static final String TABLE = "deudas";

    public static final String KEY_ID = "id_deuda";
    public static final String KEY_NOMBRE = "nombre_acreedor";
    public static final String KEY_VALOR = "valor";
    public static final String KEY_FECHA = "fecha";
    public static final String KEY_ESTADO = "estado";

    public static final String ESTADO_PENDIENTE = "Pendiente";
    public static final String ESTADO_SALDADA = "Saldada";


    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID + "` INTEGER, `" + KEY_NOMBRE + "` TEXT, `" + KEY_VALOR + "` INTEGER " +
                ", `" + KEY_FECHA + "` DATE, `" + KEY_ESTADO + "` TEXT, PRIMARY KEY(`" + KEY_ID + "`))";

        return query;
    }
}
