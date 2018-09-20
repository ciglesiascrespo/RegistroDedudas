package c.iglesias.registrodedudas.Config;

import android.app.Application;

import c.iglesias.registrodedudas.Config.Modulo.ManagerModulo;

/**
 * Created by dell on 14/09/2018.
 */

public class RegistroDeudasApplication extends Application {
    /* Componente */
    private DiComponent diComponent;

    /* Aplicacion */
    private static RegistroDeudasApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        iniComponent();

    }

    /**
     * Inicializador de componente dagger
     */
    private void iniComponent() {
        diComponent = DaggerDiComponent.builder()
                .managerModulo(new ManagerModulo(this))
                .build();
    }

    /**
     * Obtiene el componente para el inject
     *
     * @return
     */
    public DiComponent getDiComponent() {
        return diComponent;
    }

    /**
     * Obtiene la aplicacion
     *
     * @return
     */
    public static RegistroDeudasApplication getApp() {
        return app;
    }

}
