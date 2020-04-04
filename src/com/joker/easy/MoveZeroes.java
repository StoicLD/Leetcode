package com.joker.easy;

public class MoveZeroes
{
    public void moveZeroes(int[] nums)
    {
        int zeroPointer = 0;
        int nonzeroPointer = 0;
        boolean findZero = false;
        boolean findNonzero = false;
        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] == 0)
            {
                if (!findZero)
                {
                    zeroPointer = i;
                    findZero = true;
                }
            }
            else
            {
                nonzeroPointer = i;
                findNonzero = true;
            }
            if (findZero && findNonzero && (nonzeroPointer > zeroPointer))
            {
                nums[zeroPointer] = nums[nonzeroPointer];
                nums[nonzeroPointer] = 0;
                zeroPointer++;
                findNonzero = false;
            }
        }
    }

    // Shift non-zero values as far forward as possible
    // Fill remaining space with zeros
    public void moveZeroes2(int[] nums)
    {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num : nums)
        {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length)
        {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args)
    {
        int[] a = {1, 0};
        MoveZeroes mz = new MoveZeroes();
        mz.moveZeroes(a);

    }

}
