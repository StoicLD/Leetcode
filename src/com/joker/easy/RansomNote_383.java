package com.joker.easy;

public class RansomNote_383
{
    public boolean canConstruct(String ransomNote, String magazine)
    {
        //就是ransomNote是不是magazine的子串
        char[] alphabet = new char[128];
        for(int i = 0; i < magazine.length(); i++)
        {
            alphabet[(int)magazine.charAt(i)]++;
        }
        for(int j = 0; j < ransomNote.length(); j++)
        {
            char c = ransomNote.charAt(j);
            if(alphabet[c] <= 0)
                return false;
            else
                alphabet[c]--;
        }
        return true;
    }
}
