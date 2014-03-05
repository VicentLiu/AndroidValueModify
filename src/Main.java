

/**
 * 20142014-1-17обнГ9:15:59
 * Main.java
 * vicentliu
 */

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        ArrayList<String> fileArrayList = new ArrayList<String>();
        XMLUtils.xmlUpdateNodeValue("E:\\MayBoon\\test\\dimens.xml", "dimen", "dp", 0.75f);
//        fileArrayList = XMLUtils.getFileList("E:\\mayboon\\test");
//        for (String fileName : fileArrayList) {
//            XMLUtils.xmlUpdate(fileName,"TextView","android:layout_marginTop","dp", 1.25f);
//        }
    }

}
