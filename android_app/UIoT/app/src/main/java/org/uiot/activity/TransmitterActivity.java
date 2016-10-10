package org.uiot.activity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.uiot.uiot.R;

import java.io.IOException;
import java.util.UUID;

import static android.nfc.NfcAdapter.getDefaultAdapter;

/**
 * Created by Ruan Aragão on 30/09/2016.
 */

public class TransmitterActivity extends Activity {
    Button btnBluetooth, btnWifi, btnNfc;

    private static final int SOLICITA_ATIVACAO_BLUETTOOTH = 1;
    private static final int SOLICITA_CONEXAO_BLUETTOOTH = 2;
    BluetoothAdapter mBluetoothAdapter = null;
    BluetoothDevice bluetoothDevice = null;
    BluetoothSocket bluetoothSocket = null;
    boolean conexao = false;
    private static String MAC = null;

    UUID meu_uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    WifiManager adm_wifi;
    NfcManager adm_nfc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transmitter);

        //BLUETTOOTH

        btnBluetooth = (Button)findViewById(R.id.btnBluetooth);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"Dispositivo sem Bluetooth", Toast.LENGTH_LONG).show();
        } else if (!mBluetoothAdapter.isEnabled()) {
            Intent ativa = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativa, SOLICITA_ATIVACAO_BLUETTOOTH);
        }

        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (conexao){
                    //desconecta
                    try{
                        bluetoothSocket.close();
                        conexao = false;
                        Toast.makeText(getApplicationContext(),"Bluetooth Desconectado", Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        Toast.makeText(getApplicationContext(),"Ocorreu um erro"+ erro, Toast.LENGTH_LONG).show();

                    }
                }else {
                    //conectar
                    Intent abreList = new Intent(TransmitterActivity.this,ListBluetoothActivity.class);
                    startActivityForResult(abreList, SOLICITA_CONEXAO_BLUETTOOTH);
                }
            }
        });

        //WI-FI

        btnWifi = (Button)findViewById(R.id.btnWifi);
        adm_wifi = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adm_wifi.isWifiEnabled()){
                    adm_wifi.setWifiEnabled(!adm_wifi.isWifiEnabled());
                    Toast.makeText(getApplicationContext(),"Wi-Fi ligado", Toast.LENGTH_LONG).show();
                }else {
                    adm_wifi.setWifiEnabled(!adm_wifi.isWifiEnabled());
                    Toast.makeText(getApplicationContext(),"Wi-Fi ligado", Toast.LENGTH_LONG).show();
                }

            }
        });

        //NFC

        btnNfc = (Button)findViewById(R.id.btnNfc);
        final NfcAdapter nfcAdapter = getDefaultAdapter(this);
        adm_nfc = (NfcManager) this.getSystemService(Context.NFC_SERVICE);

        btnNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNfc == null){
                    Toast.makeText(getApplicationContext(),"Dispositivo sem NFC", Toast.LENGTH_LONG).show();
                }else if(nfcAdapter.isEnabled()){
                    Toast.makeText(getApplicationContext(),"NFC Ativado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"NFC  Desligado", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SOLICITA_ATIVACAO_BLUETTOOTH:
                if (resultCode == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(),"Bluetooth foi ativado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Bluetooth não foi ativado", Toast.LENGTH_LONG).show();
                }
                break;
            case SOLICITA_CONEXAO_BLUETTOOTH:
                if (resultCode == Activity.RESULT_OK){
                    MAC = data.getExtras().getString(ListBluetoothActivity.ENDERECO);

                    //Toast.makeText(getApplicationContext(),"Funcionou " +MAC, Toast.LENGTH_LONG).show();

                    bluetoothDevice = mBluetoothAdapter.getRemoteDevice(MAC);

                    try {
                        bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(meu_uuid);
                        bluetoothSocket.connect();
                        conexao = true;
                        Toast.makeText(getApplicationContext(),"Conectado", Toast.LENGTH_LONG).show();
                    }catch (IOException erro){
                        Toast.makeText(getApplicationContext(),"Erro " +erro, Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Falhou", Toast.LENGTH_LONG).show();
                }
        }
    }
}
