package com.guihgo.baseservico;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
//Ola !  3ª Parte - 
//Mostrarei //Como criar uma conexão entre o Serviço e o Activity(UI) via Broadcast...
	//Oq faremos é mandar um Valor do UI para o Serviço
	//Agora que mandamos, precisamos ouvir no Serviço !
	public Intent intentServico;
	
	public Intent intentBroad;
	
	public Button btnInicia, btnPara, btnSendUIServ;
	public TextView tvServico;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		intentServico = new Intent(MainActivity.this, servico.class);
		
		registerReceiver(atualizaUI, new IntentFilter("uploadUI"));
		
		//Linka
		btnInicia 	= (Button) findViewById(R.id.btnInicia);
		btnPara		= (Button) findViewById(R.id.btnPara);
		tvServico	= (TextView) findViewById(R.id.tvServico);
		
		btnSendUIServ = (Button) findViewById(R.id.btnSendUIServ);
		
		//OnClick dos Botões !
		
		btnSendUIServ.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Iremos mandar o valor "Café !!!"
				intentBroad = new Intent("uploadServ");
				intentBroad.putExtra("<Produtos>", "Café !!!");
				sendBroadcast(intentBroad);
			}
		});
		
		btnInicia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Inicia o Serviço
				startService(intentServico);
			}
		});
		
		btnPara.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para o Serviço
				stopService(intentServico);
			}
		});
	}
	
	
	@Override
		protected void onDestroy() {
			unregisterReceiver(atualizaUI);
			super.onDestroy();
		}
	
	public BroadcastReceiver atualizaUI = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			//Faz Algo qnd receber !
			tvServico.setText(intent.getExtras().getString("<Clientes>"));
			
		}
	}; 
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
