package au.edu.jcu.blinkmemory;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class SplashActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar loading = findViewById(R.id.loading);
        TextView mvw = findViewById(R.id.mvw);
        mvw.setAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
        myDb = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.name_input);
        Button btnViewData = (Button) findViewById(R.id.submit_button);

        getSupportActionBar().hide();//        hide actionbar


        /*
          progressbar duration and animation
         */
        new CountDownTimer(2500, 500) {
            @Override
            public void onTick(long l) {
                if (loading.getVisibility() == View.VISIBLE) {
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.VISIBLE);
                findViewById(R.id.first).setVisibility(View.VISIBLE);
                findViewById(R.id.second).setVisibility(View.VISIBLE);
            }
        }.start();
        /*
          get user input name and check
          if input is valid, run addData
          else, came out a toast "Please enter a name"
         */
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                if (!name.isEmpty()) {
                    SplashActivity.this.addData(name);
                } else {
                    Toast.makeText(SplashActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * insert data into database
     * if inserted successful, print toast "Data inserted"
     * else print toast "Data not inserted"
     */
    public void addData(String name) {
        boolean insertData = myDb.addData(name);
        if (insertData) {
            Toast.makeText(SplashActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(SplashActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();



        }

    }
}

