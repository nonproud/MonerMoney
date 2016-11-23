package comsitejustdoitwhistle.google.sites.monermoney;

import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    long delays_time;
    long time = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();

        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }


    public void onResume() {
        super.onResume();
        delays_time = time;
        handler.postDelayed(runnable, delays_time);
        time = System.currentTimeMillis();
    }

    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delays_time = (System.currentTimeMillis());
    }

}
