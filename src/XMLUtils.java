import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 20142014-1-17����9:16:51 XMLUtils.java vicentliu
 */

public class XMLUtils {

    public static boolean doc2XmlFile(Document doc, String filename) {
        boolean flag = true;
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }

    /**
     * �����ļ�
     * @param filename
     * @return
     */
    public static Document load(String filename) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filename));
            document.normalize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    /**
     * ���½ڵ�ֵ
     * @param filename �ļ�·�� ����·��
     * @param nodeName �ڵ� "TextView" "ImageView" ...
     * @param unitName ��λ����
     * @param ratio ���ű���
     */
    public static void xmlUpdateNodeValue(String filename ,String nodeName, String unitName ,float ratio) {
        Document docment = load(filename);
        Element element = docment.getDocumentElement();
        NodeList items = element.getElementsByTagName(nodeName);
        for (int i = 0; i < items.getLength();i++) {
            Element node = (Element) items.item(i);
            int result = (int) (getNumber(node.getFirstChild().getNodeValue()) * ratio);
            if (node.getFirstChild().getNodeValue().contains(unitName)) {
                node.getFirstChild().setNodeValue(result + unitName);
            }
        }
        doc2XmlFile(docment, filename);
    }

    /**
     * ���½ڵ�����ֵ
     * @param filename �ļ�·�� ����·��
     * @param nodeName �ڵ� "TextView" "ImageView" ...
     * @param nodeAttribute �ڵ��ڲ����� "android:layout_marginTop"
     * @param unitName ��λ����
     * @param ratio ���ű���
     */
    public static void xmlUpdate(String filename ,String nodeName, String nodeAttribute , String unitName ,float ratio) {
        Document docment = load(filename);
        Element element = docment.getDocumentElement();
        NodeList items = element.getElementsByTagName(nodeName);
        for (int i = 0; i < items.getLength();i++) {
            Element node = (Element) items.item(i);
            int result = (int) (getNumber(node.getAttribute(nodeAttribute).toString()) * ratio);
            if (node.getAttribute(nodeAttribute).toString().contains(unitName)) {
                node.setAttribute(nodeAttribute, result + unitName);
            }
        }
        doc2XmlFile(docment, filename);
    }

    /**
     * ��ȡ�ļ������ļ��б�
     * @param path �ļ���·��
     * @return
     */
    public static ArrayList<String> getFileList(String path) {
        ArrayList<String> fileList = new ArrayList<String>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null)
            return null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                getFileList(files[i].getAbsolutePath());
            } else {
                String fileName = files[i].getAbsolutePath().toLowerCase();
                fileList.add(fileName);
            }
        }
        return fileList;
    }

    /**
     * ��ȡ�ַ���������
     * @param string
     */
    public static int getNumber(String string) {
        int result = 0;
        Matcher m;
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        if (string != null) {
            m = p.matcher(string);   
            result = Integer.valueOf(m.replaceAll("").trim());
        }
        return result;
    }
}
