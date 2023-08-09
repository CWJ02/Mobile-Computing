package au.edu.jcu.blinkmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class Setting extends AppCompatActivity {
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        /*
          volume switch
         */
        preferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);

        if(preferences.getInt("vol" +
                "",1) == 1) {
            ((TextView)findViewById(R.id.volume))
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_up,0,0,0);
        } else {
            ((TextView)findViewById(R.id.volume))
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_off,0,0,0);
        }

        findViewById(R.id.volume).setOnClickListener(view -> {
            if(preferences.getInt("vol",1) == 1) {
                preferences.edit().putInt("vol",0).apply();
                ((TextView)findViewById(R.id.volume))
                        .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_off,0,0,0);
            } else {
                preferences.edit().putInt("vol",1).apply();
                ((TextView)findViewById(R.id.volume))
                        .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_up,0,0,0);
            }
        });



    }


    /**
     *return previous page
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *exit application
     */
    public void quitgame(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}