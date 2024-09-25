package com.example.imagegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private ImageView img;
private TextView txt,lv;
private float x,y;
Button btnStart,btnStop;
private boolean flag=true;
private int score=0,level=2000,lvl=1;
Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.textView);
        txt=findViewById(R.id.txt);
        btnStop=findViewById(R.id.stop1);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                lv.setText("Level 1");
                txt.setText("0");
            }
        });
        btnStart=findViewById(R.id.start1);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flag=true;
                level=2500;
                score=0;
                lvl=1;
                lv.setText("Level 1");

                thread=new Thread(()->{
                    try {
                        while (flag){
                            startmoveimg();
                            if(score>9 ){
                                lvl++;
                                level-=500;
                            score=0;}
                            if (level<600){
                                flag=false;
                                lv.setText("you won!!");
                            txt.setText("0");

                                }
                            Thread.sleep(level);
                        }

                    }
                    catch (Exception e){
                    }

                });
                thread.start();

            }
        });

        img=findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level>600){
                score++;
                updatetxt();
                    }


            }
        });

}

private void startmoveimg(){
        x=(float) Math.random()*800;
        y=(float) (Math.random()*1200)+400;
        img.setX(x);
        img.setY(y);
}
private void updatetxt(){
        String x=Integer.toString(score);
        lv.setText("Level"+" "+lvl);
        txt.setText(x);
}
}