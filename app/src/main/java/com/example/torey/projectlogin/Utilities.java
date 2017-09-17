package com.example.torey.projectlogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by TOREY on 8/20/2017.
 */

public class Utilities {

    Utilities() {

    }

    public static boolean validateUsername(String UsernameString, String PasswordString, Context context) {
        boolean isEmpty = false;
        if (UsernameString.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Username", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (PasswordString.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Password", Toast.LENGTH_LONG).show();
            return isEmpty;
        }
        return false;
    }

    public static boolean validateSignUp(String insertInputUsername, String insertInputPassword,
                                         String insertInputName, String insertInputEmail, String insertInputImage,
                                         String insertInputTel, Context context) {
        boolean isEmpty = false;
        if (insertInputUsername.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Username", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertInputPassword.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Password", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertInputName.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Name", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertInputEmail.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Email", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertInputImage.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Image", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertInputTel.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Tel", Toast.LENGTH_LONG).show();
            return isEmpty;
        }
        return false;
    }
    public static boolean validateInsertProduct(String insertNameProduct, String insertImageProduct, String insertPriceProduct, String insertDescriptionProduct, String insertHowToUseProduct, Context context) {
        boolean isEmpty = false;
        if (insertNameProduct.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input  Name", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertImageProduct.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input  Image", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertPriceProduct.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input Price", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertDescriptionProduct.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input  Description ", Toast.LENGTH_LONG).show();
            return isEmpty;
        } else if (insertHowToUseProduct.equals("")) {
            isEmpty = true;
            Toast.makeText(context, "Please input How to use", Toast.LENGTH_LONG).show();
            return isEmpty;
        }
        return false;
    }

    public static void setLoadImagesConner(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new CircleTransform(context))
                .placeholder(R.drawable.logo_id)
                .into(imageView);
    }

    public static void setLoadImages(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.logo_id)
                .into(imageView);
    }

    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

}
