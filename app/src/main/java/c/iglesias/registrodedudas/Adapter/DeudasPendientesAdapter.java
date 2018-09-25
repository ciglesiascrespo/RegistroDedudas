package c.iglesias.registrodedudas.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import c.iglesias.registrodedudas.Db.Response.ResponseDeudas;
import c.iglesias.registrodedudas.R;

/**
 * Created by dell on 25/09/2018.
 */

public class DeudasPendientesAdapter extends RecyclerView.Adapter<DeudasPendientesAdapter.ViewHolder> {

    List<ResponseDeudas> listItems = new ArrayList<>();

    public void setListItems(List<ResponseDeudas> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deudas_pendientes,
                parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ResponseDeudas item = listItems.get(position);

        holder.txtNombre.setText(item.getNombre());
        holder.txtFecha.setText(item.getFecha());
        holder.txtTotal.setText("$ " + item.getTotal());
        holder.txtPendiente.setText("$ " + item.getPendiente());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTotal, txtPendiente, txtFecha, txtNombre;

        public ViewHolder(View itemView) {
            super(itemView);

            txtFecha = itemView.findViewById(R.id.id_txt_fecha_item_deuda);
            txtTotal = itemView.findViewById(R.id.id_txt_item_total_deuda);
            txtPendiente = itemView.findViewById(R.id.id_txt_item_pendi_deuda_deuda);
            txtNombre = itemView.findViewById(R.id.id_txt_item_nombre_deuda);
        }
    }
}
