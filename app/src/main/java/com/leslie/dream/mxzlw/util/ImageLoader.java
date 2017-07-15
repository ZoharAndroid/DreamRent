package com.leslie.dream.mxzlw.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

/**
 * @Author dzl on 2017/7/5.
 */
public class ImageLoader {

    public static void loadImage(ImageView imageView, String url, int... res) {
        /*Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(res)
                .into(imageView);*/
        DrawableRequestBuilder<String> builder = Glide.with(imageView.getContext())

                .load(url)
                //.centerCrop()
                .crossFade();
               // .placeholder(R.drawable.empty_photo)
               // .error(R.drawable.empty_photo);
        if (res.length > 0) {
            builder.placeholder(res[0]);
        }
        if (res.length>1){
            builder.error(res[1]);
        }
        builder.into(imageView);
    }

    public static void loadImage(ImageView imageView, int... resId) {
        if (resId.length == 0) {
            return;
        }
        DrawableRequestBuilder<Integer> builder = Glide.with(imageView.getContext())
                .load(resId[0])
                .centerCrop()
                .crossFade();
        if (resId.length > 1) {
            builder.placeholder(resId[1]);
        }
        builder.into(imageView);

    }
    /**
     * 根据图片路径 转bitmap
    public static void imgToBitmap(Context context, String url, ShareContent share_content, int... res) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        //判断是否在子线程。 子线程：继续执行  主线程：抛出异常
        ThreadUtil.assertBackgroundThread();
        try {
            Bitmap bitmap = Glide.with(context)
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(300, 300)
                    .get();
            share_content.setShare_image_bitmap(bitmap);//分享的内容
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 根据图片路径 转bitmap
     */
    public static Drawable imgToDrawable(Context context, String url, int... res) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        //判断是否在子线程。 子线程：继续执行  主线程：抛出异常
        ThreadUtil.assertBackgroundThread();

        Drawable drawable =null;
        try {
            Bitmap bitmap = Glide.with(context)
                    .load(url)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(300, 300)
                    .get();
           drawable =   bitmapToDrawble(bitmap,context);
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    /**
     * Bitmap to Drawable
     * @param bitmap
     * @param mcontext
     * @return
     */
    public static Drawable bitmapToDrawble(Bitmap bitmap, Context mcontext){
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);
        return drawable;
    }
    
}
