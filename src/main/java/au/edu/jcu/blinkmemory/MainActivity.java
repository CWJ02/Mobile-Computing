package au.edu.jcu.blinkmemory;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    private Dialog dialog;
    private int lvl = 1,score = 0;
    private String answer,number;
    private Toast smile,sad;
    private CountDownTimer countDownTimer;
    private SharedPreferences preferences;
    private TextView level,point,time,t1,t2;
    private MediaPlayer correct,wrong,success;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    private Sensor accelerometerSensor;
    private SensorEventListener accelerometerSensorListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
//        hide actionbar
        level = findViewById(R.id.level);
        point = findViewById(R.id.point);
        time = findViewById(R.id.time);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        sad = makeText(this,"\uD83D\uDE14",LENGTH_SHORT);
        smile = makeText(this,"\uD83D\uDE04",LENGTH_SHORT);
        preferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
        correct = MediaPlayer.create(getApplicationContext(),R.raw.correct);
        wrong = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
        success = MediaPlayer.create(getApplicationContext(),R.raw.success);
        dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.game_over);dialog.setCancelable(false);
        TextView mvw = dialog.findViewById(R.id.mvw);
        mvw.setAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate));/* animation of play button */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        /* A proximity sensor to detect the distance between the device and user's face
        * Use toast to pop up a reminder when the user face is too close to the device
        */
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                    Toast.makeText(MainActivity.this, "Device is too close to face", Toast.LENGTH_SHORT).show();
                    // Proximity sensor is close
                    // Device is close to face
                } else {
                    Toast.makeText(MainActivity.this, "Device is too far to face", Toast.LENGTH_SHORT).show();
                    // Proximity sensor is far
                    // Device is not close to face
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        /* Add an accelerometerSensor to determine if the device is facing down on a table
        When the device is facing down, the app will call finish() to close this app
         */
        accelerometerSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                if (x < -9 && y < -9) {
                    // Device is facing down
                    finish();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        dialog.findViewById(R.id.score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), ListDataActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        /*
         button to share.java
         */
        dialog.findViewById(R.id.shrButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), Share.class);
                MainActivity.this.startActivity(intent);
            }
        });

        /*
         start the game, default volume
         */
        dialog.findViewById(R.id.ply).setOnClickListener(view -> {
            lvl = 1;score = 0;dialog.dismiss();setupNewGame();
        });
        if(preferences.getInt("vol",2) == 1) {
            ((TextView)findViewById(R.id.volume))
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_up,0,0,0);
        } else {
            ((TextView)findViewById(R.id.volume))
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.volume_off,0,0,0);
        }

        findViewById(R.id.one).setOnClickListener(view -> checkAnswer("1"));
        findViewById(R.id.two).setOnClickListener(view -> checkAnswer("2"));
        findViewById(R.id.three).setOnClickListener(view -> checkAnswer("3"));
        findViewById(R.id.four).setOnClickListener(view -> checkAnswer("4"));
        findViewById(R.id.five).setOnClickListener(view -> checkAnswer("5"));
        findViewById(R.id.six).setOnClickListener(view -> checkAnswer("6"));
        findViewById(R.id.seven).setOnClickListener(view -> checkAnswer("7"));
        findViewById(R.id.eight).setOnClickListener(view -> checkAnswer("8"));
        findViewById(R.id.nine).setOnClickListener(view -> checkAnswer("9"));
        setupNewGame();
    }




    private void setupNewGame() {
        if(lvl == 1) {
            playSound(success);
        }
        level.setText("LEVEL " + lvl);
        point.setText("" + score);
        time.setText("0");answer = "";
        t1.setText("");t2.setText("");
        int blinkNum;
        if(lvl < 5) {
            blinkNum = new Random().nextInt(10) + 1;
        } else if(lvl < 10) {
            blinkNum = new Random().nextInt(90) + 10;
        } else if(lvl < 15) {
            blinkNum = new Random().nextInt(899) + 100;
        } else if(lvl < 20) {
            blinkNum = new Random().nextInt(8999) + 1000;
        } else if(lvl < 25) {
            blinkNum = new Random().nextInt(49999) + 10000;
        } else if(lvl < 30) {
            blinkNum = new Random().nextInt(89999) + 10000;
        } else if(lvl < 35) {
            blinkNum = new Random().nextInt(499999) + 100000;
        } else if(lvl < 40) {
            blinkNum = new Random().nextInt(899999) + 100000;
        } else if(lvl < 50) {
            blinkNum = new Random().nextInt(8999999) + 1000000;
        } else if(lvl < 60) {
            blinkNum = new Random().nextInt(89999999) + 10000000;
        } else if(lvl < 70) {
            blinkNum = new Random().nextInt(89999999) + 10000000;
        } else {
            blinkNum = new Random().nextInt(899999999) + 100000000;
        }
        number = String.valueOf(blinkNum).replace("0","1");
        /*
         place only 9 numbers button for better user experience, so if 0 came out from the random need to be change to 1
        */
        smile.cancel(); t1.setText("REMEMBER"); t2.setText(number);
        /*
         count down timer on each level
         the word TYPE THE NUMBER came out when CountDownTimer on finish
         */
        countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
                time.setText("" + l/1000);
            }
            @Override
            public void onFinish() {
                t1.setText("TYPE THE NUMBER");
                t2.setText("");
            }
        };
        countDownTimer.start();
    }
    /**
     *stop the countDownTimer and run checkAnswer
     * if answer is wrong, run wrong.mp3, go to game_over.xml page and display score,
     * store the score in the database
     * else if answer is correct, run correct.mp3, lvl+1 and score + 10
     */
    private void checkAnswer(String a) {
        countDownTimer.cancel();
        t2.setText("");time.setText("0");
        t1.setText("TYPE THE NUMBER");
        answer = answer + a;t2.setText(answer);
        char[] as = answer.toCharArray();
        char[] ns = number.toCharArray();
        for (int i = 0; i < as.length; i++) {
            if(ns[i] != as[i]) {
                ((TextView)dialog.findViewById(R.id.scr)).setText("Your score is " + score);
                sad.show(); playSound(wrong); dialog.show();
                DatabaseHelper myDb = new DatabaseHelper(this);
                myDb.insertScore(score);
            } else {
                if(ns.length == as.length) {
                    smile.show(); playSound(correct); lvl = lvl + 1;
                    score = score + 10; setupNewGame();break;
                }
            }
        }
    }

    private void playSound(MediaPlayer mp) {
        try {
            if (mp != null && preferences.getInt("vol",2) == 1) {
                if (mp.isPlaying()) {
                    mp.pause();
                    mp.seekTo(0);
                    mp.start();
                } else {
                    mp.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(accelerometerSensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(accelerometerSensorListener);
    }

    /**
     *setting button, click for setting page
     */
    public void SettingButton(View view){

        startActivity(new Intent(MainActivity.this, Setting.class));
    }

}