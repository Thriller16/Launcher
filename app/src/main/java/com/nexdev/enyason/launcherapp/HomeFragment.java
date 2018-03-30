package com.nexdev.enyason.launcherapp;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class HomeFragment extends Fragment {

    public Boolean isOn = false;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    DatabaseAccess databaseAccess;

    TextToSpeech textToSpeech;

    Context c;

    AppWidgetManager mAppWidgetManager;
    AppWidgetHost mAppWidgetHost;

    public HomeFragment() {
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.home_layout, parent, false);

    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();

        //My code starts here
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton)view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);



        if(databaseAccess.getmic() == 0){
            fab1.setImageResource(R.drawable.ic_mic_black_24dp);
        }

        else if(databaseAccess.getmic() == 1){
            fab1.setImageResource(R.drawable.ic_mic_off_black_24dp);
        }

        if(databaseAccess.getnight() == 0){
            fab2.setImageResource(R.drawable.ic_visibility_black_24dp);
        }

        else if(databaseAccess.getnight() == 1){
            fab2.setImageResource(R.drawable.ic_visibility_off_black_24dp);
        }


        c = view.getContext();

        textToSpeech = new TextToSpeech(view.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }

            }
        });




        //set up widget
        mAppWidgetManager = AppWidgetManager.getInstance(c);
        mAppWidgetHost = new AppWidgetHost(c, R.id.APPWIDGET_HOST_ID);


        ImageView imageViewTel = view.findViewById(R.id.imageView_dialer);
        ImageView imageViewMessage = view.findViewById(R.id.imageView_message);
        ImageView imageViewCamera = view.findViewById(R.id.imageView_camera);
        ImageView imageViewChrome = view.findViewById(R.id.imageView_chrome);


        Drawable appiconTel = null;
        Drawable appiconMessage = null;
        Drawable appiconCamera = null;
        Drawable appiconBrowser = null;


        //My code starts here
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton)view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.fab2);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
                //trash beginning
                if(databaseAccess.getmic() == 0){
                    databaseAccess.storemic(1);
                    fab1.setImageResource(R.drawable.ic_mic_off_black_24dp);
                    Toast.makeText(getContext(), "Microphone settings are now on", Toast.LENGTH_SHORT).show();
                }

                else if(databaseAccess.getmic() == 1){
                    fab1.setImageResource(R.drawable.ic_mic_black_24dp);
                    databaseAccess.storemic(0);
                    Toast.makeText(getContext(), "Microphone settings are now off", Toast.LENGTH_SHORT).show();
                }
                //trash ending

            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();

                if(databaseAccess.getnight() == 0){
                    databaseAccess.storenight(1);
                    fab2.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    Toast.makeText(getContext(), "Night Mode is now on", Toast.LENGTH_SHORT).show();
                }

                else if(databaseAccess.getnight() == 1){
                    fab2.setImageResource(R.drawable.ic_visibility_black_24dp);
                    databaseAccess.storenight(0);
                    Toast.makeText(getContext(), "Night Mode is now off", Toast.LENGTH_SHORT).show();
                }
            }
        });


        try {
            appiconTel = getActivity().getPackageManager().getApplicationIcon("com.android.dialer");
            appiconMessage = getActivity().getPackageManager().getApplicationIcon("com.android.mms");
            appiconCamera = getActivity().getPackageManager().getApplicationIcon("com.mediatek.camera");
            appiconBrowser = getActivity().getPackageManager().getApplicationIcon("com.android.chrome");


            imageViewTel.setImageDrawable(appiconTel);
            imageViewMessage.setImageDrawable(appiconMessage);
            imageViewCamera.setImageDrawable(appiconCamera);
            imageViewChrome.setImageDrawable(appiconBrowser);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        imageViewTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeButtons("com.android.dialer");
                if(databaseAccess.getmic() == 1){
                    speakNow("Dialer");
                }

                int liiii = databaseAccess.getmic();
                Log.i("TAG", "" + liiii);
            }
        });

        imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeButtons("com.android.mms");
                if(databaseAccess.getmic() == 1){
                    speakNow("Messages");
                }
            }
        });


        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeButtons("com.mediatek.camera");
                if(databaseAccess.getmic() == 1){
                    speakNow("Camera");
                }
            }
        });


        imageViewChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHomeButtons("com.android.chrome");
                if(databaseAccess.getmic() == 1){
                    speakNow("Chrome");
                }
            }
        });

    }


    public void openHomeButtons(String packageName) {

        Intent launchIntent = getContext().getPackageManager().getLaunchIntentForPackage(packageName);
        c.startActivity(launchIntent);
//        Toast.makeText(getContext(), packageName, Toast.LENGTH_LONG).show();
    }


    public void speakNow(String toSpeak) {
        Toast.makeText(getContext().getApplicationContext(), toSpeak, Toast.LENGTH_LONG).show();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }



}




