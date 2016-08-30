package kushiel.study;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class LoadActivity extends AppCompatActivity implements View.
        OnClickListener {

    private TextView txt;
    private Button btn1;
    private ImageView imageView;

    private static final int GALLERY_IMAGE_REQUESET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        txt = (TextView) findViewById(R.id.txt);
        btn1 = (Button) findViewById(R.id.btn1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        txt.setText("Press a Button");

        btn1.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        if(view.equals(btn1)){
            if (Permission.requestPermission(
                    this,
                    GALLERY_IMAGE_REQUESET,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "DCIM" + File.separator;

                File list = new File(path);


                FilenameFilter imageFilter = new FilenameFilter() {

                    public boolean accept(File dir, String filename){

                        if(filename.toLowerCase().endsWith(".png")){
                            return true; }
                        else if(filename.toLowerCase().endsWith(".jpeg")) {
                        return true ;
                        }
                        else if(filename.toLowerCase().endsWith(".jpg")){
                            return true;
                        }else {
                            return false;
                        }
                    }
                };

                txt.setText("Loading......");
                Toast.makeText(this,"Please Wait.....",Toast.LENGTH_LONG).show();

                File[] files = list.listFiles(imageFilter);
                for (File file : files){
                    if(file.isDirectory()){
                        txt.setText("Directory : ");
                    }else {
                        txt.setText("    file : ");
                    }
                    try {
                        txt.setText(file.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
