/*给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。


示例 1:

输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
*/
public class leetcode1 {
    public static void main(String[] args) {
        Solution S = new Solution();
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = 9;
        int a = S.search(nums, target);
        System.out.println(a);

    }
}

class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        int len = nums.length - 1;
        while(i <= len){
            int mid = (i +len) / 2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] > target){
                len = mid - 1;
            }
            else{
                i = mid +1;
            }
        }
        return -1;
    }
}