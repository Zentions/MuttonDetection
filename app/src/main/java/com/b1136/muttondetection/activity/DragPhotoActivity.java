package com.b1136.muttondetection.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.b1136.muttondetection.R;
import com.b1136.muttondetection.tool.PictureTool;
import com.wingsofts.dragphotoview.DragPhotoView;

import java.util.ArrayList;
import java.util.List;

public class DragPhotoActivity extends AppCompatActivity {
    private String path;
    private DragPhotoView mPhotoView;

    int mOriginLeft;
    int mOriginTop;
    int mOriginHeight;
    int mOriginWidth;
    int mOriginCenterX;
    int mOriginCenterY;
    private float mTargetHeight;
    private float mTargetWidth;
    private float mScaleX;
    private float mScaleY;
    private float mTranslationX;
    private float mTranslationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_drag_photo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        mPhotoView = (DragPhotoView) findViewById(R.id.photoview);

        path = getIntent().getStringExtra("PicPath");
        Log.e("333",path);
        mPhotoView.setImageBitmap(PictureTool.loadBitmap(path,true));
       // setPara();
        mPhotoView.setOnTapListener(new DragPhotoView.OnTapListener() {
            @Override
            public void onTap(DragPhotoView view) {
                //finishWithAnimation();
            }
        });

        mPhotoView.setOnExitListener(new DragPhotoView.OnExitListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onExit(DragPhotoView view, float x, float y, float w, float h) {
               // performExitAnimation(view, x, y, w, h);
            }
        });

    }
//    private void setPara(){
//        mOriginLeft = getIntent().getIntExtra("left", 0);
//        mOriginTop = getIntent().getIntExtra("top", 0);
//        mOriginHeight = getIntent().getIntExtra("height", 0);
//        mOriginWidth = getIntent().getIntExtra("width", 0);
//        mOriginCenterX = mOriginLeft + mOriginWidth / 2;
//        mOriginCenterY = mOriginTop + mOriginHeight / 2;
//
//        int[] location = new int[2];
//
//        mPhotoView.getLocationOnScreen(location);
//
//        mTargetHeight = (float) mPhotoView.getHeight();
//        mTargetWidth = (float) mPhotoView.getWidth();
//        mScaleX = (float) mOriginWidth / mTargetWidth;
//        mScaleY = (float) mOriginHeight / mTargetHeight;
//
//        float targetCenterX = location[0] + mTargetWidth / 2;
//        float targetCenterY = location[1] + mTargetHeight / 2;
//
//        mTranslationX = mOriginCenterX - targetCenterX;
//        mTranslationY = mOriginCenterY - targetCenterY;
//        mPhotoView.setTranslationX(mTranslationX);
//        mPhotoView.setTranslationY(mTranslationY);
//
//        mPhotoView.setScaleX(mScaleX);
//        mPhotoView.setScaleY(mScaleY);
//
//        performEnterAnimation();
//        mPhotoView.setMinScale(mScaleX);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//    private void performExitAnimation(final DragPhotoView view, float x, float y, float w, float h) {
//        view.finishAnimationCallBack();
//        float viewX = mTargetWidth / 2 + x - mTargetWidth * mScaleX / 2;
//        float viewY = mTargetHeight / 2 + y - mTargetHeight * mScaleY / 2;
//        view.setX(viewX);
//        view.setY(viewY);
//
//        float centerX = view.getX() + mOriginWidth / 2;
//        float centerY = view.getY() + mOriginHeight / 2;
//
//        float translateX = mOriginCenterX - centerX;
//        float translateY = mOriginCenterY - centerY;
//
//
//        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(view.getX(), view.getX() + translateX);
//        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                view.setX((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateXAnimator.setDuration(300);
//        translateXAnimator.start();
//        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(view.getY(), view.getY() + translateY);
//        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                view.setY((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateYAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                animator.removeAllListeners();
//                finish();
//                overridePendingTransition(0, 0);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        translateYAnimator.setDuration(300);
//        translateYAnimator.start();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//    private void finishWithAnimation() {
//
//        final DragPhotoView photoView = mPhotoView;
//        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(0, mTranslationX);
//        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setX((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateXAnimator.setDuration(300);
//        translateXAnimator.start();
//
//        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(0, mTranslationY);
//        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setY((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateYAnimator.setDuration(300);
//        translateYAnimator.start();
//
//        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(1, mScaleY);
//        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        scaleYAnimator.setDuration(300);
//        scaleYAnimator.start();
//
//        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(1, mScaleX);
//        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//
//        scaleXAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//                animator.removeAllListeners();
//                finish();
//                overridePendingTransition(0, 0);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        scaleXAnimator.setDuration(300);
//        scaleXAnimator.start();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//    private void performEnterAnimation() {
//        final DragPhotoView photoView = mPhotoView;
//        ValueAnimator translateXAnimator = ValueAnimator.ofFloat(photoView.getX(), 0);
//        translateXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setX((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateXAnimator.setDuration(300);
//        translateXAnimator.start();
//
//        ValueAnimator translateYAnimator = ValueAnimator.ofFloat(photoView.getY(), 0);
//        translateYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setY((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        translateYAnimator.setDuration(300);
//        translateYAnimator.start();
//
//        ValueAnimator scaleYAnimator = ValueAnimator.ofFloat(mScaleY, 1);
//        scaleYAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setScaleY((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        scaleYAnimator.setDuration(300);
//        scaleYAnimator.start();
//
//        ValueAnimator scaleXAnimator = ValueAnimator.ofFloat(mScaleX, 1);
//        scaleXAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                photoView.setScaleX((Float) valueAnimator.getAnimatedValue());
//            }
//        });
//        scaleXAnimator.setDuration(300);
//        scaleXAnimator.start();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//    @Override
//    public void onBackPressed() {
//        finishWithAnimation();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
}
