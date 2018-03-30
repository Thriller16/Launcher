package com.nexdev.enyason.launcherapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AppsDrawerFragment extends Fragment {


    Toolbar toolbar;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_apps_list, parent, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    ImageView imageViewGrid, imageViewList;

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        toolbar = view.findViewById(R.id.tool_bar_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        imageViewGrid = view.findViewById(R.id.imageGrid);
        imageViewList = view.findViewById(R.id.imageList);
        recyclerView = view.findViewById(R.id.Launcher_recyclerview);
        layoutManager= new GridLayoutManager(view.getContext(),4);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter  = new LauncherAdapter(view.getContext(),layoutManager);

        recyclerView.setAdapter(adapter);


        imageViewGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager= new GridLayoutManager(getContext(),4);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new LauncherAdapter(getContext(),layoutManager));

            }
        });


        imageViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layoutManager= new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(new LauncherAdapter(getContext(),layoutManager));

            }
        });



    }


}
