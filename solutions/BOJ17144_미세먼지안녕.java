package solutions;

import java.io.*;
import java.util.*;

public class BOJ17144_미세먼지안녕 {
    static int R, C, T;
    static int[][] map;
    static int start, end; // 공기청정기 위치
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) end = i;
            }
        }
        start = end - 1;
        for(int i = 0; i < T; i++) spread();
        int result = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++)
                if(map[i][j] > 0) result += map[i][j];
        }
        System.out.println(result);
    }
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    public static void spread(){
        int[][] tmp = new int[R][C];
        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] >= 5){
                    int t = 0;
                    int add = map[i][j] / 5;
                    for(int k = 0; k < 4; k++){ // 인접한 4 방향으로 확산
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr >= 0 && nc >= 0 && nr < R && nc < C && map[nr][nc] != -1){
                            tmp[nr][nc] += add;
                            t -= add;
                        }
                    }
                    tmp[i][j] += t;  // (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수)
                }
            }
        }
        for(int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if(map[i][j] != -1) map[i][j] += tmp[i][j];

        for(int i = start - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for(int i = end + 1; i < R - 1; i++) map[i][0] = map[i + 1][0];
        for(int j = 0; j < C - 1; j++){
            map[0][j] = map[0][j + 1];
            map[R - 1][j] = map[R - 1][j + 1];
        }
        for(int i = 0; i < start; i++) map[i][C - 1] = map[i + 1][C - 1];
        for(int i = R - 1; i > end; i--) map[i][C - 1] = map[i - 1][C - 1];
        for(int j = C - 1; j > 1; j--){
            map[start][j] = map[start][j - 1];
            map[end][j] = map[end][j - 1];
        }
        map[start][1] = map[end][1] = 0;

    }
}
