package com.hackathon.dealeron.clozet.Models.Headwear;

import com.hackathon.dealeron.clozet.Models.ClothingPiece;
import com.hackathon.dealeron.clozet.Models.Color;
import com.hackathon.dealeron.clozet.Models.DressType;
import com.hackathon.dealeron.clozet.Models.WeatherType;

import java.util.List;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public class HeadwearPiece extends ClothingPiece {
    public HeadwearType type;
    public HeadwearPiece(Color color, List<DressType> dressTypes, List<WeatherType> weatherType, HeadwearType type) {
        super(color, dressTypes, weatherType);
        this.type = type;
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        HeadwearPiece other =(HeadwearPiece)obj;
        if (this.type != other.type) {
            return false;
        }
        return super.equals(other);
    }
    @Override
    public int hashCode() {
        return 0;
    }
}
