package com.hackathon.dealeron.clozet;

import com.hackathon.dealeron.clozet.Models.Bottoms.BottomPiece;
import com.hackathon.dealeron.clozet.Models.Footwear.FootwearPiece;
import com.hackathon.dealeron.clozet.Models.Headwear.HeadwearPiece;
import com.hackathon.dealeron.clozet.Models.Tops.TopPiece;

import java.util.HashSet;

/**
 * Created by tskalabrin on 8/18/2017.
 */

public class Wardrobe {
    private HashSet<HeadwearPiece> headwear;
    private HashSet<TopPiece> tops;
    private HashSet<BottomPiece> bottoms;
    private HashSet<FootwearPiece> footwear;
    public Wardrobe(){
        headwear = new HashSet<>();
        tops = new HashSet<>();
        bottoms = new HashSet<>();
        footwear = new HashSet<>();
    }
    public Wardrobe(HashSet<HeadwearPiece> headwear, HashSet<TopPiece> tops, HashSet<BottomPiece> bottoms, HashSet<FootwearPiece> footwear){
        this.headwear = headwear;
        this.tops = tops;
        this.bottoms = bottoms;
        this.footwear = footwear;
    }
    public boolean AddHeadwearPiece(HeadwearPiece piece){
        return headwear.add(piece);
    }
    public void AddHeadwearPieces(HashSet<HeadwearPiece> pieces){
        for (HeadwearPiece piece : pieces) {
            AddHeadwearPiece(piece);
        }
    }
    public boolean AddTopPiece(TopPiece piece){
        return tops.add(piece);
    }
    public void AddTopPieces(HashSet<TopPiece> pieces){
        for (TopPiece piece : pieces) {
            AddTopPiece(piece);
        }
    }
    public boolean AddBottomPiece(BottomPiece piece){
        return bottoms.add(piece);
    }
    public void AddBottomPieces(HashSet<BottomPiece> pieces){
        for (BottomPiece piece : pieces) {
            AddBottomPiece(piece);
        }
    }
    public boolean AddFootwearPiece(FootwearPiece piece){
        return footwear.add(piece);
    }
    public void AddFootwearPieces(HashSet<FootwearPiece> pieces){
        for (FootwearPiece piece : pieces) {
            AddFootwearPiece(piece);
        }
    }
}
