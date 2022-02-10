public class leetcode2 {
    public static void main(String[] args) {
        int [] nums= {1,3,5,6};
        find(nums,5);
    }
    private static String find(int[] arr,int num) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            if(num < arr[left]){   //未找到之应该插入到最开始位置
                return "无该元素，应该被插入到："+0+"号位置";
            }
            if(num > arr[right]){   //未找到之应该插入到最后位置
                return "无该元素，应该被插入到："+arr.length+"号位置";
            }

            int mid = (right - left + 1)/2 + left;
            if(arr[mid] == num){    //找到
                return "该元素下标为："+mid;
            }else if(arr[mid] > num){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
            if(arr[mid] < num && arr[mid+1] > num){   //未找到之应该插入到中间某个节点(不用判断mid的合法性，第一个判断，就不会让mid > arr.length -1).
                return "无该元素，应该被插入到："+(mid+1)+"号位置";
            }
        }
        return null;
    }
}
