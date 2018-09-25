package c.iglesias.registrodedudas.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import c.iglesias.registrodedudas.Adapter.DeudasPendientesAdapter;
import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeudasFragment extends BaseFragment {

    @Inject
    DbHandler dbHandler;

    public DeudasFragment() {


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RegistroDeudasApplication.getApp().getDiComponent().inject(this);
        setRvAdapter(new DeudasPendientesAdapter());
        obtenerDeudas();


    }

    private void obtenerDeudas() {
        showInfo(false);

        Observable.fromCallable(() -> {
            return dbHandler.obtenerListDeudas();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    showInfo(true);
                    ((DeudasPendientesAdapter) super.getAdapter()).setListItems(result);
                    showList(result.size() > 0);
                }, e -> {
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.str_error_cargando_info), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    showInfo(true);
                    showList(false);
                });
    }

}
