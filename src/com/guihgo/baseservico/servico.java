package com.guihgo.baseservico;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class servico extends Service {

	public Intent intentBroad;
	
	@Override
	public void onCreate() {
		Log.d("onCreate", "----");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("onStartCommand", "----");
		
		//o Servi�o precisa ser Criado e Iniciado primeiro.OKAY
		registerReceiver(ServBroad, new IntentFilter("uploadServ"));
		
		intentBroad = new Intent("uploadUI");
		intentBroad.putExtra("<Clientes>", "Asn�sio");
		sendBroadcast(intentBroad);
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d("onDestroy", "----");
		unregisterReceiver(ServBroad);
		
		super.onDestroy();
	}
	
	
	public BroadcastReceiver ServBroad = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			//Pega valores Mandados AQUI
			String sValor = intent.getExtras().getString("<Produtos>");
			Log.d("<Produtos>", sValor);
		}
	};
	
	//Testando... Quando eu clicar no UI, ele vai mandar um valor para o Servi�o que
	//ser� mostrado no LogCat
	//BOm, n�o deu.. Vamos ver o Pq?
	//OKAY !!! Certinho !
	//Conclus�o !
	
		//A conex�o entre os DOIS (UI e Servi�o) foi feita por BroadCast !
			//� o mesmo procedimento de conex�o, tanto para UI -> Servi�o, tanto para Servi�o -> UI   
	//Se um fala com o outro e vice-versa, temos uma conex�o, um relacionamento.
	
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
