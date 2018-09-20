package c.iglesias.registrodedudas.Config;

import c.iglesias.registrodedudas.Config.Modulo.ManagerModulo;
import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.Fragment.DeudasFragment;
import c.iglesias.registrodedudas.Fragment.MainFragment;
import c.iglesias.registrodedudas.MainActivity;
import dagger.Component;

/**
 * Created by dell on 14/09/2018.
 */
@Component(modules = {ManagerModulo.class})
public interface DiComponent {

    void inject(MainActivity mainActivity);

    void inject(DbHandler dbHandler);

    void inject(MainFragment mainFragment);

    void inject(DeudasFragment deudasFragment);
}
