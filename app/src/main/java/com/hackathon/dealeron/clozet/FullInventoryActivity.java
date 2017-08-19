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

import com.hackathon.dealeron.clozet.Models.Bottoms.BottomType;
import com.hackathon.dealeron.clozet.Models.Footwear.FootwearType;
import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearType;
import com.hackathon.dealeron.clozet.Models.Tops.TopType;
import com.hackathon.dealeron.clozet.Settings.Settings;

public class FullInventoryActivity extends AppCompatActivity {

  private TextView headwearTitle;
  private LinearLayout headwearSelector;
  private ImageView headwearItem;
  private ImageButton headwearLeft;
  private ImageButton headwearRight;
  private int currenHeadwearPosition;
  //
  private TextView topTitle;
  private LinearLayout topSelector;
  private ImageView topItem;
  private ImageButton topLeft;
  private ImageButton topRight;
  private int currenTopPosition;
  //
  private TextView bottomTitle;
  private LinearLayout bottomSelector;
  private ImageView bottomItem;
  private ImageButton bottomLeft;
  private ImageButton bottomRight;
  private int currenBottomPosition;
  //
  private TextView footwearTitle;
  private LinearLayout footwearSelector;
  private ImageView footwearItem;
  private ImageButton footwearLeft;
  private ImageButton footwearRight;
  private int currenFootwearPosition;
  //
  private FullInventory inventory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_inventory);
    getSupportActionBar().setTitle(R.string.inventory);

    WeatherRetriever weatherRetriever = new WeatherRetriever(this);
    weatherRetriever.execute(Settings.ZIP_CODE);

    inventory = new FullInventory();

    InitializeHeadwearSection();
    InitializeTopSection();
    InitializeBottomSection();
    InitializeFootwearSection();
  }

  private void InitializeHeadwearSection(){
    currenHeadwearPosition = 0;
    headwearSelector = (LinearLayout)findViewById(R.id.headwear_selector);
    headwearItem = (ImageView)findViewById(R.id.headwear_item);

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
    headwearLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenHeadwearPosition > 0){
          currenHeadwearPosition--;
        }
        else{
          currenHeadwearPosition = inventory.HEADWEAR_TYPES.size() - 1;
        }
        headwearItem.setImageDrawable(GetHeadwearImage(inventory.HEADWEAR_TYPES.get(currenHeadwearPosition)));
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

  private void InitializeTopSection(){
    currenTopPosition = 0;
    topSelector = (LinearLayout)findViewById(R.id.top_selector);
    topItem = (ImageView)findViewById(R.id.top_item);

    topTitle = (TextView)findViewById(R.id.collapse_top);
    topTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (topSelector.getVisibility() != View.VISIBLE) {
          topSelector.setVisibility(View.VISIBLE);
        }
        else{
          topSelector.setVisibility(View.GONE);
        }
        if(topItem.getDrawable() == null){
          topItem.setImageDrawable(GetTopImage(inventory.TOP_TYPES.get(0)));
        }
      }

    });

    topRight = (ImageButton)findViewById(R.id.top_right);
    topRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenTopPosition < inventory.TOP_TYPES.size() - 1){
          currenTopPosition++;
        }
        else{
          currenTopPosition = 0;
        }
        topItem.setImageDrawable(GetTopImage(inventory.TOP_TYPES.get(currenTopPosition)));
      }
    });

    topLeft = (ImageButton)findViewById(R.id.top_left);
    topLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenTopPosition > 0){
          currenTopPosition--;
        }
        else{
          currenTopPosition = inventory.TOP_TYPES.size() - 1;
        }
        topItem.setImageDrawable(GetTopImage(inventory.TOP_TYPES.get(currenTopPosition)));
      }
    });
  }

  private Drawable GetTopImage(TopType type){
    switch (type){
      default:
        return null;
    }
  }

  private void InitializeBottomSection(){
    currenBottomPosition = 0;
    bottomSelector = (LinearLayout)findViewById(R.id.bottom_selector);
    bottomItem = (ImageView)findViewById(R.id.bottom_item);

    bottomTitle = (TextView)findViewById(R.id.collapse_bottom);
    bottomTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (bottomSelector.getVisibility() != View.VISIBLE) {
          bottomSelector.setVisibility(View.VISIBLE);
        }
        else{
          bottomSelector.setVisibility(View.GONE);
        }
        if(bottomItem.getDrawable() == null){
          bottomItem.setImageDrawable(GetBottomImage(inventory.BOTTOM_TYPES.get(0)));
        }
      }

    });

    bottomRight = (ImageButton)findViewById(R.id.bottom_right);
    bottomRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenBottomPosition < inventory.BOTTOM_TYPES.size() - 1){
          currenBottomPosition++;
        }
        else{
          currenBottomPosition = 0;
        }
        bottomItem.setImageDrawable(GetBottomImage(inventory.BOTTOM_TYPES.get(currenBottomPosition)));
      }
    });

    bottomLeft = (ImageButton)findViewById(R.id.bottom_left);
    bottomLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenBottomPosition > 0){
          currenBottomPosition--;
        }
        else{
          currenBottomPosition = inventory.BOTTOM_TYPES.size() - 1;
        }
        bottomItem.setImageDrawable(GetBottomImage(inventory.BOTTOM_TYPES.get(currenBottomPosition)));
      }
    });
  }

  private Drawable GetBottomImage(BottomType type){
    switch (type){
      default:
        return null;
    }
  }

  private void InitializeFootwearSection(){
    currenFootwearPosition = 0;
    footwearSelector = (LinearLayout)findViewById(R.id.footwear_selector);
    footwearItem = (ImageView)findViewById(R.id.footwear_item);

    footwearTitle = (TextView)findViewById(R.id.collapse_footwear);
    footwearTitle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (footwearSelector.getVisibility() != View.VISIBLE) {
          footwearSelector.setVisibility(View.VISIBLE);
        }
        else{
          footwearSelector.setVisibility(View.GONE);
        }
        if(footwearItem.getDrawable() == null){
          footwearItem.setImageDrawable(GetFootwearImage(inventory.FOOTWEAR_TYPES.get(0)));
        }
      }

    });

    footwearRight = (ImageButton)findViewById(R.id.footwear_right);
    footwearRight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenFootwearPosition < inventory.FOOTWEAR_TYPES.size() - 1){
          currenFootwearPosition++;
        }
        else{
          currenFootwearPosition = 0;
        }
        footwearItem.setImageDrawable(GetFootwearImage(inventory.FOOTWEAR_TYPES.get(currenFootwearPosition)));
      }
    });

    footwearLeft = (ImageButton)findViewById(R.id.footwear_left);
    footwearLeft.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currenFootwearPosition > 0){
          currenFootwearPosition--;
        }
        else{
          currenFootwearPosition = inventory.FOOTWEAR_TYPES.size() - 1;
        }
        footwearItem.setImageDrawable(GetFootwearImage(inventory.FOOTWEAR_TYPES.get(currenFootwearPosition)));
      }
    });
  }

  private Drawable GetFootwearImage(FootwearType type){
    switch (type){
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
