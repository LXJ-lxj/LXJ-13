package org.com.cn;
/*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
import java.util.HashMap;

public class Test1 {
    public static void main(String[] args) {
        int[] num=new int[]{2,7,11,15};
        int str=9;
        Solution1 solution1 = new Solution1();
        int[] ints = solution1.twoSum(num, str);
        for (int i : ints) {
            System.out.print("["+i+"]");
        }

    }
}
class Solution1 {
    //已知一个数a，找到等于target-a的另一个数
    //直接遍历数组的话是O(n)，查找哈希表可以优化为O(1)，总的时间复杂度从O(n^2)变为O(n)
    //空间复杂度从O(1)变为O(n)
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        //声明一个HashMap，key和value的类型都是int，变量名叫map。key和value分别记录数值和下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            //如果存在nums[j] + nums[i] == target，那么nums[j] = target - nums[i]
            int diff = target - nums[i];
            //如果存在diff，则返回diff和nums[i]的下标
            //HashMap的containsKey方法判断该HashMap中是否存在这个key
            if(map.containsKey(diff)){
                //返回的时候直接创建一个数组，创建数组时可以直接初始化，就不需要填大小了，比如说new int[]{1, 2, 3};
                //此时创建一个大小为2的数组，第一个元素为map.get(diff)也就是map中对应diff的value，第二个元素就是i，也就是2个数的下标
                return new int[]{map.get(diff), i};
            }else   //否则，将nums[i]存入map中
                map.put(nums[i], i);
        }
        return new int[0];
    }
}