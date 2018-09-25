package c.iglesias.registrodedudas.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import c.iglesias.registrodedudas.Adapter.DeudasPendientesAdapter;
import c.iglesias.registrodedudas.Db.Response.ResponseDeudas;

public class DeudasFragment extends BaseFragment {

    public DeudasFragment() {


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ResponseDeudas> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ResponseDeudas item = new ResponseDeudas();

            item.setFecha("2018/09/08");
            item.setNombre("Deuda " + i);
            item.setTotal(i * 100);
            item.setPendiente(i * 20);
            list.add(item);
        }

        super.setRvAdapter(new DeudasPendientesAdapter());
        ((DeudasPendientesAdapter) super.getAdapter()).setListItems(list);

        super.showList(list.size() > 0);

    }

}
