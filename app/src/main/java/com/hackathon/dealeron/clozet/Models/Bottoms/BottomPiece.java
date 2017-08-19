package com.hackathon.dealeron.clozet.Models.Bottoms;

import com.hackathon.dealeron.clozet.Models.ClothingPiece;
import com.hackathon.dealeron.clozet.Models.Color;
import com.hackathon.dealeron.clozet.Models.DressType;
import com.hackathon.dealeron.clozet.Models.WeatherType;

import java.util.List;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public class BottomPiece extends ClothingPiece {
    public BottomType type;
    public BottomPiece(Color color, List<DressType> dressTypes, List<WeatherType> weatherType, BottomType type) {
        super(color, dressTypes, weatherType);
        this.type = type;
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        BottomPiece other =(BottomPiece)obj;
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
