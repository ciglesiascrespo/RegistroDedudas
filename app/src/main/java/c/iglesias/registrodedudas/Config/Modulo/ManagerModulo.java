package c.iglesias.registrodedudas.Config.Modulo;

import android.content.Context;

import c.iglesias.registrodedudas.Db.DbHandler;
import c.iglesias.registrodedudas.Db.DbHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Ciglesias on 19/09/2018.
 */
@Module
public class ManagerModulo {
    private final Context mContext;

    /**
     * Constructor
     *
     * @param mContext
     */
    public ManagerModulo(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public Context getContext() {
        return mContext;
    }

    @Provides
    public DbHelper provideDbHelper(){
        return new DbHelper(mContext);
    }

    @Provides
    public DbHandler provideDbHandler(){
        return DbHandler.getInstance(mContext);
    }
}
