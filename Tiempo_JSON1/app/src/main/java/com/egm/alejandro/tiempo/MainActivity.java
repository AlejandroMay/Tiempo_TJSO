package com.egm.alejandro.tiempo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONException;

import java.util.ArrayList;

import model.Weather;


public class MainActivity extends ActionBarActivity {

    ListView listado;

    ArrayList<List> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String city = "Cancun,MX";

        listado= (ListView) findViewById(R.id.listado);
        datos = new ArrayList<List>();
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon


            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }




        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);


            for (int i = 0; i < 7; i++) {
                switch (i){


                    case 0: datos.add(new List("Lunes",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 273.15)) + "°C",R.drawable.sol));
                        break;
                    case 1: datos.add(new List("Martes",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 275.10)) + "°C",R.drawable.sol));
                        break;
                    case 2: datos.add(new List("Miercoles",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 273.15)) + "°C",R.drawable.nube));
                        break;
                    case 3: datos.add(new List("Jueves",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 272.10)) + "°C",R.drawable.sol));
                        break;
                    case 4: datos.add(new List("Viernes",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 274.12)) + "°C",R.drawable.lluvia));
                        break;
                    case 5: datos.add(new List("Sabado",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 276.10)) + "°C",R.drawable.sol));
                        break;
                    case 6: datos.add(new List("Domingo",weather.currentCondition.getDescr(),"" + Math.round((weather.temperature.getTemp() - 275.12)) + "°C",R.drawable.sol));
                        break;
                }
            }

            adaptador adaptadorData = new adaptador(datos,MainActivity.this.getApplicationContext());

            listado.setAdapter(adaptadorData);
            listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {

                    List seleccionado = (List) pariente.getItemAtPosition(posicion);

                    Intent intent = new Intent(getBaseContext(), WeatherActivity.class);

                    intent.putExtra("dia", seleccionado.getDia());
                    intent.putExtra("estado", seleccionado.getEstado());
                    intent.putExtra("temperatura", seleccionado.getTemperatura());
                    intent.putExtra("imagen", seleccionado.getImage());
                    startActivity(intent);
                }
            });
        }

    }
}


