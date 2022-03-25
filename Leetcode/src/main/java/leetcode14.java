public class leetcode14 {
    /*环形公交路线上有n个站，按次序从0到n - 1进行编号。我们已知每一对相邻公交站之间的距离，distance[i]表示编号为i的车站和编号为(i + 1) % n的车站之间的距离。

环线上的公交车都可以按顺时针和逆时针的方向行驶。

返回乘客从出发点start到目的地destination之间的最短距离。*/
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int len = distance.length;
        if (start == destination) return 0;
        int rightIndex = (start + 1) % len;
        int min = Integer.MAX_VALUE;
        int sum = distance[start];
        while (rightIndex != destination) {
            sum += distance[rightIndex];
            rightIndex = (rightIndex + 1) % len;
        }
        min = Math.min(min, sum);
        rightIndex = (destination + 1) % len;
        sum = distance[destination];
        while (rightIndex != start) {
            sum += distance[rightIndex];
            rightIndex = (rightIndex + 1) % len;
        }
        min = Math.min(min, sum);
        return min;
    }

}
