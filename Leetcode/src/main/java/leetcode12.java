public class leetcode12 {
    /*
    * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。*/

    public static void main(String[] args) {
       /* int left=0;
        int right=nums.length-1;
        int pos=0;
        int tmp;
        while(pos<=right){   //pos从0开始，向右到right时，再向右的值都是替换过的
            if(nums[pos]==0){//三色旗问题只有0.1.2
                if(pos!=left){   //可以减少不必要的替换所花费的时间
                    tmp=nums[pos];    //替换
                    nums[pos]=nums[left];
                    nums[left]=tmp;
                    left++;
                    pos++;  //替换之后left及之前的值都可以确定是遍历替换过的，所以pos++
                }else{
                    left++;
                    pos++;
                }
            }
            else if(nums[pos]==2){
                if(pos!=right){
                    tmp=nums[pos];
                    nums[pos]=nums[right];
                    nums[right]=tmp;
                    right--;   //这里pos不能++，替换过来的right值还没有比较过
                }else{
                    right--;
                }
            }
            else{
                pos++;
            }
        }*/
    }


}
