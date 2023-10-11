package solutions;

import java.io.*;
import java.util.*;

public class BOJ13458_시험감독 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long result = N;
        for(int a : arr){
            a -= B;
            if(a > 0)
                result += (a % C == 0) ? a / C : a / C + 1;
        }
        System.out.println(result);
    }
}
