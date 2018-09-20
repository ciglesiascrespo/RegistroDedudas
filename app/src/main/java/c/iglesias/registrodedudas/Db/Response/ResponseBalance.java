package c.iglesias.registrodedudas.Db.Response;

/**
 * Created by dell on 20/09/2018.
 */

public class ResponseBalance {
    private int total=0;
    private int pendiente = 0;
    private int saldado= 0;

    public ResponseBalance(){};



    public ResponseBalance(int total, int pendiente, int saldado ){
        this.total = total;
        this.pendiente = pendiente;
        this.saldado = saldado;
    }


    public void setPendiente(int pendiente) {
        this.pendiente = pendiente;
    }

    public void setSaldado(int saldado) {
        this.saldado = saldado;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPendiente() {
        return pendiente;
    }

    public int getSaldado() {
        return saldado;
    }

    public int getTotal() {
        return total;
    }
}
