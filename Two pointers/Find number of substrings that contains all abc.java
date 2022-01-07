package microsoft;

public class FindNumOfValidSubstrings {
    // Time complexity O(N), where N is the length of given string
    public static int findNumOfValidSubstrings (String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] freq = new int[3];
        char[] chars = s.toCharArray();
        int count = 0;

        int size = s.length();
        // The number of unique characters (a, b or c) within a substring (unique means after removing duplicates)
        // Possible values: 0 - 3. NumOfUnique < 3 means a substring doesn't contain at least one from (a, b, c)
        int NumOfUnique = 0;
        // Use for loop instead, make l++ at the end of each loop.
        for (int l = 0, r = 0; l < size; l++){
            // Keeps moving r to the right, until the substring within current window contains all of (a, b, c)
            while (NumOfUnique < 3 && r < size){
                freq[chars[r] - 'a']++;
                if (freq[chars[r] - 'a'] == 1){
                    NumOfUnique++;
                }
                r++;
            }

            // If current substring contains all of (a, b, c), then count the number of substrings starting from l,
            // and remove the character at l from current window
            if (NumOfUnique == 3){
                count += size - r + 1;

                freq[chars[l] - 'a']--;
                if (freq[chars[l] - 'a'] == 0){
                    NumOfUnique--;
                }
            }
        }

        return count;
    }

    public static void main(String [] args) {
        String s = "ababacb";
        String[] strs = {null, "", "abc", "abcabc", "abac", "abacb", "aaabaca"};
        int count = findNumOfValidSubstrings(strs[2]);

        System.out.println(count);
    }
}
