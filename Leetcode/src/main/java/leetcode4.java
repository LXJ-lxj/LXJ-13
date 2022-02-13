/*
给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。*/
public class leetcode4 {
    /*	public StringBuffer reverse()
 将此字符序列用其反转形式取代。*/
        public String reverseWords(String s) {
            String words[] = s.split(" ");
            StringBuilder res=new StringBuilder();
            for (String word: words)
                res.append(new StringBuffer(word).reverse().toString() + " ");
            return res.toString().trim();
    }
}
