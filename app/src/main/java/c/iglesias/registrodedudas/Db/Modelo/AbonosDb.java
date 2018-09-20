package c.iglesias.registrodedudas.Db.Modelo;

/**
 * Created by Ciglesias on 19/09/2018.
 */

public class AbonosDb {
    public static final String TABLE = "abonos";

    public static final String KEY_ID = "id_abono";
    public static final String KEY_ID_DEUDA = "id_deuda";
    public static final String KEY_VALOR = "valor";
    public static final String KEY_FECHA = "fecha";



    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID + "` INTEGER, `" + KEY_ID_DEUDA + "` INTEGER, `" + KEY_VALOR + "` INTEGER " +
                ", `" + KEY_FECHA + "` DATE, PRIMARY KEY(`" + KEY_ID + "`))";

        return query;
    }
}
