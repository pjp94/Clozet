package com.hackathon.dealeron.clozet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hackathon.dealeron.clozet.Settings.Settings;

public class FullInventoryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_inventory);
    getSupportActionBar().setTitle(R.string.inventory);

    WeatherRetriever weatherRetriever = new WeatherRetriever(this);
    weatherRetriever.execute(Settings.ZIP_CODE);
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
      case R.id.menu_home:
        intent = new Intent(FullInventoryActivity.this, MainActivity.class);
        startActivity(intent);
        selected = true;
        break;
      case R.id.menu_settings:
        intent = new Intent(FullInventoryActivity.this, SettingsActivity.class);
        startActivity(intent);
        selected = true;
        break;
      default:
        selected = super.onOptionsItemSelected(item);
    }

    return selected;
  }
}
