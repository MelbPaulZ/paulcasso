package com.developer.paul.paulcasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Paul on 27/5/17.
 */

public class BlurredBitmap {

    private static int DEFAULT_WIDTH = 700;
    private static int DEFAULT_HEIGHT = 200;
    private static int DEFAULT_RADIUS = 100;
    private static int DEFAULT_START_X = 900;
    private static int DEFAULT_START_Y = 400;

    private static int MODEL_CENTER_CROP = 1000;

    private Bitmap mBitmap;
    private static BlurredBitmap mBlurredBitmap;
    private int mWidth = DEFAULT_WIDTH, mHeight = DEFAULT_HEIGHT;

    private int mRadius = DEFAULT_RADIUS;
    private int mStartX = DEFAULT_START_X , mStartY = DEFAULT_START_Y;
    private Context mContext;

    private int mModel;


    private BlurredBitmap(Context context){
        this.mContext = context;
    }

    public static BlurredBitmap getInstance(Context context){
        if (mBlurredBitmap==null) {
            mBlurredBitmap = new BlurredBitmap(context);
        }
        return mBlurredBitmap;
    }

    public BlurredBitmap loadAsset(String file){
        InputStream stream = null;
        try {
            stream = mContext.getAssets().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mBitmap = BitmapFactory.decodeStream(stream);
        return mBlurredBitmap;
    }

    public BlurredBitmap loadDrawable(Drawable drawable){
        mBitmap = ((BitmapDrawable)drawable).getBitmap();
        return mBlurredBitmap;
    }


    public BlurredBitmap size(int width, int height){
        mWidth = width;
        mHeight = height;
        return mBlurredBitmap;
    }

    public BlurredBitmap start(int startX, int startY){
        mStartX = startX;
        mStartY = startY;
        return mBlurredBitmap;
    }

    public BlurredBitmap radius(int radius){
        mRadius = radius;
        return mBlurredBitmap;
    }

    public BlurredBitmap centerCrop(){
        mModel = MODEL_CENTER_CROP;
        return mBlurredBitmap;
    }

    public Bitmap buildBlurredBitmap(){
        try {
            bitmapNullError(mBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (mModel ==MODEL_CENTER_CROP){
            int bitmapWidth = mBitmap.getWidth();
            int bitmapHeight = mBitmap.getHeight();
            mStartX = bitmapWidth/2 - mWidth/2;
            mStartY = bitmapHeight/2 - mHeight/2;
//            Log.i("aaaa", "buildBlurredBitmap: startX : " + mStartX  + " startY : " + mStartY
//                    + " height : " + mHeight + " width : " + mWidth + " total width : " + bitmapWidth
//                    + " total height : " + bitmapHeight);
        }

        Bitmap resizedBitmap=Bitmap.createBitmap(mBitmap, mStartX, mStartY, mWidth, mHeight);
        Bitmap blurredBitmap = FastBlur.Blur(resizedBitmap, mRadius);
        return blurredBitmap;
    }

    public Drawable buildBlurredDrawable(){
        Bitmap bitmap = buildBlurredBitmap();
        Drawable drawable = new BitmapDrawable(mContext.getResources(), bitmap);
        return drawable;
    }

    private void bitmapNullError(Bitmap bitmap) throws Exception {
        if (bitmap==null){
            throw new Exception("image file cannot be null");
        }
    }



}