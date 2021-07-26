package edu.skku.map.pa1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Button btn3;
    Button btn4;
    Button shuffle;
    int currentPuzzle = 3;
    Piece blank3, blank4;
    int blankIndex3, blankIndex4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImgCutter.ImgView3 part3 = new ImgCutter().new ImgView3(this);
        ImgCutter.ImgView4 part4 = new ImgCutter().new ImgView4(this);
        PuzzleAct puzzleInGame = new PuzzleAct(currentPuzzle);
        Bitmap[] answer3 = part3.img;
        Bitmap[] answer4 = part4.img;


        final PuzzleView[] puzzle3 = {new PuzzleView(this, 3, part3.img)};
        final PuzzleView[] puzzle4 = {new PuzzleView(this, 4, part4.img)};

        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(puzzle3[0]); //default는 3*3


        btn3=(Button)findViewById(R.id.btn33);
        btn4=(Button)findViewById(R.id.btn44);
        shuffle=(Button)findViewById(R.id.btnShuffle);
        btn3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentPuzzle = 3;
                gridView.setNumColumns(currentPuzzle);
                puzzle3[0] =new PuzzleView(getApplicationContext(),3,answer3);
                gridView.setAdapter(puzzle3[0]);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currentPuzzle = 4;
                gridView.setNumColumns(currentPuzzle);
                puzzle4[0] =new PuzzleView(getApplicationContext(),4,answer4);
                gridView.setAdapter(puzzle4[0]);
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(currentPuzzle == 3){
                    Collections.shuffle(Arrays.asList(puzzle3[0].pieces3));
                    gridView.setAdapter(puzzle3[0]);
                    for(int i =0;i<9;i++){
                        if(puzzle3[0].pieces3[i].isBlank==1){
                            blank3 = puzzle3[0].pieces3[i];
                            blankIndex3 = i;
                        }
                    }
                }

                if(currentPuzzle == 4){
                    Collections.shuffle(Arrays.asList(puzzle4[0].pieces4));
                    gridView.setAdapter(puzzle4[0]);
                    for(int i =0;i<9;i++){
                        if(puzzle4[0].pieces4[i].isBlank==1){
                            blank4 = puzzle4[0].pieces4[i];
                            blankIndex4 = i;
                        }
                    }

                }
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentPuzzle==3){

                        for(int i =0;i<9;i++){
                            if(puzzle3[0].pieces3[i].isBlank==1){
                                blank3 = puzzle3[0].pieces3[i];
                                blankIndex3 = i;
                            }
                        }
                    if(moveable(currentPuzzle, position, blankIndex3)==1){
                        Piece temp = (Piece)puzzle3[0].getItem(position);
                        puzzle3[0].pieces3[position]=puzzle3[0].pieces3[blankIndex3];
                        puzzle3[0].pieces3[blankIndex3]= temp;

                        puzzle3[0].pieces3[blankIndex3].isBlank=0;
                        puzzle3[0].pieces3[position].isBlank=1;

                        blankIndex3 = position;

                        gridView.setAdapter(puzzle3[0]);

                        if(puzzleInGame.isPuzzleFinished(puzzle3[0].pieces3, 3)==0){
                            Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        return;
                    }

                }


                if(currentPuzzle==4) {
                    for(int i =0;i<16;i++){
                        if(puzzle4[0].pieces4[i].isBlank==1){
                           blank4 = puzzle4[0].pieces4[i];
                            blankIndex4 = i;
                        }
                    }

                    if(moveable(currentPuzzle, position, blankIndex4)==1){
                        Piece temp = (Piece)puzzle4[0].getItem(position);
                        puzzle4[0].pieces4[position]=puzzle4[0].pieces4[blankIndex4];
                        puzzle4[0].pieces4[blankIndex4]= temp;

                        puzzle4[0].pieces4[blankIndex4].isBlank=0;
                        puzzle4[0].pieces4[position].isBlank=1;

                        blankIndex4 = position;

                        gridView.setAdapter(puzzle4[0]);

                        if(puzzleInGame.isPuzzleFinished(puzzle4[0].pieces4, 4)==0){
                            Toast.makeText(getApplicationContext(), "FINISH!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        return;
                    }


                }
            }
        });


    }
    public int moveable(int puzzleNum, int position, int blankIndex){
        int max=0;
        int pos1,pos2,pos3,pos4;

        if(puzzleNum==3){
            pos1=position-1;
            pos2=position+1;
            pos3=position-3;
            pos4=position+3;
            max=8;
            if((position==2&&blankIndex==3)||(position==3&&blankIndex==2)||(position==5&&blankIndex==6)||(position==6&&blankIndex==5)){
                return 0;
            }
        }
        else{
             pos1=position-1;
            pos2=position+1;
            pos3=position-4;
            pos4=position+4;
            max=15;
            if((position==3&&blankIndex==4)||(position==4&&blankIndex==3)||(position==7&&blankIndex==8)||(position==8&&blankIndex==7)||(position==11&&blankIndex==12)||(position==12&&blankIndex==11)){
                return 0;
            }
        }

        if((pos1==blankIndex&&pos1>=0&&pos1<=max) ||(pos2==blankIndex&&pos2>=0&&pos2<=max)||(pos3==blankIndex&&pos1>=0&&pos1<=max) ||(pos4==blankIndex&&pos2>=0&&pos2<=max)){
            return 1;
        }
        else{
            return 0;
        }

    }

}