/**
 * Created by Alex Alexandre <alex.alexandre@uiot.org>
 * on 10/10/16.
 */

package org.uiot.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import org.uiot.uiot.R;
import org.uiot.utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;


public class DeviceActivity extends Activity {
    private EditText deviceName;
    private Button btnDeviceNext;
    private WifiManager wifiManager;
    private static String ipAddress = "";
    private static String macpAddress = "";
    private HashMap<String, String> data;
    private String url = "http://raise.uiot.org/devices";
    private String method = "POST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_device);
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        buttonDevice();
    }

    /**
     * This's the most important method on this class, he call the ip/mac method,
     * to verify the ip. And will create the intents to call a self-register device service
     * and the sensors activity
     */
    private void buttonDevice(){
        btnDeviceNext = (Button) findViewById(R.id.idBtnDeviceNext);
        btnDeviceNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceName = (EditText) findViewById(R.id.idDeviceName);
                ipAddress = getIp();
                validateIp(ipAddress);
                macpAddress = getMac();

//                Toast.makeText(getApplicationContext(), ipAddress, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), macpAddress, Toast.LENGTH_SHORT).show();



                //Estou presisando saber como passar as request

//                data = new HashMap<String, String>();
//                data.put("name", deviceName.toString());
//                data.put("chipset_id", ipAddress);
//                data.put("mac", macpAddress);
//
//                try {
//                    String retorno= HttpUtils.makeRequest(url,method,data);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }



                startActivity(new Intent(DeviceActivity.this, SensorsActivity.class));
            }
        });
    }

    /**
     * This method get the ip address
     * @return String ip address
     */
    private String getIp(){
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }

    /**
     * This method get de mac address
     * @return String mac address
     */
    private  String getMac(){
        return wifiManager.getConnectionInfo().getMacAddress();
    }

    /**
     * This method will verify if getIp() returns a valid value,
     * if not, will ask to the user if want enter manually or stop the app
     */
    private void validateIp(String ip){
        if (ip.equals("0.0.0.0")){
            builderIpDialog();
        }
    }

    /**
     * This method builds the ip error message
     */
    private void builderIpDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.ip_error_dialog_message)
                .setTitle(R.string.msg_attention);

        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton(R.string.btn_continue,new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), input.getText().toString().trim(),
                        Toast.LENGTH_SHORT).show();
            }

        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), R.string.msg_cancel,
                        Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}