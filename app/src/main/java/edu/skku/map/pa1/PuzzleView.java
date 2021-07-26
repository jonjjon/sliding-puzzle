package edu.skku.map.pa1;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PuzzleView extends BaseAdapter {
    private Context context;
    public Bitmap[] img;
    public int partNum = 0;
    public int emptyIndex=0;
    public Piece[] pieces3 = new Piece[9];
    public Piece[] pieces4 = new Piece[16];


    public PuzzleView(Context context,int num, Bitmap[] imgList) {
        this.context = context;
        if(num==3){
            this.partNum=3;
            this.img = imgList;
            this.emptyIndex = 8;
            for(int i=0;i<9;i++){
                pieces3[i]=new Piece(imgList[i],i);
                if(i==8){
                    pieces3[i].isBlank=1;
                }
            }

        }
        if(num==4) {
            this.partNum=4;
            this.img = imgList;
            this.emptyIndex = 15;
            for(int i=0;i<16;i++){
                pieces4[i]= new Piece(imgList[i],i);
                if(i==15){
                    pieces4[i].isBlank=1;
                }
            }
        }


    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        if(partNum==3){
            return pieces3[position];
        }
        else{
            return pieces4[position];
        }

    }

    @Override
    public long getItemId(int position) {
        Exception e= new Exception();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;
        if(convertView==null){
            imageView =  new ImageView(context);
        }
        else{
            imageView = (ImageView) convertView;
        }
        if(partNum==3){
            imageView.setLayoutParams(new ViewGroup.LayoutParams(260,275));
        }
        if(partNum==4){
            imageView.setLayoutParams(new ViewGroup.LayoutParams(190,200));
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(1,1,1,1);

        if(partNum==3){
            imageView.setImageBitmap(pieces3[position].pic);
        }
        if(partNum==4){
            imageView.setImageBitmap(pieces4[position].pic);
        }

        return imageView;
    }


}
