package c.iglesias.registrodedudas.Db.Modelo;

/**
 * Created by Ciglesias on 19/09/2018.
 */

public class IngresosDb {
    public static final String TABLE = "ingresos";

    public static final String KEY_ID = "id_ingreso";
    public static final String KEY_VALOR = "valor";
    public static final String KEY_FECHA = "fecha";
    public static final String KEY_NOMBRE = "nombre";


    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID + "` INTEGER, `" + KEY_VALOR + "` INTEGER " +
                ", `" + KEY_FECHA + "` DATE, `" + KEY_NOMBRE + "` TEXT, PRIMARY KEY(`" + KEY_ID + "`))";

        return query;
    }
}
