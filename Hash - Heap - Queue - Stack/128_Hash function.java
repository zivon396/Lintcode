public class Solution {
    /**
     * @param key: A string you should hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(String key, int HASH_SIZE) {
        // write your code here
        long ans = 0;
        for(int i = 0; i < key.length(); i++) {
            ans = (ans * 33 + (int)(key.charAt(i))) % HASH_SIZE; 
        }

        return (int)ans;
    }
}
