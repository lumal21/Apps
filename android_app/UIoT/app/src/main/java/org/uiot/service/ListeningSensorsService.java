/**
 * Created by Alex Alexandre <alex.alexandre@uiot.ogr>
 * on 28/09/16.
 */

package org.uiot.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.List;


public class ListeningSensorsService extends Service implements SensorEventListener{

    private SensorManager sensorManager;

    // Motion Sensors
    private Sensor accelerometer;
    private Sensor gravity;
    private Sensor gyroscope;
    private Sensor linearAcceleration;

    // Position Sensors
    private Sensor magneticField;
    private Sensor orientation;
    private Sensor proximity;

    // Environment Sensors
    // ....



    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        linearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle i = intent.getExtras();
        List<String> selectedSensorList = (List<String>) i.getSerializable("SENSORSLIST");
        readList(selectedSensorList);

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     *  This method read the list with  sensors selected by the user
     */
    public void readList(List<String> sensorsList){
        for(int i = 0; i < sensorsList.size(); i++){
            activeSensorChoosed(sensorsList.get(i));
        }
    }

    /**
     *  This method enables the sensors selected by the user
     */
    public void activeSensorChoosed(String nameSensor){
        switch (nameSensor){
            case "PSH Accelerometer":
                sensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case "PSH Gravity sensor":
                sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case "PSH Gyroscope sensor":
                sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case "PSH Linear Acceleration sensor":
                sensorManager.registerListener(this, linearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        /*if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelerometerValue(event);
        }*/

        switch (mySensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerValue(event);
                break;
            case Sensor.TYPE_GRAVITY:
                gravityValue(event);
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyroscopeValue(event);
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                linearAccelerationValue(event);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magneticFieldValue(event);
                break;
            case Sensor.TYPE_ORIENTATION:
                orientationValue(event);
                break;
            case Sensor.TYPE_PROXIMITY:
                proximityValue(event);
                break;

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     *  This method return null because this service will be called for an activity (SensorsActivity)
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }




    // ************************** Methods that recover the sensors value **********************************

    public void accelerometerValue(SensorEvent event){
        float x = event.values[0];
        float y = event.values[0];
        float z = event.values[0];

        Toast.makeText(getApplicationContext(), "Acelerometro \n" + "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z, Toast.LENGTH_SHORT).show();
    }
    public void gravityValue(SensorEvent event){
        final float alpha = (float) 0.8;
        float[] gravity = new float[3];

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        Toast.makeText(getApplicationContext(), "GRAVIDADE \n" + "X: " + gravity[0] + "\n" + "Y: " + gravity[1] + "\n" + "Z: " + gravity[2], Toast.LENGTH_SHORT).show();
    }
    public void gyroscopeValue(SensorEvent event){
        float x = event.values[0];
        float y = event.values[0];
        float z = event.values[0];

        Toast.makeText(getApplicationContext(), "Giroscopio \n" + "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z, Toast.LENGTH_SHORT).show();

    }
    public void linearAccelerationValue(SensorEvent event){
        float x = event.values[0];
        float y = event.values[0];
        float z = event.values[0];

        Toast.makeText(getApplicationContext(), "Aceleração LINEAR\n" + "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z, Toast.LENGTH_SHORT).show();
    }
    public void magneticFieldValue(SensorEvent event){

    }
    public void orientationValue(SensorEvent event){

    }
    public void proximityValue(SensorEvent event){

    }

}