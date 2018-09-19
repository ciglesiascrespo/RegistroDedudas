package c.iglesias.registrodedudas;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.progresviews.ProgressLine;
import com.app.progresviews.ProgressWheel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.id_prg_wheel_pendientes)
    ProgressWheel prgWheelPendientes;

    @BindView(R.id.id_prg_wheel_saldadas)
    ProgressWheel prgWheelSaldadas;

    @BindView(R.id.appbar)
    @Nullable
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    @Nullable
    DrawerLayout drawerLayout;

    @BindView(R.id.navView)
    @Nullable
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        initToolbar();
        if (navigationView != null) {
            View headerView = navigationView.getHeaderView(0);
            initMenuNavigationView(headerView);
        }
initValues();

    }

    private void initValues(){

        prgWheelSaldadas.setPercentage(60);
    }

    /**
     * Metodo que inicializa el toolbar de la aplicacion
     */
    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Metodo que inicializa las acciones sobre el menu del drawer0
     */
    private void initMenuNavigationView(View view) {

    }
}
