package com.hackathon.dealeron.clozet;

import com.hackathon.dealeron.clozet.Models.Bottoms.BottomType;
import com.hackathon.dealeron.clozet.Models.Footwear.FootwearType;
import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearType;
import com.hackathon.dealeron.clozet.Models.Tops.TopType;
import com.hackathon.dealeron.clozet.Settings.Gender;

import java.util.HashSet;
import java.util.Set;

public class FullInventory {

  private static final Set<HeadwearType> HEADWEAR_TYPES = new HashSet<>();
  private static final Set<TopType> TOP_TYPES = new HashSet<>();
  private static final Set<BottomType> BOTTOM_TYPES = new HashSet<>();
  private static final Set<FootwearType> FOOTWEAR_TYPES = new HashSet<>();

  public FullInventory(Gender gender) {
    populateHeadwear();
    populateTops(gender);
    populateBottoms(gender);
    populateFootwear(gender);
  }

  private void populateHeadwear() {
    HEADWEAR_TYPES.add(HeadwearType.Bandana);
    HEADWEAR_TYPES.add(HeadwearType.BaseballCap);
    HEADWEAR_TYPES.add(HeadwearType.Beenie);
    HEADWEAR_TYPES.add(HeadwearType.CowboyHat);
    HEADWEAR_TYPES.add(HeadwearType.Fedora);
    HEADWEAR_TYPES.add(HeadwearType.Scarf);
    HEADWEAR_TYPES.add(HeadwearType.SkiMask);
    HEADWEAR_TYPES.add(HeadwearType.Snapback);
  }

  private void populateTops(Gender gender) {
    TOP_TYPES.add(TopType.Hoodie);
    TOP_TYPES.add(TopType.LightJacket);
    TOP_TYPES.add(TopType.LongSleeveButtonUp);
    TOP_TYPES.add(TopType.LongSleeveTshirt);
    TOP_TYPES.add(TopType.NorthFaceJacket);
    TOP_TYPES.add(TopType.RainJacket);
    TOP_TYPES.add(TopType.ShortSleeveButtonUp);
    TOP_TYPES.add(TopType.ShortSleeveTshirt);
    TOP_TYPES.add(TopType.SuitJacket);
    TOP_TYPES.add(TopType.Sweater);
    TOP_TYPES.add(TopType.TankTop);
    TOP_TYPES.add(TopType.WinterJacket);

    if (gender == Gender.FEMALE) {
      TOP_TYPES.add(TopType.Dress);
    }
  }

  private void populateBottoms(Gender gender) {
    BOTTOM_TYPES.add(BottomType.LongJeans);
    BOTTOM_TYPES.add(BottomType.LongPants);
    BOTTOM_TYPES.add(BottomType.ShortJeans);
    BOTTOM_TYPES.add(BottomType.ShortPants);

    if (gender == Gender.FEMALE) {
      BOTTOM_TYPES.add(BottomType.Leggings);
      BOTTOM_TYPES.add(BottomType.Skirt);
    }
  }

  private void populateFootwear(Gender gender) {
    FOOTWEAR_TYPES.add(FootwearType.Boots);
    FOOTWEAR_TYPES.add(FootwearType.DressShoes);
    FOOTWEAR_TYPES.add(FootwearType.FlipFlops);
    FOOTWEAR_TYPES.add(FootwearType.HiTopSneakers);
    FOOTWEAR_TYPES.add(FootwearType.LowTopSneakers);
    FOOTWEAR_TYPES.add(FootwearType.Sandals);

    if (gender == Gender.FEMALE) {
      FOOTWEAR_TYPES.add(FootwearType.Flats);
      FOOTWEAR_TYPES.add(FootwearType.Heels);
      FOOTWEAR_TYPES.add(FootwearType.Uggs);
    }
  }
}
