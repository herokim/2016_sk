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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;

public class FileLoad extends AppCompatActivity implements View.
        OnClickListener{

    private Button button;
    private LinearLayout layout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_load);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        button = (Button)findViewById(R.id.button);
        layout = (LinearLayout)findViewById(R.id.linearLayout);
        textView = (TextView)findViewById(R.id.textView);

        textView.setText("Press a Button");
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.equals(button)){
            Toast.makeText(this,"Please Wait....",Toast.LENGTH_LONG).show();

            Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null,"bucket_display_name='camera'",null,null);

            textView.setText(c.toString());

        }

        }
    }