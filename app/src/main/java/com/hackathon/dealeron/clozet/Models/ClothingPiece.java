package com.hackathon.dealeron.clozet.Models;

import java.util.List;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public abstract class ClothingPiece {
    public Color color;
    public List<DressType> dressTypes;
    public List<WeatherType> weatherType;
    public ClothingPiece(Color color, List<DressType> dressTypes, List<WeatherType> weatherType)
    {
        this.color = color;
        this.dressTypes = dressTypes;
        this.weatherType = weatherType;
    }
}
