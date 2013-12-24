package oplayer.util;

import android.content.Context;
import com.join.musume.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: mawanjin@join-cn.com
 * Date: 12/16/13
 * Time: 10:44 AM
 */
public class SourceHolder {

    public static File getSource(Context context) {
        File file = null;
        //将raw里的视频文件复制到sd卡中去
//        if (!FileUtils.getInstance().isFileExist("/temp/vitamio/test.mp4")) {
        if (!FileUtils.getInstance().isFileExist("/temp/vitamio/mv.flv")) {
            try {
                InputStream in = context.getResources().openRawResource(R.raw.mv);
                file = FileUtils.getInstance().write2SDFromInput("/temp/vitamio/", "mv.flv", in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            file = new File(FileUtils.getInstance().getSDPATH() + "/temp/vitamio/mv.flv");
        return file;
    }
}
