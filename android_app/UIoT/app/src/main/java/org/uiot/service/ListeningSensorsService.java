/**
 * Created by Alex Alexandre <alex.alexandre@redes.unb.br> on 28/09/16.
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

    private SensorManager mSensorManager;

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
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        linearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        magneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        orientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        proximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(this, accelerometer , SensorManager.SENSOR_DELAY_NORMAL);
//        mSensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle i = intent.getExtras();
        List<String> listasss = (List<String>) i.getSerializable("LISTA");


        Toast.makeText(getApplicationContext(), ""+listasss, Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
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
        mSensorManager.unregisterListener(this);
    }


    // ************************** Methods that recover the sensors value **********************************

    public void accelerometerValue(SensorEvent event){
        float x = event.values[0];
        float y = event.values[0];
        float z = event.values[0];

        Toast.makeText(getApplicationContext(), "Acelerometro \n" + "X: " + x + "\n" + "Y: " + y + "\n" + "Z: " + z, Toast.LENGTH_SHORT).show();
    }
    public void gravityValue(SensorEvent event){

    }
    public void gyroscopeValue(SensorEvent event){

    }
    public void linearAccelerationValue(SensorEvent event){

    }
    public void magneticFieldValue(SensorEvent event){

    }
    public void orientationValue(SensorEvent event){

    }
    public void proximityValue(SensorEvent event){

    }

    /**
     *  This method enables the sensors selected by the user
     */
    public void callRightSensors(Sensor sensor, SensorEvent event){


    }

}