

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
     * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
     * @param fileName
     */
    public static void readFilebyChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        //���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽ�
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
                // ������������ַ��ֿ���ʾʱ���ỻ�����С�
                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
                if (((char) tempchar) != '\r')
                    System.out.println((char) tempchar);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽ�
        try {
            // һ�ζ�����ַ�
            char[] tempchars = new char[30];
            int charRead = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
            while ((charRead = reader.read(tempchars)) != -1) {
                // ͬ�����ε�\r����ʾ
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
     * �����ݻ�д���ļ���
     * @param filePath
     * @param content
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;
        try {
         // �����ļ�·���������������
            bw = new BufferedWriter(new FileWriter(filePath));
            // ������д���ļ���
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
