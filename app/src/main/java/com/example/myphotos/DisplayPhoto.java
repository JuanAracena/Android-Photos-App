package com.example.myphotos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DisplayPhoto extends AppCompatActivity {
    public static Photo currentPhoto;
//    public Photo currentPhoto;
    public static AlbumT currentAlbum;
    public static Account acc;

    private Button backButton;
    private Button nextButton;
    private Button addTag;
    private Button delete;
    private Button backtoPhotolist;

    private TextInputEditText tagtypemenu;
    private TextInputEditText textinput;

    private ListView taglist;
    private ArrayAdapter<String> adapter3;
    private ArrayList<String> photoTags;
    private ImageView display;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photodisplay);
        setListAdapter(adapter3);

        backButton = (Button) findViewById(R.id.backDisplayButton);
        nextButton = (Button) findViewById(R.id.forwardDisplayButton);
        addTag = (Button) findViewById(R.id.addTagTypeButton);
        backtoPhotolist = (Button) findViewById(R.id.backtoPhotolist);
        delete = (Button) findViewById(R.id.deletetag);
        taglist = (ListView) findViewById(R.id.taglist);
        display = (ImageView) findViewById(R.id.photoDisplay);

        String photoFilePath = getIntent().getStringExtra("photoFilePath");
        currentPhoto = new Photo(photoFilePath);

        display.setImageBitmap(currentPhoto.getImage());

        tagtypemenu = findViewById(R.id.textdropdown);
        textinput = findViewById(R.id.textinput);

        photoTags = new ArrayList<>();
        readAcc();

        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, photoTags);
        taglist.setAdapter(adapter3);

        backtoPhotolist.setOnClickListener(view -> {
            Intent intent = new Intent(this, InsideAlbum.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(view -> {
            sendUserDataBack();
        });

        nextButton.setOnClickListener(view -> {

        });

    }

    private void setListAdapter(ArrayAdapter<String> adapter3) {
    }

    private void sendUserDataBack() {
        Intent send = new Intent(DisplayPhoto.this, InsideAlbum.class);
        writeAcc();
        startActivity(send);
    }

    public void writeAcc() {
        try {
            String p = this.getApplicationInfo().dataDir + "/appdata.dat";
            FileOutputStream fos = new FileOutputStream(p);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(acc);
            fos.close();
            oos.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void readAcc() {
        try {
            String p = this.getApplicationInfo().dataDir + "/appdata.dat";
            FileInputStream fis = new FileInputStream(p);
            ObjectInputStream ois = new ObjectInputStream(fis);
            acc = (Account) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ignored) {
            ;
        }
    }
}
