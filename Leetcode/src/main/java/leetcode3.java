public class leetcode3 {
        public static void main(String[] args) {
            int[] arr = {0,1,0,3,12};
            moveZeroes(arr);
            for (int i:arr) {
                System.out.print(i+",");
            }
        }
        public static int[] moveZeroes(int[] arr){
            if (arr == null){
                return null;
            }
            //第一次遍历的时候，j指针记录非0的个数，只要是非0的统统都赋给nums[j]
            int j = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0){
                    arr[j++] = arr[i];
                }
            }
            //非0元素统计完了，剩下的都是0了
            //所以第二次遍历把末尾的元素都赋为0即可
            for (int i = j; i < arr.length; i++) {
                arr[i] = 0;
            }
            return arr;
        }

}
