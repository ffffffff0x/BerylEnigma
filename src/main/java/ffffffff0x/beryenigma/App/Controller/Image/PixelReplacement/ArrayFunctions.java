package ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement;

/**
 * from https://github.com/yuchenxi2000/PicEncrypt
 * @author: RyuZUSUNC
 * @create: 2021-05-20 11:29
 **/

public class ArrayFunctions {
    //二维数组降一维
    void recovery(int[][] arr2, int[] arr1, int M, int N)
    {
        int k = 0;
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
            {
                arr1[k++] = arr2[i][j];
            }

    }
    //一维数组升二维
    void change(int[] arr1, int[][] arr2, int M, int N)
    {
        int k = 0;
        for (int i = 0; i < M; ++i)
            for (int j = 0; j < N; ++j)
            {
                arr2[i][j] = arr1[k++];
            }

    }
    //把二维数组行列倒换
    void arr_change(int[][] arr, int[][] temp, int M, int N)
    {
        for (int i = 0; i < M; ++i)
        {
            for (int j = 0; j < N; ++j)
                temp[j][i] = arr[i][j];
        }
    }

    //把arr1复制给arr2
    void arr_copy(double[] arr1, double[] arr2, int N) {
        if (N >= 0) System.arraycopy(arr1, 0, arr2, 0, N);
    }
}
