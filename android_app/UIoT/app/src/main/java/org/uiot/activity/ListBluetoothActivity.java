package org.uiot.activity;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by Ruan Aragão on 30/09/2016.
 */

public class ListBluetoothActivity extends ListActivity {
    private BluetoothAdapter  meuBluettooth = null;

    static String ENDERECO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluettooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluettooth = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> dispositivosPareados = meuBluettooth.getBondedDevices();

        if(dispositivosPareados.size() > 0){

            for (BluetoothDevice dispositivos : dispositivosPareados){
                String nomeBt = dispositivos.getName();
                String macBt = dispositivos.getAddress();
                ArrayBluettooth.add(nomeBt + "\n" + macBt);
            }
        }
        setListAdapter(ArrayBluettooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String informacao = ((TextView)v).getText().toString();

        //Toast.makeText(getApplicationContext(),"Info" + informacao, Toast.LENGTH_LONG).show();

        String enderecoMac = informacao.substring(informacao.length()-17);

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();
    }
}
