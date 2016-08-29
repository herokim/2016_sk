package hero.study;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Created by admin on 16. 8. 29..
 */
public class imageFilter implements FilenameFilter {

    public final String[] okFileExtensions =
            new String[] {"jpg", "jpeg", "png"};

    @Override
    public boolean accept(File dir, String filename) {
        String lowerName = filename.toLowerCase();

        if(lowerName.endsWith("jpeg")){
            return true;
        }else if(lowerName.endsWith("jpg")){
            return true;
        }else if(lowerName.endsWith("png")){
            return true;
        }else{
            return false;
        }

    }
}
