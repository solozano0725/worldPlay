package app.worldplay.rappi.common;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import app.worldplay.rappi.R;
import app.worldplay.rappi.view.activity.MainActivity;

public class SplashScreenAnimated extends AppCompatActivity implements Runnable {

    private static final long SPLASH_SCREEN_DELAY = 3000;

    ImageView img;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen_animated);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        img = findViewById(R.id.splashId);

        final Animation animation_1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animation_1.reset();
        img.clearAnimation();
        img.startAnimation(animation_1);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreenAnimated.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenAnimated.this.finish();
                } catch (InterruptedException e) {

                } finally {
                    SplashScreenAnimated.this.finish();
                }

            }
        };
        thread.start();

    }

    @Override
    public void run() {

    }
}

