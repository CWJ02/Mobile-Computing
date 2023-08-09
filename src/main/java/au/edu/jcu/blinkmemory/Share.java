package au.edu.jcu.blinkmemory;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;


import com.facebook.CallbackManager;

import com.facebook.login.widget.LoginButton;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


public class Share extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    ImageView imageView;
    ShareButton sbPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        loginButton = findViewById(R.id.login_button);
        imageView = findViewById(R.id.iv_pic);
        sbPhoto = findViewById(R.id.shr_photo);

        imageView.setImageResource(R.drawable.story);

        callbackManager = CallbackManager.Factory.create();
    }

    /**
     * sharing dialog
     * share content for photos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        SharePhoto sharePhoto = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();

        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto)
                .build();

        sbPhoto.setShareContent(sharePhotoContent);
    }

    /**
     * return to previous page
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

}