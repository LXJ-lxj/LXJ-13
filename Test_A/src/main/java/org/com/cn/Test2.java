package org.com.cn;

/*给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：

nums[0] = 0
nums[1] = 1
当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
返回生成数组 nums 中的 最大 值。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class Test2 {
    public static void main(String[] args) {
        int a=7;
        Solution c=new Solution();
        int maximumGenerated = c.getMaximumGenerated(a);
        System.out.println(maximumGenerated);
    }
}
class Solution
{
    public int getMaximumGenerated(int n)
    {
        if (n == 0)
            return 0;

        int [] nums = new int [n + 2];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 1; i < n/2 + 1; i ++)
        {
            nums[2 * i] = nums[i];
            nums[2 * i + 1] = nums[i] + nums[i + 1];
        }

        int res = 0;
        for (int i = 0; i < n + 1; i ++)
            res = Math.max(res, nums[i]);
        return res;
    }
}

