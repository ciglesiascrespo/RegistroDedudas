package c.iglesias.registrodedudas.Activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.iglesias.registrodedudas.Adapter.DeudasPendientesAdapter;
import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.Db.Modelo.DeudasDb;
import c.iglesias.registrodedudas.R;
import c.iglesias.registrodedudas.Utils.Util;

import static c.iglesias.registrodedudas.Utils.Util.subrayar;

public class AggDeudaActivity extends AppCompatActivity {

    @BindView(R.id.id_edt_nombre_deuda)
    TextInputEditText edtNombreDeuda;

    @BindView(R.id.id_til_nombre_deuda)
    TextInputLayout tilNombreDeuda;

    @BindView(R.id.id_edt_valor_deuda)
    TextInputEditText edtValorDeuda;

    @BindView(R.id.id_til_valor_deuda)
    TextInputLayout tilValorDeuda;

    @BindView(R.id.id_btn_guadar_deuda)
    Button btnGuardar;

    @BindView(R.id.appbar)
    @Nullable
    Toolbar toolbar;

    @BindView(R.id.id_txt_fecha_deuda)
    TextView txtFecha;

    @Inject
    DbHandler dbHandler;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agg_deuda);
        ButterKnife.bind(this);
        ((RegistroDeudasApplication) getApplication()).getDiComponent().inject(this);
        init();
    }

    private void init() {
        btnGuardar.setOnClickListener(listenerBtnGuardar);
        txtFecha.setOnClickListener(listenerTxtFecha);
        Calendar now = Calendar.getInstance();
        txtFecha.setText(subrayar(Util.formatFecha(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.getActualMaximum(Calendar.DAY_OF_MONTH))));
        initToolbar();
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private View.OnClickListener listenerBtnGuardar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nombre = edtNombreDeuda.getText().toString();
            String valor = edtValorDeuda.getText().toString();
            String fecha = txtFecha.getText().toString();


            if (nombre.isEmpty()) {
                tilNombreDeuda.setError(getResources().getString(R.string.str_campo_requerido));
            } else {
                tilNombreDeuda.setError(null);
            }

            if (valor.isEmpty()) {
                tilValorDeuda.setError(getResources().getString(R.string.str_campo_requerido));
            } else {
                tilValorDeuda.setError(null);
            }

            if (fecha.isEmpty()) {
                Toast.makeText(AggDeudaActivity.this, getResources().getString(R.string.str_seleccione_una_fecha), Toast.LENGTH_SHORT).show();
            }

            if (!(nombre.isEmpty() || fecha.isEmpty() || valor.isEmpty())) {
                ContentValues cv = new ContentValues();

                cv.put(DeudasDb.KEY_NOMBRE,nombre);
                cv.put(DeudasDb.KEY_ESTADO, DeudasDb.ESTADO_PENDIENTE);
                cv.put(DeudasDb.KEY_FECHA, fecha);
                cv.put(DeudasDb.KEY_VALOR, Long.valueOf(valor));

                if(dbHandler.insertDeuda(cv) > 0){
                    Toast.makeText(AggDeudaActivity.this, getResources().getString(R.string.str_deuda_agregada), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AggDeudaActivity.this, getResources().getString(R.string.str_deuda_no_agregada), Toast.LENGTH_SHORT).show();
                }

            }


        }
    };

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
            txtFecha.setText(subrayar(Util.formatFecha(anio, mes, dia)));

        }
    };

    private View.OnClickListener listenerTxtFecha = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Calendar now = Calendar.getInstance();

            datePickerDialog =
                    new DatePickerDialog(AggDeudaActivity.this, dateListener, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));


            if (datePickerDialog != null) {
                datePickerDialog.show();
            }
        }
    };
}
