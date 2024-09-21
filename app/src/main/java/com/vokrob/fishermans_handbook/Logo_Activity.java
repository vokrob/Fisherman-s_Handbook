package com.vokrob.fishermans_handbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Logo_Activity extends Activity {
    private Animation logoAnim, buttonLogoAnim;
    private Button bAnim;

    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();

    }

    private void init() {
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);

        logoImage = findViewById(R.id.logoView);
        bAnim = findViewById(R.id.buttonAnim);
        logoImage.startAnimation(logoAnim);
        bAnim.startAnimation(buttonLogoAnim);

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
}


























