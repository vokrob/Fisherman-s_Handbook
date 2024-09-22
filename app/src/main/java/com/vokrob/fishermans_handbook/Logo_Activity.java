package com.vokrob.fishermans_handbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Logo_Activity extends Activity {
    private Animation logoAnim, buttonLogoAnim;
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        startMainActivity();
    }

    private void init() {
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);

        logoImage = findViewById(R.id.logoView);
        logoImage.startAnimation(logoAnim);
    }

    public void onClickStart(View view) {
        Intent i = new Intent(Logo_Activity.this, MainActivity.class);
        startActivity(i);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void startMainActivity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Intent i = new Intent(Logo_Activity.this, MainActivity.class);
                startActivity(i);
            }
        }).start();
    }
}


























