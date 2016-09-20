package org.uiot.uiot;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Alex Alexandre <alex.alexandre@redes.unb.br>
 * on 17/09/16.
 */
public class SensorsActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensors);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL); // Creating a device sensors list

        ListView sensorListView = (ListView) findViewById(R.id.list_sensores); // Retrieving a list screen

        ArrayAdapter<Sensor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceSensors); // adapting the list of sensors that can display on the screen
        sensorListView.setAdapter(adapter); // show de list

    }

}
