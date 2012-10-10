package com.ejemplo.tallerbdd;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AgregarActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_agregar);
        
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String strNombre = "";
				String strEncargado = "";
				EditText edtControl = (EditText) findViewById(R.id.edtNombre);
				strNombre = edtControl.getText().toString();
				edtControl = (EditText) findViewById(R.id.edtEncargado);
				strEncargado = edtControl.getText().toString();
				
				AccesoBDD objAccesoBDD = new AccesoBDD(getBaseContext());
				objAccesoBDD.insertar(strNombre, strEncargado);
				setResult(RESULT_OK);
				finish();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_evento_agregar, menu);
        return true;
    }
}
