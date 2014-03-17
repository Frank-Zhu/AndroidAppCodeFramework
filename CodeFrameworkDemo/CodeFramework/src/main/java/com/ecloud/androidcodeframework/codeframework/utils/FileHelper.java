package com.ecloud.androidcodeframework.codeframework.utils;

import android.content.Context;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      14-3-16 下午2:24
 * Description: 文件操作工具类
 * Modification  History:
 * Date         	Author        		Version        	Discription
 * -----------------------------------------------------------------------------------
 * 14-3-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FileHelper {
    private final static String TAG = "FileUtils";

    /**
     * 拷贝assets目录文件到指定文件夹
     * @param context 上下文
     * @param assetDir asset目录地址
     * @param dir 文件目的地地址
     * @return true:成功 false:失败
     */
    public static boolean copyAssetsFile(Context context, String assetDir, String dir) {
        LogHelper.i(TAG,"--> copyAssetsFile");
        LogHelper.i(TAG,"assetDir = " + assetDir);
        LogHelper.i(TAG,"dir = " + dir);
        String[] files;
        try {
            files = context.getResources().getAssets().list(assetDir);
        } catch (IOException e1) {
            return false;
        }

        File mWorkingPath = new File(dir);
        // if this directory does not exists, make one.
        if (!mWorkingPath.exists()) {
            if (!mWorkingPath.mkdirs()) {
                return false;
            }
        }

        for (String file : files) {
            try {
                // we make sure file name not contains '.' to be a folder.
                if (!file.contains(".")) {
                    if (0 == assetDir.length()) {
                        copyAssetsFile(context, file, dir + file + "/");
                    } else {
                        copyAssetsFile(context, assetDir + "/" + file, dir + file + "/");
                    }
                    continue;
                }
                File outFile = new File(mWorkingPath, file);
                if (outFile.exists()) {
                    outFile.delete();
                }
                InputStream in;
                if (0 != assetDir.length()) {
                    in = context.getAssets().open(assetDir + "/" + file);
                } else {
                    in = context.getAssets().open(file);
                }
                OutputStream out = new FileOutputStream(outFile);
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 复制单个文件
     * @param oldPath String 源文件路径
     * @param newPath String 复制后路径
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        try {
            LogHelper.d(TAG, "copyFile --> oldPath = " + oldPath + " --> newPath = " + newPath);
            int byteRead;
            File oldFile = new File(oldPath);

            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                return false;
            }

            InputStream inStream = new FileInputStream(oldPath); //读入原文件
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            while ( (byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        }
        catch (Exception e) {
            LogHelper.d(TAG,"copy file error!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除单一文件
     * @param filePath 文件路径
     * @return boolean:成功 false:失败
     */
    public static boolean deleteFile(String filePath){
        LogHelper.d(TAG, "deleteFile --> filePath = " + filePath);
        File mFile = new File(filePath);
        return mFile.exists() && mFile.delete();
    }

    /**
     * 删除文件夹
     * @param path 文件夹路径
     * @return true:成功 false:失败
     */
    public static boolean deleteDir(String path){
        boolean success = true ;
        File file = new File(path) ;
        if(file.exists()){
            File[] fileList = file.listFiles() ;
            if(fileList != null){
                for (File list : fileList) {
                    if (list.isDirectory()) {
                        deleteDir(list.getPath());
                    } else {
                        boolean ret = list.delete();
                        if (!ret) {
                            success = false;
                        }
                    }
                }
            }
        } else {
            success = false ;
        }
        if(success){
            success = file.delete() ;
        }
        return success ;
    }

    /**
     * 检查文件是否存在
     * @param filePath 文件路径
     * @return true:存在 false:不存在
     */
    public static boolean checkFileExist(String filePath) {
        LogHelper.d(TAG, "checkFileExist --> filePath = " + filePath);
        boolean isExist;
        File file = new File(filePath);
        isExist = file.exists();
        LogHelper.d(TAG, "checkFileExist --> isExist = " + isExist);
        return isExist;
    }

    /**
     * 保存字符串到文件
     * @param filePath 文件路径
     * @param message 需要保存的字符串
     * @param append    是否追加模式
     */
    public static void saveMsgToFile(String filePath, String message, boolean append) {
        try {
            LogHelper.d(TAG, "saveMsgToFile --> filePath = " + filePath);
            FileOutputStream out = new FileOutputStream(filePath,append);
            byte[] bytes = message.getBytes();
            out.write(bytes);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件转为字符串(适用于小文件)
     * @param context 上下文
     * @param filePath 文件路径
     * @param isAssets 是否asset目录文件
     * @return String
     */
    public static String readFileToString(Context context, String filePath, boolean isAssets) {
        LogHelper.d(TAG, "readFileToString --> filePath = " + filePath);
        String res = "";
        InputStream in = null;
        FileInputStream inFile = null;
        int length;
        try {
            if (isAssets) {
                in = context.getResources().getAssets().open(filePath);
                length = in.available();
            } else {
                inFile = new FileInputStream(filePath);
                length = inFile.available();
            }
            byte[] buffer = new byte[length];
            int len;
            if (inFile != null) {
                len = inFile.read(buffer);
                inFile.close();
            } else {
                len = in.read(buffer);
                in.close();
            }
            if(len > 0){
                res = EncodingUtils.getString(buffer, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 读取文件流(适用于小文件)
     * @param context 上下文
     * @param filePath 文件路径
     * @param isAssets 是否asset目录文件
     * @return byte[]
     */
    public static byte[] readFileToByte(Context context, String filePath, boolean isAssets) {
        LogHelper.d(TAG, "readFileToByte --> filePath = " + filePath);
        InputStream in = null;
        FileInputStream inFile = null;
        int length;
        byte[] buffer;
        try {
            if (isAssets) {
                in = context.getResources().getAssets().open(filePath);
                length = in.available();
            } else {
                inFile = new FileInputStream(filePath);
                length = inFile.available();
            }

            buffer = new byte[length];
            int redLen;
            if (inFile != null) {
                redLen = inFile.read(buffer);
                inFile.close();
            } else {
                redLen = in.read(buffer);
                in.close();
            }
            return redLen > 0 ? buffer : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件夹下所有文件
     * @param strPath 文件夹路径
     * @param isAddDirectory 是否添加文件夹
     * @return ArrayList<File> 文件列表
     */
    public static ArrayList<File> getDirectoryAllFile(String strPath,boolean isAddDirectory){
        ArrayList<File> fileList = new ArrayList<File>();
        refreshFileList(strPath, fileList,isAddDirectory);
        return fileList;
    }

    /**
     * 读取文件夹下所有文件
     * @param strPath 文件夹路径
     * @param fileList 文件List
     * @param isAddDirectory 是否添加文件夹
     */
    public static void refreshFileList(String strPath, ArrayList<File> fileList, boolean isAddDirectory) {
        LogHelper.d(TAG, "refreshFileList--> strPath = " + strPath);
        File dir = new File(strPath);
        File[] files = dir.listFiles();

        if (files == null)
            return ;

        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    fileList.add(file);
                }
                LogHelper.d(TAG, "refreshFileList--> files = " + file);
                refreshFileList(file.getAbsolutePath(), fileList, isAddDirectory);
            } else {
                fileList.add(file);
                LogHelper.d(TAG, "refreshFileList--> files = " + file);
            }
        }
    }
}
