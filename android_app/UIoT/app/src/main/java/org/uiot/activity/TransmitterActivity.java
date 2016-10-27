package org.uiot.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.uiot.uiot.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruan Aragão on 30/09/2016.
 */

public class TransmitterActivity extends Activity {
    private ListView transmitterListView;
    ArrayAdapter arrayAdapter;

    BluetoothAdapter bluetoothAdapter = null;
    NfcAdapter nfcAdapter;

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transmitter);

        open();
    }

    private void open(){

        checkConnection();

    }

    private ArrayList<String> showList(){
        ArrayList<String> dados = new ArrayList<>();
        if(checkBluetooth()){
            dados.add("BLUETOOTH");
        }
        if(checdNfc()){
            dados.add("NFC");
        }
        dados.add("Wi-Fi");
        dados.add("Redes Moveis");
        return dados;
    }

    private void checkConnection(){
        connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            transmitterView();
            selectSensors();
        }else{
            AlertDialog();
        }
    }

    private Boolean checkBluetooth(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null;
    }

    private Boolean checdNfc(){
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        return nfcAdapter != null;

    }

    private void transmitterView(){
        transmitterListView = (ListView) findViewById(R.id.list_sensores);
        ArrayList<String> teste = showList();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, teste);
        transmitterListView.setAdapter(arrayAdapter);
        transmitterListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    private void AlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.msg_attention);
        builder.setMessage(R.string.msg_check_internet);

        //SET POSITIVE
        connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiManager = (WifiManager)this.getSystemService(Context.WIFI_SERVICE);
        builder.setPositiveButton(R.string.btn_turn_on_wifi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final ProgressDialog dialog1 = new ProgressDialog(TransmitterActivity.this);

                        dialog1.setMessage("Conectando com WiFi");
                        dialog1.setIndeterminate(false);
                        dialog1.setCanceledOnTouchOutside(true);
                        dialog1.setCancelable(true);
                        dialog1.show();

                        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());

                        if(wifiManager.isWifiEnabled()){
                            open();
                            dialog1.dismiss();
                        }

                    }
                }
        );

        //SET NEGATIVE
        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                open();
            }
        });

        AlertDialog ad=builder.create();
        ad.show();
    }

    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        conectado = conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected();
        return conectado;
    }


    private void selectSensors(){
        Button enableSensors = (Button) findViewById(R.id.idHabilitarSensorbtn);
        enableSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();
                responseText.append("Os sensores selecionados foram...\n");
                List<String> selectedSensorsList = new ArrayList<>();
                int count = 0;

                for (int k = 0; k < transmitterListView.getCount(); k++){
                    if(transmitterListView.isItemChecked(k)){
                        responseText.append("\n").append(arrayAdapter.getItem(k));
                        count++;
                    }
                }

                Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();

            }
        });
    }
}
