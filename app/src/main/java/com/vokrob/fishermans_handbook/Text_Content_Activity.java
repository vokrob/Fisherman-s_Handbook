package com.vokrob.fishermans_handbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {
    private ActionBar actionBar;
    private TextView text_content;
    private Typeface face1;
    private ImageView iContent;
    private SharedPreferences def_pref;

    private int category = 0;
    private int position = 0;

    private int[] array_fish = {R.string.fish_1, R.string.fish_2, R.string.fish_3, R.string.fish_4, R.string.fish_5};
    private int[] array_bait = {R.string.bait_1, R.string.bait_2, R.string.bait_3, R.string.bait_4};
    private int[] array_tackle = {R.string.tackle_1, R.string.tackle_2, R.string.tackle_3, R.string.tackle_4};
    private int[] array_lure = {R.string.lure_1, R.string.lure_2, R.string.lure_3};
    private int[] array_stories = {R.string.stories_1, R.string.stories_2, R.string.stories_3, R.string.stories_4};
    private int[] array_advice = {R.string.advice_1, R.string.advice_2, R.string.advice_3, R.string.advice_4};

    private int[] array_image_fish = {R.drawable.carp, R.drawable.pike, R.drawable.catfish, R.drawable.sturgeon, R.drawable.burbot};
    private int[] array_image_bait = {R.drawable.worm, R.drawable.corn, R.drawable.bread, R.drawable.rice};
    private int[] array_image_tackle = {R.drawable.sinkers, R.drawable.hooks, R.drawable.fishing_line, R.drawable.fishing_lure};
    private int[] array_image_lure = {R.drawable.corn, R.drawable.bread, R.drawable.rice};
    private int[] array_image_stories = {R.drawable.stories, R.drawable.stories, R.drawable.stories, R.drawable.stories};
    private int[] array_image_advice = {R.drawable.advice, R.drawable.advice, R.drawable.advice, R.drawable.advice};

    private String[] array_title_fish = {"Карп", "Щука", "Сом", "Осётр", "Налим"};
    private String[] array_title_bait = {"Червяк", "Кукуруза", "Хлеб", "Рис"};
    private String[] array_title_tackle = {"Грузила", "Крючки", "Леска", "Блесма"};
    private String[] array_title_lure = {"Кукуруза", "Хлеб", "Рис"};
    private String[] array_title_stories = {"Рыбак и море", "Рыбак и щука", "На дому", "Случай на рыбалке"};
    private String[] array_title_advice = {"Советы по прикормке", "Сезон рыбалки", "На что клюёт", "Большой улов"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_700));
        setContentView(R.layout.text_content);
        init();
        receiveIntent();
    }

    private void receiveIntent() {
        Intent i = getIntent();
        if (i != null) {
            category = i.getIntExtra("category", 0);
            position = i.getIntExtra("position", 0);
        }
        switch (category) {
            case 0:
                iContent.setImageResource(array_image_fish[position]);
                text_content.setText(array_fish[position]);
                actionBar.setTitle(array_title_fish[position]);
                break;
            case 1:
                iContent.setImageResource(array_image_bait[position]);
                text_content.setText(array_bait[position]);
                actionBar.setTitle(array_title_bait[position]);
                break;
            case 2:
                iContent.setImageResource(array_image_tackle[position]);
                text_content.setText(array_tackle[position]);
                actionBar.setTitle(array_title_tackle[position]);
                break;
            case 3:
                iContent.setImageResource(array_image_lure[position]);
                text_content.setText(array_lure[position]);
                actionBar.setTitle(array_title_lure[position]);
                break;
            case 4:
                iContent.setImageResource(array_image_stories[position]);
                text_content.setText(array_stories[position]);
                actionBar.setTitle(array_title_stories[position]);
                break;
            case 5:
                iContent.setImageResource(array_image_advice[position]);
                text_content.setText(array_advice[position]);
                actionBar.setTitle(array_title_advice[position]);
                break;
        }
    }

    private void init() {
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.text_main_content);
        iContent = findViewById(R.id.imageContent);
        face1 = Typeface.createFromAsset(this.getAssets(), "fonts/Lobster-Regular.ttf");
        text_content.setTypeface(face1);
        actionBar = getSupportActionBar();
        String text = def_pref.getString("main_text_size", "Средний");
        if (text != null) {
            switch (text) {
                case "Большой":
                    text_content.setTextSize(24);
                    break;
                case "Средний":
                    text_content.setTextSize(18);
                    break;
                case "Маленький":
                    text_content.setTextSize(14);
                    break;
            }
        }
    }
}





















