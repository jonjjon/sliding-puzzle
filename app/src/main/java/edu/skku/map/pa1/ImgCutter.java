package edu.skku.map.pa1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


public class ImgCutter extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new ImgView3(this));
        setContentView(new ImgView4(this));
    }

    class ImgView3 extends View {
        int width,height;
        int left,top;
        int orgWidth, orgHeight;
        int partWidth, partHeight;
        Bitmap imgOrg;
        Bitmap[][] imgPic = new Bitmap[3][3];
        Bitmap[] img;

        public ImgView3(Context context){
            super(context);
            Display display =((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
            imgOrg = BitmapFactory.decodeResource(context.getResources(), R.drawable.img);
            imgOrg = Bitmap.createScaledBitmap(imgOrg, 300, 300, true);
            orgWidth = imgOrg.getWidth();
            orgHeight = imgOrg.getHeight();

            partWidth = orgWidth/3;
            partHeight = orgHeight/3;

            left = (width-orgWidth)/2;
            top = (height-orgHeight)/2;

            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++) {
                    imgPic[i][j]=Bitmap.createBitmap(imgOrg, j*partWidth,i*partHeight,partWidth, partHeight);
                }
                imgPic[2][2]=Bitmap.createBitmap(imgOrg,123,40,1,1); //공란 //200,1 로 바꾸기
            }
            img= new Bitmap[]{ imgPic[0][0], imgPic[0][1], imgPic[0][2], imgPic[1][0], imgPic[1][1], imgPic[1][2],
                imgPic[2][0], imgPic[2][1], imgPic[2][2]};

        }

    }
    class ImgView4 extends View {
        int width,height;
        int left,top;
        int orgWidth, orgHeight;
        int partWidth, partHeight;
        Bitmap imgOrg;
        Bitmap[][] imgPic = new Bitmap[4][4];
        Bitmap[] img;
        Piece[] pieces;

        public ImgView4(Context context){
            super(context);
            Display display =((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
            imgOrg = BitmapFactory.decodeResource(context.getResources(), R.drawable.img);
            imgOrg = Bitmap.createScaledBitmap(imgOrg, 300, 300, true);
            orgWidth = imgOrg.getWidth();
            orgHeight = imgOrg.getHeight();

            partWidth = orgWidth/4;
            partHeight = orgHeight/4;

            left = (width-orgWidth)/3;
            top = (height-orgHeight)/3;

            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++) {
                    imgPic[i][j]=Bitmap.createBitmap(imgOrg, j*partWidth,i*partHeight,partWidth, partHeight);
                }
                imgPic[3][3]=Bitmap.createBitmap(imgOrg,200,1,1,1); //공란
            }
            img= new Bitmap[]{ imgPic[0][0], imgPic[0][1], imgPic[0][2], imgPic[0][3],
                    imgPic[1][0], imgPic[1][1], imgPic[1][2],imgPic[1][3],
                    imgPic[2][0], imgPic[2][1], imgPic[2][2], imgPic[2][3],
                    imgPic[3][0], imgPic[3][1], imgPic[3][2], imgPic[3][3]};

        }
    }

}