package c.iglesias.registrodedudas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.iglesias.registrodedudas.Config.RegistroDeudasApplication;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.Fragment.DeudasFragment;
import c.iglesias.registrodedudas.Fragment.MainFragment;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.appbar)
    @Nullable
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    @Nullable
    DrawerLayout drawerLayout;

    @BindView(R.id.navView)
    @Nullable
    NavigationView navigationView;

    @Inject
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((RegistroDeudasApplication) getApplication()).getDiComponent().inject(this);
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
            setupDrawerContent(navigationView);
            showItem(navigationView.getMenu().getItem(0));
        }
        initValues();

    }

    private void initValues() {


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
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        showItem(menuItem);
                        return true;
                    }
                });
    }


    public void showItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch(menuItem.getItemId()) {
            case R.id.id_menu_principal:
                fragmentClass = MainFragment.class;
                break;
            case R.id.id_menu_deudas_pendientes:
                fragmentClass = DeudasFragment.class;
                break;
            default:
                fragmentClass = MainFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();
    }


}
