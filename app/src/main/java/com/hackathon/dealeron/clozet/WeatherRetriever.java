package com.hackathon.dealeron.clozet;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherRetriever extends AsyncTask<Integer, Void, String> {

  private static final String API_KEY = "253bc9eac2015fe7eb6ac0b53a7bbbd6";

  private Activity activity;

  public WeatherRetriever(Activity activity) {
    this.activity = activity;
  }

  @Override
  protected String doInBackground(Integer... zipCode) {
    String output = "";

    try {
      URL url = new URL(String.format("http://api.openweathermap.org/data/2.5/weather?zip=%d&units=imperial&appid=%s", zipCode[0], API_KEY));
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();

      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");

      if (connection.getResponseCode() != 200) {
        throw new RuntimeException("HTTP GET failed. Error: " + connection.getResponseCode());
      }

      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      output = br.readLine();

      connection.disconnect();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return output;
  }

  @Override
  protected void onPostExecute(String result) {
    TextView weatherText = (TextView)activity.findViewById(R.id.weather_text);
    weatherText.setText(result);
  }
}
