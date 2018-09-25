package c.iglesias.registrodedudas.Db.Response;

/**
 * Created by dell on 25/09/2018.
 */

public class ResponseDeudas {
    private String nombre, fecha;
    private int total, pendiente;

    public ResponseDeudas(){

    }


    public int getTotal() {
        return total;
    }

    public int getPendiente() {
        return pendiente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPendiente(int pendiente) {
        this.pendiente = pendiente;
    }
}
