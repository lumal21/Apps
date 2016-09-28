package org.uiot.uiot;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Alexandre <alex.alexandre@redes.unb.br>
 * on 17/09/16.
 */
public class SensorsActivity extends Activity{

    private SensorManager sensorManager;
    private List<Sensor> deviceSensors;
    private ListView sensorListView;
    private TextView qtdSensors;
    private ArrayAdapter<String> adapter;
    private List<String> sensorsNameList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);

        showList();

        selectSensors();

    }



    private void showList(){
        sensorListView = (ListView) findViewById(R.id.list_sensores); // Retrieving a list screen
        qtdSensors = (TextView) findViewById(R.id.qtdSensors);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        sensorsNameList = builderSensorNameList(deviceSensors);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, sensorsNameList); // adapting the list of sensors that can display on the screen
        sensorListView.setAdapter(adapter); // show the list
        sensorListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        qtdSensors.setText(String.valueOf(deviceSensors.size()));
    }

    private List<String> builderSensorNameList(List<Sensor> sensorsList){
        List<String> sensorsName = new ArrayList<String>();

        for(int i = 0; i < sensorsList.size(); i++){
            String name = sensorsList.get(i).getName();
            sensorsName.add(i, name);
        }
        return sensorsName;
    }

    private void selectSensors(){
        Button enableSensors = (Button) findViewById(R.id.idHabilitarSensorbtn);

        enableSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("Os sensores selecionados foram...\n");

                for (int k = 0; k < sensorListView.getCount(); k++){
                    if(sensorListView.isItemChecked(k)){
                        responseText.append("\n" + adapter.getItem(k));
                    }
                }
                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();
            }
        });
    }

}