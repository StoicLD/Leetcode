package com.joker.other;

public class PerformStringShifts
{
    public String stringShift(String s, int[][] shift)
    {
        int shiftDis = 0;
        for(int i = 0; i < shift.length; i++)
        {
            shiftDis += shift[i][0] > 0 ? shift[i][1] : -shift[i][1];
        }
        //往右为正，往左为负
        shiftDis %= s.length();
        if(shiftDis < 0)
            return s.substring(-shiftDis) + s.substring(0, -shiftDis);
        else
            return s.substring(s.length() - shiftDis) + s.substring(0, s.length() - shiftDis);
    }
}
