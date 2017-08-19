package com.hackathon.dealeron.clozet;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.hackathon.dealeron.clozet.Models.Bottoms.BottomType;
import com.hackathon.dealeron.clozet.Models.Footwear.FootwearType;
import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearType;
import com.hackathon.dealeron.clozet.Models.Tops.TopType;
import com.hackathon.dealeron.clozet.Settings.Gender;
import com.hackathon.dealeron.clozet.Settings.Settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullInventory {

  public static final List<HeadwearType> HEADWEAR_TYPES = new ArrayList<>();
  public static final List<TopType> TOP_TYPES = new ArrayList<>();
  public static final List<BottomType> BOTTOM_TYPES = new ArrayList<>();
  public static final List<FootwearType> FOOTWEAR_TYPES = new ArrayList<>();

  public FullInventory() {
    populateHeadwear();
    populateTops();
    populateBottoms();
    populateFootwear();
  }

  public Drawable GetHeadwearImage(Context context, HeadwearType type){
    switch (type){
      case BaseballCap:
        return context.getResources().getDrawable(R.drawable.baseball_cap, null);
      case Beenie:
        return context.getResources().getDrawable(R.drawable.beenie_, null);
      default:
        return context.getResources().getDrawable(R.drawable.missing_icon, null);
    }
  }

  public Drawable GetTopImage(Context context, TopType type){
    switch (type){
      case ShortSleeveTshirt:
        return context.getResources().getDrawable(R.drawable.short_sleeve_tshirt, null);
      case LongSleeveTshirt:
        return context.getResources().getDrawable(R.drawable.long_sleeve_tshirt, null);
      case ShortSleeveButtonUp:
        return context.getResources().getDrawable(R.drawable.short_sleeve_buttonup, null);
      case LongSleeveButtonUp:
        return context.getResources().getDrawable(R.drawable.long_sleeve_buttonup, null);
      case Dress:
        return context.getResources().getDrawable(R.drawable.dress_, null);
      case RainJacket:
        return context.getResources().getDrawable(R.drawable.rain_jacket, null);
      case Sweater:
        return context.getResources().getDrawable(R.drawable.sweater_, null);
      case WinterJacket:
        return context.getResources().getDrawable(R.drawable.winter_jacket, null);
      case SuitJacket:
        return context.getResources().getDrawable(R.drawable.suit_jacket, null);
      case TankTop:
        return context.getResources().getDrawable(R.drawable.tank_top, null);
      case Hoodie:
        return context.getResources().getDrawable(R.drawable.hoodie_, null);
      case LightJacket:
        return context.getResources().getDrawable(R.drawable.light_jacket, null);
      default:
        return context.getResources().getDrawable(R.drawable.missing_icon, null);
    }
  }

  public Drawable GetBottomImage(Context context, BottomType type){
    switch (type){
      case ShortPants:
        return context.getResources().getDrawable(R.drawable.short_pants, null);
      case LongPants:
        return context.getResources().getDrawable(R.drawable.long_pants, null);
      case ShortJeans:
        return context.getResources().getDrawable(R.drawable.short_jeans, null);
      case LongJeans:
        return context.getResources().getDrawable(R.drawable.long_jeans, null);
      case Skirt:
        return context.getResources().getDrawable(R.drawable.skirt_, null);
      default:
        return context.getResources().getDrawable(R.drawable.missing_icon, null);
    }
  }

  public Drawable GetFootwearImage(Context context, FootwearType type){
    switch (type){
      case Boots:
        return context.getResources().getDrawable(R.drawable.boots_, null);
      case HiTopSneakers:
        return context.getResources().getDrawable(R.drawable.hitop_sneakers, null);
      case LowTopSneakers:
        return context.getResources().getDrawable(R.drawable.lowtop_sneakers, null);
      case DressShoes:
        return context.getResources().getDrawable(R.drawable.dress_shoes, null);
      case FlipFlops:
        return context.getResources().getDrawable(R.drawable.flip_flops, null);
      case Heels:
        return context.getResources().getDrawable(R.drawable.heels_, null);
      default:
        return context.getResources().getDrawable(R.drawable.missing_icon, null);
    }
  }

  private void populateHeadwear() {
    HEADWEAR_TYPES.add(HeadwearType.BaseballCap);
    HEADWEAR_TYPES.add(HeadwearType.Beenie);
  }

  private void populateTops() {
    TOP_TYPES.add(TopType.Hoodie);
    TOP_TYPES.add(TopType.LightJacket);
    TOP_TYPES.add(TopType.LongSleeveButtonUp);
    TOP_TYPES.add(TopType.LongSleeveTshirt);
    TOP_TYPES.add(TopType.RainJacket);
    TOP_TYPES.add(TopType.ShortSleeveButtonUp);
    TOP_TYPES.add(TopType.ShortSleeveTshirt);
    TOP_TYPES.add(TopType.SuitJacket);
    TOP_TYPES.add(TopType.Sweater);
    TOP_TYPES.add(TopType.TankTop);
    TOP_TYPES.add(TopType.WinterJacket);

    if (Settings.GENDER == Gender.FEMALE) {
      TOP_TYPES.add(TopType.Dress);
    }
  }

  private void populateBottoms() {
    BOTTOM_TYPES.add(BottomType.LongJeans);
    BOTTOM_TYPES.add(BottomType.LongPants);
    BOTTOM_TYPES.add(BottomType.ShortJeans);
    BOTTOM_TYPES.add(BottomType.ShortPants);

    if (Settings.GENDER == Gender.FEMALE) {
      BOTTOM_TYPES.add(BottomType.Skirt);
    }
  }

  private void populateFootwear() {
    FOOTWEAR_TYPES.add(FootwearType.Boots);
    FOOTWEAR_TYPES.add(FootwearType.DressShoes);
    FOOTWEAR_TYPES.add(FootwearType.FlipFlops);
    FOOTWEAR_TYPES.add(FootwearType.HiTopSneakers);
    FOOTWEAR_TYPES.add(FootwearType.LowTopSneakers);

    if (Settings.GENDER == Gender.FEMALE) {
      FOOTWEAR_TYPES.add(FootwearType.Heels);
    }
  }
}
