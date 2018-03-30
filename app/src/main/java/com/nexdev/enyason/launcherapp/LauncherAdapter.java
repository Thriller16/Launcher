package com.nexdev.enyason.launcherapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Enyason on 3/5/2018.
 */

public class LauncherAdapter extends RecyclerView.Adapter<LauncherAdapter.ViewHolder> {

    private static Context context;
    private static List<AppInfo> appsList;

    RecyclerView.LayoutManager layoutManager;
    DatabaseAccess databaseAccess;

    public LauncherAdapter(Context c, RecyclerView.LayoutManager layoutManager) {

        context = c;
        this.layoutManager  = layoutManager;
        setUpApps();

        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon



    }

    public static void setUpApps(){

        PackageManager pm = context.getPackageManager();
        appsList = new ArrayList<AppInfo>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(i, 0);
        for (ResolveInfo ri : allApps) {
            AppInfo app = new AppInfo();
            app.label = ri.loadLabel(pm);
            app.packageName = ri.activityInfo.packageName;

            Log.i(" Log package ",app.packageName.toString());
            app.icon = ri.activityInfo.loadIcon(pm);
            appsList.add(app);

        }

        Collections.sort(appsList, new Comparator<AppInfo>() {
            @Override
            public int compare(AppInfo appInfo, AppInfo temp) {
                String id1 = appInfo.label.toString().toLowerCase();
                String id2 = temp.label.toString().toLowerCase();

                // ascending order
                return id1.compareTo(id2);

                // descending order
                //return id2.compareTo(id1);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //This is what adds the code we've written in here to our target view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;

        if(layoutManager instanceof GridLayoutManager) {
            view = inflater.inflate(R.layout.item_row_view_grid, parent, false);


        } else if(layoutManager instanceof LinearLayoutManager) {
            view = inflater.inflate(R.layout.item_row_view_list, parent, false);



        }


        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String appLabel = appsList.get(position).label.toString();
        String appPackage = appsList.get(position).packageName.toString();
        Drawable appIcon = appsList.get(position).icon;

        TextView textView = holder.textView;
        textView.setText(appLabel);
        ImageView imageView = holder.img;
        imageView.setImageDrawable(appIcon);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextToSpeech textToSpeech;
        public TextView textView;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            //Finds the views from our row.xml
            textView = (TextView) itemView.findViewById(R.id.tv_app_name);
            img = (ImageView) itemView.findViewById(R.id.app_icon);
            itemView.setOnClickListener(this);


            textToSpeech = new TextToSpeech(itemView.getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        textToSpeech.setLanguage(Locale.UK);
                    }

                }
            });


        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();
            Context context = view.getContext();

            databaseAccess = DatabaseAccess.getInstance(context);
            databaseAccess.open();


            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(pos).packageName.toString());
            context.startActivity(launchIntent);
//            Toast.makeText(context, appsList.get(pos).label.toString(), Toast.LENGTH_SHORT).show();


            String toSpeak = appsList.get(pos).label.toString();
//            Toast.makeText(context.getApplicationContext(), toSpeak,Toast.LENGTH_LONG).show();

            if(databaseAccess.getmic() == 1){
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}


