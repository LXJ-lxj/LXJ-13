public class leetcode5 {
    public static void main(String[] args) {
        String s="abcabcbb";
        int[] last =new int[128];
        for(int i=0;i<128;i++){
            last[i]=-1;
        }
        int n=s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            System.out.println(index);
            System.out.println(last[index]+1);
            start = Math.max(start, last[index] + 1);
            System.out.println(start);
            res   = Math.max(res, i - start + 1);
            System.out.println(res);
            last[index] = i;
        }

        System.out.println(res);
    }
}
