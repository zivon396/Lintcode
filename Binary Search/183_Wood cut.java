// 猜数字
public class Solution {
    /**
     * @param l: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] l, int k) {
        // write your code here
        if (l == null || l.length == 0){
            return 0;
        }
        int max = 0;
        for (int i = 0; i < l.length; i++){
            max = Math.max(max, l[i]);
        }

        int start = 1, end = max;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            System.out.println(mid);
            if (getNum(mid, l) < k){
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (getNum(end, l) >= k){
            return end;
        }

        if (getNum(start, l) >= k){
            return start;
        }

        return 0;
    }

    private int getNum (int num, int[] l){
        int count = 0;
        for (int i = 0; i < l.length; i++){
            count += l[i] / num;
        }

        return count;
    }
}
