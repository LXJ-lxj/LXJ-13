import java.util.List;

/*给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。*/
/*public class leetcode11 {
    List<List<Integer>> res = new ArrayList<>();
    int len = nums.length;
    //先进行排序
        Arrays.sort(nums);
        for(int i = 0;i < len-2; i ++){
        //大于0提前结束
        if(nums[i] > 0) break;
        if(i > 0 && nums[i] == nums[i-1]) continue;
        int left = i+1;
        int right = nums.length-1;
        while(left < right){
            int sum = nums[i]+nums[left]+nums[right];
            if(sum==0){
                //数字一样跳过 防止重复
                res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                while(left<right&&nums[left]==nums[left+1])
                    left++;
                while(left<right&&nums[right]==nums[right-1])
                    right--;
                left ++;
                right --;
            }
            //小 右移
            else if(sum <0) left++;
                //大 左移
            else  if(sum>0) right--;
        }
    }
}*/
