package edu.skku.map.pa1;

import android.graphics.Bitmap;

public class Piece {
    int isBlank;
    Bitmap pic;
    int num;

    public Piece(Bitmap img,int sn){
        this.isBlank=0;
        this.pic=img;
        this.num=sn;
    }

}
