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
		
		//o Serviço precisa ser Criado e Iniciado primeiro.OKAY
		registerReceiver(ServBroad, new IntentFilter("uploadServ"));
		
		intentBroad = new Intent("uploadUI");
		intentBroad.putExtra("<Clientes>", "Asnésio");
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
	
	//Testando... Quando eu clicar no UI, ele vai mandar um valor para o Serviço que
	//será mostrado no LogCat
	//BOm, não deu.. Vamos ver o Pq?
	//OKAY !!! Certinho !
	//Conclusão !
	
		//A conexão entre os DOIS (UI e Serviço) foi feita por BroadCast !
			//É o mesmo procedimento de conexão, tanto para UI -> Serviço, tanto para Serviço -> UI   
	//Se um fala com o outro e vice-versa, temos uma conexão, um relacionamento.
	
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
