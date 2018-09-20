package c.iglesias.registrodedudas.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import c.iglesias.registrodedudas.Db.Modelo.AbonosDb;
import c.iglesias.registrodedudas.Db.Modelo.DeudasDb;
import c.iglesias.registrodedudas.Db.Modelo.IngresosDb;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private final String TAG = getClass().getName();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e(TAG, "Creo DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e(TAG, "abonos: " + AbonosDb.getCreateTable());
        Log.e(TAG, "ingresos: " + IngresosDb.getCreateTable());
        Log.e(TAG, "deudas: " + DeudasDb.getCreateTable());

        db.execSQL(DeudasDb.getCreateTable());
        db.execSQL(AbonosDb.getCreateTable());
        db.execSQL(IngresosDb.getCreateTable());
        insertData(db);
    }

    private void insertData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put(AbonosDb.KEY_FECHA, "2018-01-01");
        cv.put(AbonosDb.KEY_ID_DEUDA, 1);
        cv.put(AbonosDb.KEY_VALOR, 50);

        db.insert(AbonosDb.TABLE, null, cv);

        cv = new ContentValues();

        cv.put(AbonosDb.KEY_FECHA, "2018-01-02");
        cv.put(AbonosDb.KEY_ID_DEUDA, 1);
        cv.put(AbonosDb.KEY_VALOR, 100);

        db.insert(AbonosDb.TABLE, null, cv);

        cv = new ContentValues();

        cv.put(DeudasDb.KEY_FECHA, "2018-01-01");
        cv.put(DeudasDb.KEY_ESTADO, "Pendiente");
        cv.put(DeudasDb.KEY_VALOR, 1000);
        cv.put(DeudasDb.KEY_NOMBRE, "Ciglesias");

        db.insert(DeudasDb.TABLE, null, cv);

        cv = new ContentValues();

        cv.put(IngresosDb.KEY_FECHA, "2018-01-11");
        cv.put(IngresosDb.KEY_VALOR, 120);
        cv.put(IngresosDb.KEY_NOMBRE, "Sueldo");

        db.insert(IngresosDb.TABLE, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long insert(String tableName, ContentValues cv) {
        long i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            i = db.insert(tableName, null, cv);
            //if (db.isOpen()) db.close();
        } catch (Exception e) {
            //db.close();
            Log.e(TAG, "Error insertando en la base de datos: " + e.getMessage());
            e.printStackTrace();

        }
        return i;

    }

    public Cursor execSql(String query) {
        Cursor c = null;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            c = db.rawQuery(query, null);
            // db.close();
        } catch (Exception e) {
            //if (db.isOpen()) db.close();
            Log.e(TAG, "Error ejecutando sql: " + query + " " + e.getMessage());
            e.printStackTrace();
        }

        return c;
    }

    public void update(String tableName, ContentValues cv, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(tableName, cv, condition, null);
            //  db.close();
        } catch (Exception e) {
            // if (db.isOpen()) db.close();
            Log.e(TAG, "Error update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String tableName, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(tableName, condition, null);
            //  db.close();
        } catch (Exception e) {
            //  if (db.isOpen()) db.close();
            Log.e(TAG, "Error delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
