package solutions;

import java.util.*;
import java.io.*;

public class BOJ14499_주사위굴리기 {
    public static int[] dr = {0, 0, 0, -1, 1};
    public static int[] dc = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder();
        int north = 0, west = 0, east = 0, south = 0;
        int bottom = 0, top = 0;
        while(st.hasMoreTokens()){
            int direction = Integer.parseInt(st.nextToken());
            int nr = x + dr[direction];
            int nc = y + dc[direction];
            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
            int tmp = bottom;
            switch (direction){
                case 1 : // 동
                    bottom = east;
                    east = top;
                    top = west;
                    west = tmp;
                    break;
                case 2 : // 서
                    bottom = west;
                    west = top;
                    top = east;
                    east = tmp;
                    break;
                case 3 : // 북
                    bottom = north;
                    north = top;
                    top = south;
                    south = tmp;
                    break;
                case 4 : // 남
                    bottom = south;
                    south = top;
                    top = north;
                    north = tmp;
                    break;
            }
            if(map[nr][nc] == 0) map[nr][nc] = bottom;
            else{
                bottom = map[nr][nc];
                map[nr][nc] = 0;
            }
            x = nr;
            y = nc;
            result.append(top).append("\n");
        }
        System.out.print(result);
    }
}
