package edu.skku.map.pa1;

import android.graphics.Bitmap;
import android.widget.GridView;
import android.widget.Toast;


import java.util.Collections;

public class PuzzleAct {
    int partNum=0;

    public PuzzleAct(int puzzlePart){
        this.partNum = puzzlePart;
    }

    public int isPuzzleFinished(Piece[] pieces,int part){
        int end=0;
        if(part ==3){
            for(int i=0;i<pieces.length;i++){
                if(pieces[i].num==i){
                    continue;
                }
                else{
                    end++;
                }
            }
        }
        else{
            for(int i=0;i<pieces.length;i++){
                if(pieces[i].num==i){
                    continue;
                }
                else {
                    end++;
                }
            }
        }
        return end;
    }

}
