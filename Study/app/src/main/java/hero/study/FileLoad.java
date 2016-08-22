package hero.study;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.jar.Manifest;

public class FileLoad extends AppCompatActivity implements View.
        OnClickListener{

    private Button button;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout linearLayout;

    private static final int Gallery_Image_Request = 1;

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

            File list = new File(Path);


            FilenameFilter imageFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {

                    if(filename.toLowerCase().endsWith(".png")){
                        return true ;
                    } else if(filename.toLowerCase().endsWith(".jpg")){
                        return true;
                    } else if(filename.toLowerCase().endsWith(".jpeg")){
                        return true;
                    } else {
                        return false;
                    }
                }
            };

                textView.setText("Loading...");
                Toast.makeText(this,"Please Wait....",Toast.LENGTH_LONG).show();

                File[] files = list.listFiles(imageFilter);
                for(File file : files){
                    if(file.isDirectory()){
                        textView.setText("Directory : ");
                    } else {
                        textView.setText("      File : ");
                    }
                    try {
                        textView.append(file.getCanonicalPath());
                        FileFilter fileFilter = new FileFilter() {
                            @Override
                            public boolean accept(File pathname) {
                                try {
                                    pathname.getCanonicalFile();
                                    imageView.setImageBitmap((Bitmap) pathname.getCanonicalFile());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }



        }

        }
    }