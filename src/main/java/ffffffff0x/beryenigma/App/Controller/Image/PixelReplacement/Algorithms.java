package ffffffff0x.beryenigma.App.Controller.Image.PixelReplacement;

import java.util.*;

/**
 * @author: RyuZUSUNC
 * @create: 2021-05-20 11:29
 **/

public class Algorithms {
    //加解密时使用，选择法排序，同时生成地址映射表
    static void SelectSort(double[] arr, int length, HashMap<Double, Integer> m, int[] address_arr) {
        if (arr == null || length <= 0)return;
        int index;
        for (int i = 0; i < length; ++i) {
            index = i;
            for (int j = i; j < length; ++j) {
                if (arr[j] < arr[index])index = j;
            }
            if (index != i) {
                double temp;
                temp=arr[i];
                arr[i]=arr[index];
                arr[index]=temp;
            }

            int address=m.get(arr[i]);
            address_arr[address] = i;

				/*
				算法解释：
				i,是当前位置的下标，arr[i]是当前位置的元素，
				由于选择法每一趟排序过后，当前位置一定是之后范围内最小元素。
				也就是说，它现在的位置就是整个排序完成后的位置。
				所以，可以在一轮排序之后，直接对当前元素生成它的地址映射。
				m<double,int>是对原混沌序列的值和下标的映射，
				即map[x]的意义为x在原序列中的下标值。
				而address_arr是地址映射表，它的下标意为原序列的那一个元素，
				它的值意为，那个元素在经过排序的序列中的下标。
				*/

        }
    }
    //生成一个混沌序列，以x为初值
    static void produce_logisticArray(double x, double[] arr, int N) {
        double u = 3.9999999;
        arr[0] = x;
        for (int i = 1; i < N; ++i) {
            arr[i] = u*arr[i - 1] * (1 - arr[i - 1]);
        }
    }

    //通过混沌序列，生成 值-下标 的反向映射
    static void produce_map(HashMap<Double, Integer> m, double[] logistic_array, int N) {
        for (int i = 0; i < N; ++i) {
            m.put(logistic_array[i], i);
        }
    }

    //行置乱算法
    double rowEncrypt(int[][] pixel, double x1, int i, int N)
    {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array = new double[N] ;
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);

        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);

        //用一个暂存数组保存被置乱后的象素数组
        int[] temp =new int[N];
        for (int j = 0; j < N; ++j) {
            temp[address_array[j]] = pixel[i][j];
        }
        //正式置乱原图
        System.arraycopy(temp, 0, pixel[i], 0, N);
        return logistic_array[N - 1];
    }

    //行置乱算法
    void allRowEncrypt(int[][] pixel, double x1, int M, int N) {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array = new double[N] ;
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);

        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);

        for (int i = 0; i < M; ++i) {
            int[] temp =new int[N];
            for (int j = 0; j < N; ++j)
            {
                temp[address_array[j]] = pixel[i][j];
            }
            //正式解密原图
            System.arraycopy(temp, 0, pixel[i], 0, N);
        }
    }

    //行置乱接口
    void rowEncrypt_interface(int[][] pixel, double x1, int M, int N) {
        double x = x1;
        //对每一行都进行置乱
        for (int i = 0; i < M; ++i)
        {
            x = rowEncrypt(pixel, x, i, N);
        }
    }

    //列置乱算法
    double columnEncrypt(int[][] pixel, double x1, int i, int M, int N) {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array =new double[N];
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);
        //比对排序后的序列与排序前的序列的
        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);
        //用一个暂存数组保存被置乱后的象素数组
        int[] temp =new int[N];
        for (int j = 0; j < N; ++j)
        {
            temp[address_array[j]] = pixel[i][j];
        }
        //正式置乱原图
        System.arraycopy(temp, 0, pixel[i], 0, N);

        return logistic_array[N - 1];
    }

    //列置乱接口
    void columnEncrypt_interface(int[][] pixel, double x1, int M, int N) {
			/*
			由于置乱的算法是每行置乱，所以不能直接进行列置乱
			要把当前二维数组转的行列转换
			即：原本的pixel[M][N] 转换成 pixel[N][M]
			然后用同样的算法进行置乱
			之后再进行一次行列转换即可
			*/
        ArrayFunctions af=new ArrayFunctions();
        int[][] temp =new int[N][M];
        //行列互换
        af.arr_change(pixel,temp, M, N);

        double x = x1;
        for (int i = 0; i < N; ++i)
        {
            x = columnEncrypt(temp, x, i,N ,M);
        }
        int[][] temp2 =new int[M][N];
        //再次行列互换
        af.arr_change(temp,temp2, N, M);
        //正式将原数组置乱
        for (int i = 0; i < M; ++i)
        {
            System.arraycopy(temp2[i], 0, pixel[i], 0, N);
        }

    }
    //行解密算法
    double rowDecrypt(int[][] pixel, double x1, int i, int N) {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array =new double[N];
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);
        //比对排序后的序列与排序前的序列的
        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);
        //用一个暂存数组保存被置乱后的象素数组
        int[] temp =new int[N];
        for (int j = 0; j < N; ++j)
        {
            temp[j] = pixel[i][address_array[j]];
        }
        //正式解密原图
        System.arraycopy(temp, 0, pixel[i], 0, N);
        return logistic_array[N - 1];
    }

    void allRowDecrypt(int[][] pixel, double x1, int M, int N) {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array =new double[N];
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);
        //比对排序后的序列与排序前的序列的
        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);
        for (int i = 0; i < M; ++i) {
            int[] temp =new int[N];
            for (int j = 0; j < N; ++j)
                temp[j] = pixel[i][address_array[j]];
            //正式解密原图
            System.arraycopy(temp, 0, pixel[i], 0, N - 1 + 1);
        }
    }

    //行解密接口
    void rowDecrypt_interface(int[][] pixel, double x1, int M, int N) {
        double x = x1;
        for (int i = 0; i < M; ++i)
        {
            x = rowDecrypt(pixel, x, i, N);
        }
    }

    //列解密算法
    double columnDecrypt(int[][] pixel, double x1, int i, int M, int N) {
        ArrayFunctions af=new ArrayFunctions();
        //生成混沌序列
        double[] logistic_array =new double[N];
        produce_logisticArray(x1, logistic_array, N);
        //建立值与下标映射的Map
        HashMap<Double, Integer> m= new HashMap<>();
        produce_map(m, logistic_array, N);
        //拷贝混沌序列
        double[] temp_logArr =new double[N];
        af.arr_copy(logistic_array, temp_logArr, N);
        //比对排序后的序列与排序前的序列的
        int[] address_array =new int[N];
        //对混沌序列进行排序，采用选择，并且同时生产地址映射表
        SelectSort(temp_logArr, N, m, address_array);
        //用一个暂存数组保存被置乱后的象素数组
        int[] temp =new int[N];
        for (int j = 0; j < N; ++j)
            temp[j] = pixel[i][address_array[j]];
        //正式置乱原图
        System.arraycopy(temp, 0, pixel[i], 0, N);

        return logistic_array[N - 1];
    }

    //列解密接口
    void columnDecrypt_interface(int[][] pixel, double x1, int M, int N) {
			/*
			由于置乱的算法是每行置乱，所以不能直接进行列置乱
			要把当前二维数组转的行列转换
			即：原本的pixel[M][N] 转换成 pixel[N][M]
			然后用同样的算法进行置乱
			之后再进行一次行列转换即可
			*/
        ArrayFunctions af=new ArrayFunctions();
        int[][] temp =new int[N][M];
        //行列互换
        af.arr_change(pixel, temp, M, N);

        double x = x1;
        for (int i = 0; i < N; ++i) {
            x = columnDecrypt(temp, x, i, N, M);

        }

        int[][] temp2 =new int[M][N];
        //再次行列互换
        af.arr_change(temp, temp2, N, M);
        //正式对像素数组解密
        for (int i = 0; i < M; ++i) {
            System.arraycopy(temp2[i], 0, pixel[i], 0, N);
        }
    }

    //置乱
    void encrypt(int[][] pixel, double x1, int M, int N) {
        rowEncrypt_interface(pixel, x1, M, N);
        columnEncrypt_interface(pixel, x1, M, N);
    }

    //解密
    void decrypt(int[][] pixel, double x1, int M, int N) {
        columnDecrypt_interface(pixel, x1, M, N);
        rowDecrypt_interface(pixel, x1, M, N);

    }
}
