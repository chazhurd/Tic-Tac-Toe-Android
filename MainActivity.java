package com.example.churdmidterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
    int orientation = 0;
    @Override
    public void onSaveInstanceState(Bundle state) {


        ImageView topleft = findViewById(R.id.topleft);
        ImageView topcenter = findViewById(R.id.topcenter);
        ImageView topright = findViewById(R.id.topright);
        ImageView middleleft = findViewById(R.id.middleleft);
        ImageView middlecenter = findViewById(R.id.middlecenter);
        ImageView middleright = findViewById(R.id.middleright);
        ImageView bottomleft = findViewById(R.id.bottomleft);
        ImageView bottomcenter = findViewById(R.id.bottomcenter);
        ImageView bottomright = findViewById(R.id.bottomright);

        int tl = (int)topleft.getTag();
        int tc = (int)topcenter.getTag();
        int tr = (int)topright.getTag();
        int ml = (int)middleleft.getTag();
        int mc = (int)middlecenter.getTag();
        int mr = (int)middleright.getTag();
        int bl = (int)bottomleft.getTag();
        int bc = (int)bottomcenter.getTag();
        int br = (int)bottomright.getTag();
        //store these tags and update respectively in on create.
        int[] imagesIds = {tl,tc,tr,ml,mc,mr,bl,bc,br};
        state.putIntArray("imageIds", imagesIds);
        super.onSaveInstanceState(state);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        orientation = getResources().getConfiguration().orientation;

        ImageView myX = findViewById(R.id.imageX);
        ImageView myO = findViewById(R.id.imageO);

        myX.setOnTouchListener(this);
        myX.setTag(R.drawable.x);
        myO.setOnTouchListener(this);
        myO.setTag(R.drawable.o);

        ImageView tl = findViewById(R.id.topleft);
        ImageView tc = findViewById(R.id.topcenter);
        ImageView tr = findViewById(R.id.topright);
        ImageView ml = findViewById(R.id.middleleft);
        ImageView mc = findViewById(R.id.middlecenter);
        ImageView mr = findViewById(R.id.middleright);
        ImageView bl = findViewById(R.id.bottomleft);
        ImageView bc = findViewById(R.id.bottomcenter);
        ImageView br = findViewById(R.id.bottomright);

        tl.setOnDragListener(this);
        tc.setOnDragListener(this);
        tr.setOnDragListener(this);
        ml.setOnDragListener(this);
        mc.setOnDragListener(this);
        mr.setOnDragListener(this);
        bl.setOnDragListener(this);
        bc.setOnDragListener(this);
        br.setOnDragListener(this);

        if(savedInstanceState!=null){
            int[] myIds = savedInstanceState.getIntArray("imageIds");
            tl.setImageResource(myIds[0]);
            tc.setImageResource(myIds[1]);
            tr.setImageResource(myIds[2]);
            ml.setImageResource(myIds[3]);
            mc.setImageResource(myIds[4]);
            mr.setImageResource(myIds[5]);
            bl.setImageResource(myIds[6]);
            bc.setImageResource(myIds[7]);
            br.setImageResource(myIds[8]);

            tl.setTag(myIds[0]);
            tc.setTag(myIds[1]);
            tr.setTag(myIds[2]);
            ml.setTag(myIds[3]);
            mc.setTag(myIds[4]);
            mr.setTag(myIds[5]);
            bl.setTag(myIds[6]);
            bc.setTag(myIds[7]);
            br.setTag(myIds[8]);

        }else {
            //set tags
            tl.setTag(R.drawable.blank);
            tc.setTag(R.drawable.blank);
            tr.setTag(R.drawable.blank);
            ml.setTag(R.drawable.blank);
            mc.setTag(R.drawable.blank);
            mr.setTag(R.drawable.blank);
            bl.setTag(R.drawable.blank);
            bc.setTag(R.drawable.blank);
            br.setTag(R.drawable.blank);

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This adds items to the action bar if it is present.
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getMenuInflater().inflate(R.menu.land, menu);
        } else {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        return true;
    }
    public void onAbout(MenuItem item) {
        Toast.makeText(this, "Midterm, Spring 2020, Chaz C Hurd", Toast.LENGTH_SHORT).show();
    }
    // end template
    public void onAddMargin(MenuItem item){
        LinearLayout layout = findViewById(R.id.game_piece_container);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, 400));
    }
    public void onReduceMargin(MenuItem item){
        LinearLayout layout = findViewById(R.id.game_piece_container) ;
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_DOWN){
            View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null,dragShadowBuilder,v,0);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if(event.getAction() == DragEvent.ACTION_DROP) {
           View obj =  (View)event.getLocalState();
           //get drawable id of the imageview being dragged
           int draggedID = (int) obj.getTag(); //X OR O
            //set drawableID on Imageview being dropped on
            ImageView imageDroppedOn = (ImageView)v;
            if(imageDroppedOn.getTag().equals(R.drawable.blank)) {
                imageDroppedOn.setImageResource(draggedID);
                if(draggedID == R.drawable.o)
                {
                imageDroppedOn.setTag(R.drawable.o);
                }else{
                    imageDroppedOn.setTag(R.drawable.x);
                }
            }else{
                Toast.makeText(this, "You Must Place On A Blank/Grey Cell", Toast.LENGTH_SHORT).show();
            }
           return true;
        }

        return true;
    }
}
