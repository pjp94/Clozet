package com.hackathon.dealeron.clozet;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearType;
import com.hackathon.dealeron.clozet.Settings.Settings;

public class FullInventoryActivity extends AppCompatActivity {

  private TextView headwearTitle;
  private LinearLayout headwearSelector;
  private ImageView headwearItem;
  private FullInventory inventory;
  private ImageButton headwearLeft;
  private ImageButton headwearRight;
  private int currenHeadwearPosition;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_inventory);
    getSupportActionBar().setTitle(R.string.inventory);

    WeatherRetriever weatherRetriever = new WeatherRetriever(this);
    weatherRetriever.execute(Settings.ZIP_CODE);

    inventory = new FullInventory();
    currenHeadwearPosition = 0;

    headwearRight = (ImageButton)findViewById(R.id.headwear_right);
    headwearRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenHeadwearPosition < inventory.HEADWEAR_TYPES.size() - 1){
          currenHeadwearPosition++;
        }
        else{
          currenHeadwearPosition = 0;
        }
        headwearItem.setImageDrawable(GetHeadwearImage(inventory.HEADWEAR_TYPES.get(currenHeadwearPosition)));
      }
    });
    headwearLeft = (ImageButton)findViewById(R.id.headwear_left);
    headwearItem = (ImageView)findViewById(R.id.headwear_item);
    headwearSelector = (LinearLayout)findViewById(R.id.headwear_selector);
    headwearTitle = (TextView)findViewById(R.id.collapse_headwear);
    headwearTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (headwearSelector.getVisibility() != View.VISIBLE) {
          headwearSelector.setVisibility(View.VISIBLE);
        }
        else{
          headwearSelector.setVisibility(View.GONE);
        }
        if(headwearItem.getDrawable() == null){
          headwearItem.setImageDrawable(GetHeadwearImage(inventory.HEADWEAR_TYPES.get(0)));
        }
      }

    });
  }

  private Drawable GetHeadwearImage(HeadwearType type){
    switch (type){
      case BaseballCap:
        return getResources().getDrawable(R.drawable.baseball_cap, null);
      case Beenie:
        return getResources().getDrawable(R.drawable.beenie_, null);
      default:
        return null;
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
