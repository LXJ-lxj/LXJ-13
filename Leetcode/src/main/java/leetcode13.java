public class leetcode13 {
    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int cur = 0;
        int num = x;
        while(num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }
}
