// 14. Longest Common Prefix
// Write a function to find the longest common prefix string amongst an array of strings.
// If there is no common prefix, return an empty string "".
// Example 1:
// Input: strs = ["flower","flow","flight"]
// Output: "fl"
// Example 2:
// Input: strs = ["dog","racecar","car"]
// Output: ""
// Explanation: There is no common prefix among the input strings.


class Solution {
    public String e_longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0)
            return "";
        StringBuilder output = new StringBuilder();
        char temp;

        for (int i = 0; i < strs[0].length(); i++) 
        {
            temp = strs[0].charAt(i);
        
            for (int j = 0; j <  strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != temp) {
                      return output.toString();
                }
            }
            output.append(temp);
        }
          return output.toString();
    }
}