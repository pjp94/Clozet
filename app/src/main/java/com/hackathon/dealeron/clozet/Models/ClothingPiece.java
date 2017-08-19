package com.hackathon.dealeron.clozet.Models;

import java.util.List;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public abstract class ClothingPiece {
    public Color color;
    public List<DressType> dressTypes;
    public List<WeatherType> weatherTypes;
    public ClothingPiece(Color color, List<DressType> dressTypes, List<WeatherType> weatherTypes)
    {
        this.color = color;
        this.dressTypes = dressTypes;
        this.weatherTypes = weatherTypes;
    }
    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ClothingPiece other =(ClothingPiece)obj;
        if (this.dressTypes.size() != other.dressTypes.size()){
            return false;
        }
        if (this.weatherTypes.size() != other.weatherTypes.size()){
            return false;
        }
        if (!this.color.equals(other.color)){
            return false;
        }
        for (DressType dressType : this.dressTypes)
        {
            if (!other.dressTypes.contains(dressType)){
                return false;
            }
        }
        for (WeatherType weatherType : this.weatherTypes)
        {
            if (!other.weatherTypes.contains(weatherType)){
                return false;
            }
        }
        return true;
    }
}
