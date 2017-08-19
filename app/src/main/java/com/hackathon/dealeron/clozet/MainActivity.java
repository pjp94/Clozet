package com.hackathon.dealeron.clozet;

import android.app.ActionBar;
import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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

    if (!Settings.IS_SET) {
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

          TemperatureUnit unit  = tempId == 0
                  ? TemperatureUnit.FAHRENHEIT
                  : TemperatureUnit.CELCIUS;

          Gender gender = genderId == 0
                  ? Gender.MALE
                  : Gender.FEMALE;

          Settings.setSettings(unit, gender, zipCode);
          Settings.IS_SET = true;

          settings.dismiss();

          WeatherRetriever weatherRetriever = new WeatherRetriever(MainActivity.this);
          weatherRetriever.execute(20855);
        }
      });

      settings.show();
    }


  }

}
