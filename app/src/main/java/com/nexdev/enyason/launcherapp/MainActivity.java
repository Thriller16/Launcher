package com.nexdev.enyason.launcherapp;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {


    RelativeLayout relativeLayout;

    CustomFragnebtPagerAdapter pagerAdapter;

    fr.castorflex.android.verticalviewpager.VerticalViewPager viewPager;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();


        pagerAdapter = new CustomFragnebtPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);

//        viewPager.setOffscreenPageLimit(1);

        viewPager.setAdapter(pagerAdapter);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

//        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View view, float position) {
//
//
//                view.setTranslationX(view.getWidth() * -position);
//
//                if(position <= -1.0F || position >= 1.0F) {
//                    view.setAlpha(0.0F);
//                } else if( position == 0.0F ) {
//                    view.setAlpha(1.0F);
//                } else {
//                    // position is between -1.0F & 0.0F OR 0.0F & 1.0F
//                    view.setAlpha(1.0F - Math.abs(position));
//                }
//            }
//        });


//        NotificationManager nm = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.cancel();

    }


    @Override
    public void onBackPressed() {
//        do nothing
//
//        HomeFragment itemFragment = new HomeFragment();
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragmentContainer, itemFragment)
//                .addToBackStack(null)
//                .commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
