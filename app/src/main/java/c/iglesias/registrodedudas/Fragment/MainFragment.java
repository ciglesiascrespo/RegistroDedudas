package c.iglesias.registrodedudas.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.app.progresviews.ProgressWheel;

import org.w3c.dom.Text;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.Db.Response.ResponseBalance;
import c.iglesias.registrodedudas.R;
import c.iglesias.registrodedudas.Utils.Util;


public class MainFragment extends Fragment {

    private final String TAG = getClass().getName();

    @BindView(R.id.id_prg_wheel_pendientes)
    ProgressWheel prgWheelPendientes;

    @BindView(R.id.id_prg_wheel_saldadas)
    ProgressWheel prgWheelSaldadas;

    @Inject
    DbHandler dbHandler;

    @BindView(R.id.id_txt_fecha_inicio_balance)
    TextView txtInicioBalance;

    @BindView(R.id.id_txt_fecha_fin_balance)
    TextView txtFinBalance;

    @BindView(R.id.id_txt_deuda_total)
    TextView txtDeudaTotal;

    @BindView(R.id.id_txt_deuda_pendiente)
    TextView txtDeudaPendiente;

    @BindView(R.id.id_txt_deuda_saldadas)
    TextView txtDeudaSaldada;

    @Inject
    Context context;

    DatePickerDialog datePickerDialog;

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
        ButterKnife.bind(this, view);
        RegistroDeudasApplication.getApp().getDiComponent().inject(this);
        initView();
        return view;
    }

    private void initView() {
        Calendar now = Calendar.getInstance();

        txtInicioBalance.setOnClickListener(onClickListener);
        txtFinBalance.setOnClickListener(onClickListener);

        txtInicioBalance.setText(subrayar(Util.formatFecha(now.get(Calendar.YEAR), now.get(Calendar.MONTH), 1)));
        txtFinBalance.setText(subrayar(Util.formatFecha(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.getActualMaximum(Calendar.DAY_OF_MONTH))));

        setValues();


    }

    private SpannableString subrayar(String in) {
        SpannableString mitextoU = new SpannableString(in);
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        return mitextoU;
    }

    private void setValues() {

        ResponseBalance responseBalance = dbHandler.obtenerBalance(txtInicioBalance.getText().toString(), txtFinBalance.getText().toString());
        if (responseBalance != null && responseBalance.getTotal() != -1) {
            txtDeudaPendiente.setText("$ " + responseBalance.getPendiente());
            txtDeudaSaldada.setText("$ " + responseBalance.getSaldado());
            txtDeudaTotal.setText("$ " + responseBalance.getTotal());


            prgWheelPendientes.setStepCountText("$ " + responseBalance.getPendiente());
            prgWheelSaldadas.setStepCountText("$ " + responseBalance.getSaldado());

            prgWheelPendientes.setPercentage(responseBalance.getPendiente()*360/responseBalance.getTotal());
            prgWheelSaldadas.setPercentage(responseBalance.getSaldado()*360/responseBalance.getTotal());

        } else {
            txtDeudaPendiente.setText("$ 0");
            txtDeudaSaldada.setText("$ 0");
            txtDeudaTotal.setText("$ 0");

            prgWheelPendientes.setStepCountText("$ 0");
            prgWheelSaldadas.setStepCountText("$ 0");

            prgWheelPendientes.setPercentage(0);
            prgWheelSaldadas.setPercentage(0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private DatePickerDialog.OnDateSetListener dateListenerInicio = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
            txtInicioBalance.setText(subrayar(Util.formatFecha(anio, mes, dia)));
            setValues();
        }
    };


    private DatePickerDialog.OnDateSetListener dateListenerFin = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
            txtFinBalance.setText(subrayar(Util.formatFecha(anio, mes, dia)));
            setValues();
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar now = Calendar.getInstance();
            switch (view.getId()) {
                case R.id.id_txt_fecha_inicio_balance:
                    datePickerDialog =
                            new DatePickerDialog(getContext(), dateListenerInicio, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                    break;
                case R.id.id_txt_fecha_fin_balance:
                    datePickerDialog =
                            new DatePickerDialog(getContext(), dateListenerFin, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
                    break;
            }

            if (datePickerDialog != null) {
                datePickerDialog.show();
            }
        }
    };
}
