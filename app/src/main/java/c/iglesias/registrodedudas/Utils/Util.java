package c.iglesias.registrodedudas.Utils;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;

/**
 * Created by dell on 21/09/2018.
 */

public class Util {
    public static String formatFecha(int anio, int mes, int dia) {
        String date;
        date = String.valueOf(anio) + "/" + (mes >= 9 ? "" : "0") + String.valueOf(mes + 1) + "/" + (dia > 9 ? "" : "0") + String.valueOf(dia);
        return date;
    }

    public static SpannableString subrayar(String in) {
        SpannableString mitextoU = new SpannableString(in);
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        return mitextoU;
    }
}
