package me.muscleorange.airfile.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by muscleorange on 14/11/30.
 */
public class FileExtraUtils {

    /**
     * 判断是否有SD卡
     * @return
     */
    public static boolean hasSdcard(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡根目录路径
     * @return
     */
    public static String getSDPath(){
        if(hasSdcard())
            return Environment.getExternalStorageDirectory().toString();
        return null;
    }

    /**
     * 创建文件夹
     * @param dirPath 文件夹full path
     */
    public static boolean createDir(String dirPath){
        File dir = new File(dirPath);
        if(!dir.exists())
            return dir.mkdirs();
        return true;
    }

    /**
     * 判断文件是否存在
     * @param path 文件路径
     * @return
     */
    public static boolean exist(String path){
        File f = new File(path);
        return f.exists();
    }


    /**
     * 拷贝文件
     * @param srcFilePath 原文件路径
     * @param destFilePath 目的文件路径
     * @param overwrite 是否覆盖目的文件，如果存在
     */
    public static void copyFile(String srcFilePath, String destFilePath, boolean overwrite){
        if(srcFilePath.equals(destFilePath) || !exist(srcFilePath)) return;
        if(exist(destFilePath) && !overwrite) return;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(srcFilePath).getChannel();
            outChannel = new FileOutputStream(destFilePath).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != inChannel) inChannel.close();
                if(null != outChannel) outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
