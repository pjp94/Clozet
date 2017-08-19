package com.hackathon.dealeron.clozet;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeatherRetriever extends AsyncTask<Integer, Void, String> {

  private static final String API_KEY = "253bc9eac2015fe7eb6ac0b53a7bbbd6";
  private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?zip=%d&units=imperial&appid=%s";

  private Activity activity;

  public WeatherRetriever(Activity activity) {
    this.activity = activity;
  }

  @Override
  protected String doInBackground(Integer... zipCode) {
    String output = "";

    try {
      URL url = new URL(String.format(API_URL, zipCode[0], API_KEY));
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
    try {
      JSONObject jObject = new JSONObject(result);
      JSONArray jArray = jObject.getJSONArray("weather");
      JSONObject weatherInfo = jArray.getJSONObject(0);

      int code = (int)weatherInfo.get("id");
      int temperature = jObject.getJSONObject("main").getInt("temp");
      String weather = (String)weatherInfo.get("main");

      JSONObject system = jObject.getJSONObject("sys");

      long sunrise = system.getLong("sunrise");
      long sunset = system.getLong("sunset");
      Map<String, Long> sunTimes = new HashMap<>();

      sunTimes.put("sunrise", sunrise);
      sunTimes.put("sunset", sunset);

      Drawable icon = getWeatherIcon(code, sunTimes);
      ImageView weatherIcon = (ImageView)activity.findViewById(R.id.weather_icon);
      TextView weatherData = (TextView)activity.findViewById(R.id.weather_data);

      weatherIcon.setImageDrawable(icon);
      weatherData.setText(temperature + "°F\n" + weather);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private Drawable getWeatherIcon(int code, Map<String, Long> sunTimes) {
    Drawable weatherIcon = null;
    Resources resources = activity.getResources();
    long sunrise = sunTimes.get("sunrise");
    long sunset = sunTimes.get("sunset");
    long currentTime = System.currentTimeMillis();
    boolean isDaytime = currentTime >= sunrise && currentTime <= sunset;

    if (code >= 200 && code <= 232) {
      weatherIcon = resources.getDrawable(R.drawable.icon_thunderstorm, null);
    } else if ((code >= 300 && code <= 321) || (code >= 520 && code <= 531)) {
      weatherIcon = resources.getDrawable(R.drawable.icon_rain_shower, null);
    } else if (code >= 500 && code <= 504) {
      weatherIcon = resources.getDrawable(isDaytime ? R.drawable.icon_rain_day : R.drawable.icon_rain_night, null);
    } else if (code == 511 || (code >= 600 && code <= 622)) {
      weatherIcon = resources.getDrawable(R.drawable.icon_snow, null);
    } else if (code >= 701 && code <= 781) {
      weatherIcon = resources.getDrawable(isDaytime ? R.drawable.icon_mist_day: R.drawable.icon_mist_night, null);
    } else if (code == 800) {
      weatherIcon = resources.getDrawable(isDaytime ? R.drawable.icon_day : R.drawable.icon_night, null);
    } else if (code == 801) {
      weatherIcon = resources.getDrawable(isDaytime ? R.drawable.icon_partly_cloudy_day : R.drawable.icon_partly_cloudy_night, null);
    } else if (code == 802 || code == 803 || code == 804) {
      weatherIcon = resources.getDrawable(R.drawable.icon_cloudy, null);
    }

    return weatherIcon;
  }
}
