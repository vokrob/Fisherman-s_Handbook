package com.vokrob.fishermans_handbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import com.vokrob.fishermans_handbook.settings.SettingsActivity;
import com.vokrob.fishermans_handbook.utils.CustomArrayAdapter;
import com.vokrob.fishermans_handbook.utils.ListItemClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView list;
    private String[] array, arraySecName;
    private CustomArrayAdapter adapter;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private int category_index;
    private int[] array_fish_image = new int[]{R.drawable.carp, R.drawable.pike, R.drawable.catfish, R.drawable.sturgeon, R.drawable.burbot};
    private int[] array_bait_image = new int[]{R.drawable.worm, R.drawable.corn, R.drawable.bread, R.drawable.rice};
    private int[] array_tackle_image = new int[]{R.drawable.sinkers, R.drawable.hooks, R.drawable.fishing_line, R.drawable.fishing_lure};
    private int[] array_lure_image = new int[]{R.drawable.corn, R.drawable.bread, R.drawable.rice};
    private int[] array_stories_image = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four};
    private int[] array_advice_image = new int[]{R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four};
    private List<ListItemClass> listItemMain;
    private ListItemClass listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_700));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.listView);
        listItemMain = new ArrayList<>();

        fillArray(R.string.fish, getResources().getStringArray(R.array.fish_array), getResources().getStringArray(R.array.fish_array_2), array_fish_image, 0);

        adapter = new CustomArrayAdapter(this, R.layout.list_view_item_1, listItemMain, getLayoutInflater());
        list.setAdapter(adapter);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettingsActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsActivity() {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(i);
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
            fillArray(R.string.fish, getResources().getStringArray(R.array.fish_array), getResources().getStringArray(R.array.fish_array_2), array_fish_image, 0);
        } else if (id == R.id.id_bait) {
            fillArray(R.string.bait, getResources().getStringArray(R.array.bait_array), getResources().getStringArray(R.array.bait_array_2), array_bait_image, 1);
        } else if (id == R.id.id_tackle) {
            fillArray(R.string.tackle, getResources().getStringArray(R.array.tackle_array), getResources().getStringArray(R.array.tackle_array_2), array_tackle_image, 2);
        } else if (id == R.id.id_lure) {
            fillArray(R.string.lure, getResources().getStringArray(R.array.lure_array), getResources().getStringArray(R.array.lure_array_2), array_lure_image, 3);
        } else if (id == R.id.id_stories) {
            fillArray(R.string.stories, getResources().getStringArray(R.array.stories_array), getResources().getStringArray(R.array.stories_array_2), array_stories_image, 4);
        } else if (id == R.id.id_advice) {
            fillArray(R.string.advice, getResources().getStringArray(R.array.advice_array), getResources().getStringArray(R.array.advice_array_2), array_advice_image, 5);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillArray(int title, String[] nameArray, String[] secName, int[] image, int index) {
        toolbar.setTitle(title);
        if (adapter != null) adapter.clear();

        for (int i = 0; i < nameArray.length; i++) {
            listItem = new ListItemClass();
            listItem.setNameF(nameArray[i]);
            listItem.setSecond_name(secName[i]);
            listItem.setImage_id(image[i]);

            listItemMain.add(listItem);
        }
        if (adapter != null) adapter.notifyDataSetChanged();
        category_index = index;

    }
}























