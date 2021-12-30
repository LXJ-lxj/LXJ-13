public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        long s = 1, e = (long)n+1, mid = 0;
        while (s < e) {
            mid = (s + e)/2;
            if (isBadVersion((int)mid)) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }
        return (int)s;
    }
}
