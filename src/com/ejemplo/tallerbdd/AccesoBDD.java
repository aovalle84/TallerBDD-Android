package com.ejemplo.tallerbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AccesoBDD {

	private static final String BASEDATOS_NOMBRE = "evento.db";
	private static final int BASEDATOS_VERSION = 1;
	
	private static final String TABLA_EVENTO = "Evento";
	
	public static final String CAMPO_ID = "_id";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_ENCARGADO = "encargado";
	
	private SQLiteDatabase mObjBaseDatos;
	
	public AccesoBDD(Context objContexto) {
		try {
			EventoHelper objHelper = new EventoHelper(objContexto, 
					BASEDATOS_NOMBRE, null, BASEDATOS_VERSION);
			mObjBaseDatos = objHelper.getWritableDatabase();
		} catch (Exception ex) {
			Log.e("Error", ex.getMessage());
		}
	}
	
	public void cerrar() {
		mObjBaseDatos.close();
	}
	
	public long insertar(String strNombre, String strEncargado) {
		long lngIdInsertado = -1;
		try {
			ContentValues objRegistro = new ContentValues();
			objRegistro.put(CAMPO_NOMBRE, strNombre);
			objRegistro.put(CAMPO_ENCARGADO, strEncargado);
			lngIdInsertado = mObjBaseDatos.insert(TABLA_EVENTO, 
					null, objRegistro);
		} catch (Exception ex) {
			Log.e("Error", ex.getMessage());
		}
		return lngIdInsertado;
	}
	
	public Cursor consultarTodos() {
		Cursor objResultado = null;
		try {
			objResultado = mObjBaseDatos.query(TABLA_EVENTO, 
					new String[] {CAMPO_ID, CAMPO_NOMBRE, CAMPO_ENCARGADO}, 
					null, null, null, null, null);
		} catch (Exception ex) {
			Log.e("Error", ex.getMessage());
		}
		return objResultado;
	}
	
	class EventoHelper extends SQLiteOpenHelper {
		
		private final static String SQL_CREAR_TABLA = 
				"create table Evento (" +
				"_id integer primary key autoincrement, " +
				"nombre text not null, " + 
				"encargado text not null" +
				");";

		public EventoHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase objBaseDatos) {
			objBaseDatos.execSQL(SQL_CREAR_TABLA);
		}

		@Override
		public void onUpgrade(SQLiteDatabase objBaseDatos, 
				int intVersionAntigua, int intVersionNueva) {
			objBaseDatos.execSQL("drop table if exists " + TABLA_EVENTO);
			onCreate(objBaseDatos);
		}
		
	}
}
