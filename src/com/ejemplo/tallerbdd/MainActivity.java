package com.ejemplo.tallerbdd;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent objIntent = new Intent(getBaseContext(), AgregarActivity.class);
				startActivityForResult(objIntent, 1);
			}
		});
        
        ListView lstEventos = (ListView) findViewById(R.id.lstEventos);
        llenarLista(lstEventos);
    }
    
    private void llenarLista(ListView lstEventos) {
    	AccesoBDD objAccesoBDD = new AccesoBDD(this);
    	Cursor objCursor = objAccesoBDD.consultarTodos();
    	String[] arrCamposFuente = new String[] {
    			AccesoBDD.CAMPO_NOMBRE,
    			AccesoBDD.CAMPO_ENCARGADO
    	};
    	int[] arrIdsDestino = new int[] {
    			android.R.id.text1,
    			android.R.id.text2
    	};
    	SimpleCursorAdapter objAdaptador = new SimpleCursorAdapter(this, 
    			android.R.layout.simple_list_item_2, 
    			objCursor, 
    			arrCamposFuente, 
    			arrIdsDestino);
    	lstEventos.setAdapter(objAdaptador);
    }

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
	        ListView lstEventos = (ListView) findViewById(R.id.lstEventos);
	        llenarLista(lstEventos);
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
