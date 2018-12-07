package test;

public class SubString {
    public static void main(String[] args) {
        String str = "ilovejavajavacdejavailha123java34javali3java@#@R#FDSFAFDAjavai23o2fjai23javii2o3java";
        String sToFind = "java";
        int num = new SubString().countStr(str,sToFind);
        System.out.println("共找到" + num + "个" + sToFind);
    }

    /**
     * @param str 原字符串
     * @param sToFind 需要查找的字符串
     * @return 返回在原字符串中sToFind出现的次数
     */
    private int countStr(String str,String sToFind) {
        int num = 0;
        while (str.contains(sToFind)) {
            str = str.substring(str.indexOf(sToFind) + sToFind.length());
            num ++;
        }
        return num;
    }
}