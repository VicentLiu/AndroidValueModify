

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class NumberUtil {

    public static ArrayList<File> getFileList(String path) {
        ArrayList<File> fileList = new ArrayList<File>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null)
            return null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileList(files[i].getAbsolutePath());
            } else {
                String fileName = files[i].getAbsolutePath().toLowerCase();
                fileList.add(files[i].getAbsoluteFile());
            }
        }
        return fileList;
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     * @param fileName
     */
    public static void readFilebyChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        //以字符为单位读取文件内容，一次读一个字节
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r')
                    System.out.println((char) tempchar);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //以字符为单位读取文件内容，一次读多个字节
        try {
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charRead = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charRead = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charRead == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.println(tempchars);
                } else {
                    for (int i = 0; i < charRead; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.println(tempchars[i]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e2) {
                }
            }
        }
    }

    /**
     * 将内容回写到文件中
     * @param filePath
     * @param content
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;
        try {
         // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e2) {
                    bw = null;
                }
            }
        }
    }
}
