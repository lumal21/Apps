/**
 * Created by Alex Alexandre <alex.alexandre@uiot.org>
 * on 17/09/16.
 */

package org.uiot.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.uiot.service.ListeningSensorsService;
import org.uiot.uiot.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * This method I set all values ​​in the correct variables to display a sensors list on the screen
     */
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

    /**
     * This method is called by the showList method to build a list with all the sensors enable in the device
     */
    private List<String> builderSensorNameList(List<Sensor> sensorsList){
        List<String> sensorsName = new ArrayList<>();

        for(int i = 0; i < sensorsList.size(); i++){
            String name = sensorsList.get(i).getName();
            sensorsName.add(i, name);
        }
        return sensorsName;
    }


    /**
     * This method create the button to select the sensors,
     * and capture the selected sensors to send the sensors list service
     */
    private void selectSensors(){
        Button enableSensors = (Button) findViewById(R.id.idBtnHabilitarSensor);
        enableSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();
                responseText.append("Os sensores selecionados foram...\n");
                List<String> selectedSensorsList = new ArrayList<String>();
                int count = 0;

                for (int k = 0; k < sensorListView.getCount(); k++){
                    if(sensorListView.isItemChecked(k)){
                        responseText.append("\n" + adapter.getItem(k));
                        selectedSensorsList.add(count, adapter.getItem(k).toString());
                        count++;
                    }
                }

//                Toast.makeText(getApplicationContext(), responseText, Toast.LENGTH_LONG).show();


                Intent iSensor = new Intent(SensorsActivity.this, ListeningSensorsService.class);
                Bundle b = new Bundle();
                TransformDataExport dadoPronto = new TransformDataExport(selectedSensorsList);


                b.putSerializable("SENSORSLIST", (Serializable) dadoPronto.getList());
                iSensor.putExtras(b);

                startService(iSensor);
                //startService(new Intent(SensorsActivity.this, ListeningSensorsService.class));
                startActivity(new Intent(SensorsActivity.this, TransmitterActivity.class));

            }
        });
    }


    /**
     * This method turns the sensors list selected by the user
     * in a serializable data to send to the listening sensors service
     */
    public class TransformDataExport implements Serializable {
        private List<String> sensorsSerial;

        public TransformDataExport(List<String> sensorsSerial) {
            this.sensorsSerial = sensorsSerial;
        }

        public List<String> getList() {
            return this.sensorsSerial;
        }
    }
}