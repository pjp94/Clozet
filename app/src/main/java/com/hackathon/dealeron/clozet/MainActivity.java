package com.hackathon.dealeron.clozet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.hackathon.dealeron.clozet.Settings.Gender;
import com.hackathon.dealeron.clozet.Settings.Settings;
import com.hackathon.dealeron.clozet.Settings.TemperatureUnit;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().setDisplayShowTitleEnabled(false);

    if (!Settings.INITIAL_SETUP_COMPLETE) {
      SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
      int defaultValue = 0;
      int tempUnit = sharedPref.getInt(getString(R.string.saved_temp_unit), defaultValue);
      int gender = sharedPref.getInt(getString(R.string.saved_gender), defaultValue);
      int zipCode = sharedPref.getInt(getString(R.string.saved_zipcode), defaultValue);

      Settings.UNIT = tempUnit == 0 ? TemperatureUnit.FAHRENHEIT : TemperatureUnit.CELCIUS;
      Settings.GENDER = gender == 0 ? Gender.MALE : Gender.FEMALE;
      Settings.ZIP_CODE = zipCode;
    }

    if (!Settings.isSet()) {
      AlertDialog.Builder settingsBuilder = new AlertDialog.Builder(MainActivity.this);
      LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
      View settingsDialog = inflater.inflate(R.layout.settings_dialog, null);

      settingsBuilder.setView(settingsDialog)
              .setTitle(R.string.set_up)
              .setMessage(R.string.change_settings)
              .setCancelable(false);

      final AlertDialog settings = settingsBuilder.create();
      final RadioGroup tempUnitGroup = (RadioGroup)settingsDialog.findViewById(R.id.radio_temperature);
      final RadioGroup genderGroup = (RadioGroup)settingsDialog.findViewById(R.id.radio_gender);
      final EditText zipCodeText = (EditText)settingsDialog.findViewById(R.id.user_zipcode);
      final Button submitSettings = (Button)settingsDialog.findViewById(R.id.button_initial_settings);

      tempUnitGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
          submitSettings.setEnabled(genderGroup.getCheckedRadioButtonId() != -1 && zipCodeText.getText().length() == 5);
        }
      });

      genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
          submitSettings.setEnabled(tempUnitGroup.getCheckedRadioButtonId() != -1 && zipCodeText.getText().length() == 5);
        }
      });

      zipCodeText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          // do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          submitSettings.setEnabled(genderGroup.getCheckedRadioButtonId() != -1
                  && tempUnitGroup.getCheckedRadioButtonId() != -1
                  && zipCodeText.getText().length() == 5);
        }

        @Override
        public void afterTextChanged(Editable s) {
          // do nothing
        }
      });

      submitSettings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int tempId = tempUnitGroup.getCheckedRadioButtonId();
          int genderId = genderGroup.getCheckedRadioButtonId();
          int zipCode = Integer.parseInt(zipCodeText.getText().toString());

          TemperatureUnit unit  =
                  tempId == R.id.radio_button_fahrenheit
                  ? TemperatureUnit.FAHRENHEIT
                  : TemperatureUnit.CELCIUS;

          Gender gender =
                  genderId == R.id.radio_button_male
                  ? Gender.MALE
                  : Gender.FEMALE;

          Settings.setSettings(unit, gender, zipCode);
          Settings.INITIAL_SETUP_COMPLETE = true;

          settings.dismiss();

          initializeWeather();
        }
      });

      settings.show();
    } else {
      initializeWeather();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    boolean selected;
    Intent intent;

    switch (item.getItemId()) {
      case R.id.menu_settings:
        intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
        selected = true;
        break;
      default:
        selected = super.onOptionsItemSelected(item);
    }

    return selected;
  }

  @Override
  public void onPause() {
    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();

    editor.clear();
    editor.putInt(getString(R.string.saved_temp_unit), Settings.UNIT == TemperatureUnit.FAHRENHEIT ? 0 : 1);
    editor.putInt(getString(R.string.saved_gender), Settings.GENDER == Gender.MALE ? 0 : 1);
    editor.putInt(getString(R.string.saved_zipcode), Settings.ZIP_CODE);
    editor.commit();

    super.onPause();
  }

  private void initializeWeather() {
    WeatherRetriever weatherRetriever = new WeatherRetriever(MainActivity.this);
    weatherRetriever.execute(Settings.ZIP_CODE);
  }

}
