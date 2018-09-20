package c.iglesias.registrodedudas.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.progresviews.ProgressWheel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.R;


public class MainFragment extends Fragment {

    @BindView(R.id.id_prg_wheel_pendientes)
    ProgressWheel prgWheelPendientes;

    @BindView(R.id.id_prg_wheel_saldadas)
    ProgressWheel prgWheelSaldadas;

    @Inject
    DbHandler dbHandler;

    public MainFragment() {

    }


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,view);
        RegistroDeudasApplication.getApp().getDiComponent().inject(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
