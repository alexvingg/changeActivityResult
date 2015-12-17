package br.com.aulatresactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_TAKE_PICTURE = 902;

    public static final String PARAM_INTENT = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = (EditText) findViewById(R.id.editText);
                Intent it = new Intent(MainActivity.this, ResultActivity.class);
                it.putExtra(PARAM_INTENT, edit.getText().toString());
                startActivity(it);
            }
        });
    }


    public void ligar(View v){
        //Uri ur = Uri.parse("tel:991215946");
        //Intent i = new Intent(Intent.ACTION_DIAL, ur);
        //startActivity(i);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Compartilhar com whats app");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
        sendIntent.setPackage("com.whatsapp");
    }

    public void takePicture(View v){
        Intent take = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(take,REQ_CODE_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_TAKE_PICTURE && resultCode == RESULT_OK){
            Bitmap bit = (Bitmap) data.getExtras().get("data");
            ImageView img = (ImageView) findViewById(R.id.showImage);
            bit.getWidth();
            img.setImageBitmap(bit);
            img.setRotation(90);
        }
    }
}
