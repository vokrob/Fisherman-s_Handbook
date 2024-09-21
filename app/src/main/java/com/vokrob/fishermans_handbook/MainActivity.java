package com.vokrob.fishermans_handbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.vokrob.fishermans_handbook.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    private String[] array;
    private ArrayAdapter<String> adapter;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private int category_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.fish_array);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.id_fish, R.id.id_bait, R.id.id_tackle, R.id.id_lure, R.id.id_stories, R.id.id_advice)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
                intent.putExtra("category", category_index);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_fish) {
            fillArray(R.string.fish, R.array.fish_array, 0);
        } else if (id == R.id.id_bait) {
            fillArray(R.string.bait, R.array.bait_array, 1);
        } else if (id == R.id.id_tackle) {
            fillArray(R.string.tackle, R.array.tackle_array, 2);
        } else if (id == R.id.id_lure) {
            fillArray(R.string.lure, R.array.lure_array, 3);
        } else if (id == R.id.id_stories) {
            fillArray(R.string.stories, R.array.stories_array, 4);
        } else if (id == R.id.id_advice) {
            fillArray(R.string.advice, R.array.advice_array, 5);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillArray(int title, int arrayList, int index) {
        toolbar.setTitle(title);
        array = getResources().getStringArray(arrayList);
        adapter.clear();
        adapter.addAll(Arrays.asList(array));
        adapter.notifyDataSetChanged();
        category_index = index;

    }
}























