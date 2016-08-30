package hero.study;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import java.io.FilenameFilter;
import java.io.IOException;

public class FileLoad extends AppCompatActivity implements View.
        OnClickListener{

    private static final int Gallery_Image_Request = 1;
    private Button button;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_load);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        textView.setText("Press a Button");

        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.equals(button)){

            if(PermissionUtil.requestPermission(
                    this,
                    Gallery_Image_Request,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)){

                String Path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "DCIM" + File.separator + "Camera";

                File gallery = new File(Path);


                FilenameFilter filenameFilter = new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        String lowerCase = filename.toLowerCase();

                        if(lowerCase.endsWith("jpg")){
                            return true;
                        }else if(lowerCase.endsWith("png")){
                            return true;
                        }else if(lowerCase.endsWith("jpeg")){
                            return true;
                        }else {
                            return false;
                        }
                    }
                };

                File[] files = gallery.listFiles(filenameFilter);

// update
                String temp = "";

                textView.setText("Loading...");

                Toast.makeText(this,"Please Wait....",Toast.LENGTH_LONG).show();

                for(File f : files){
                    if(f.isDirectory()){
                        textView.setText("Directory : ");
                    }else{
                        textView.setText("Files : ");
                    }
                    try {

                        textView.setText(f.getCanonicalPath());
                        temp = f.getCanonicalPath();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                imageView.setImageBitmap(BitmapFactory.decodeFile(temp));

            }



        }

        }

    }
