package com.example.churdmidterm;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 Created by Paul Schimpf on 11/11/2015
 The purpose of this class is as a simple placeholder in a Relative Layout.
 It knows only how to use as much space as its container allocates while enforcing an aspect ratio.
 You can then align the edges of other objects to it.
 ====================
 TO USE THIS ...
 ====================
 1) You'll need an attrs.xml file in your res/values directory that looks like this:
 <declare-styleable name="AspectRatioView">
 <attr name="aspectRatio" format="float" />
 </declare-styleable>

 2) The aspect ratio defaults to 1 (width/height), but you can change it in the XML using the
 attribute called aspectRatio. For example, if you wanted a width that is half the height:

 <com.your.package.path.AspectRatioView
 custom:aspectRatio="0.5f"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 />

 3) In order to use the aspectRatio attribute, you'll need to declare the "custom" namespace
 (which is the part before the colon above). That is declared at the top of the XML file, in
 the header for the outermost layout, like this:

 xmlns:custom="http://schemas.android.com/apk/res-auto"

 **/
public class AspectRatioView extends View
{
    float mAspectRatio ;  // width divided by height

    public AspectRatioView(final Context context) {
        super(context);
    }

    public AspectRatioView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs) ;
    }

    public AspectRatioView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        getAttrs(context, attrs) ;
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AspectRatioView,
                0, 0);
        try {
            mAspectRatio = a.getFloat(R.styleable.AspectRatioView_aspectRatio, 1.0f);
        } finally {
            a.recycle();
        }
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec) ;
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec) ;

        int calcWidth = (int)((float)parentHeight * mAspectRatio) ;
        int calcHeight = (int)((float)parentWidth / mAspectRatio) ;

        int finalWidth, finalHeight ;

        if (calcHeight > parentHeight) {
            finalWidth = calcWidth ;
            finalHeight = parentHeight ;
        } else {
            finalWidth = parentWidth ;
            finalHeight = calcHeight ;
        }

        setMeasuredDimension (finalWidth, finalHeight) ;
    }
}
