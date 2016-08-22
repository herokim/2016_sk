package hero.study;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by admin on 16. 8. 22..
 */
public class PermissionUtil {

    public static boolean requestPermission(
            Activity activity, int reqeuestCode, String... permission
    ) {
        boolean granted = true;
        ArrayList<String> permissionNeeded = new ArrayList<>();

        for (String s : permission) {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, s);
            boolean hasPermission = (permissionCheck == PackageManager.PERMISSION_GRANTED);
            granted &= hasPermission;
            if (!hasPermission) {
                permissionNeeded.add(s);
            }
        }

        if (granted) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    permissionNeeded.toArray(new String[permissionNeeded.size()]),
                    reqeuestCode);
            return false;
        }
    }

    public static boolean permissionGranted(
            int requestCode, int permissionCode, int[] grantResult) {
        if (requestCode == permissionCode) {
            if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
                return  false;
    }
}



