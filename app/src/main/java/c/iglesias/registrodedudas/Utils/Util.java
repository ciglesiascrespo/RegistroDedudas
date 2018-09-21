package c.iglesias.registrodedudas.Utils;

/**
 * Created by dell on 21/09/2018.
 */

public class Util {
    public static String formatFecha(int anio, int mes, int dia) {
        String date;
        date = String.valueOf(anio) + "/" + (mes >= 9 ? "" : "0") + String.valueOf(mes + 1) + "/" + (dia > 9 ? "" : "0") + String.valueOf(dia);
        return date;
    }
}
