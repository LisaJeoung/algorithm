package solutions;

import java.io.*;
import java.util.*;
public class BOJ3190_뱀 {
    public static int N, direction;
    public static int[][] map, snake;
    public static int[] dr = {0, 0, 1, 0, -1}; // 동 남 서 북 (시계 방향)
    public static int[] dc = {0, 1, 0, -1, 0};
    public static int[] tail, head;
    public static int time;
    public static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1]; // 사과 표시
        snake = new int[N + 1][N+ 1]; // 뱀 위치 기록
        StringTokenizer st;
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
        direction = 1; // 동
        tail = new int[]{1, 1};
        head = new int[]{1, 1};
        snake[1][1] = 1; // 다음 방향 기록
        time = 0;
        flag = true;
        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            if (!flag) continue;
            int second = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            for (int j = time + 1; j <= second; j++) {
                if(!flag) break;
                if(j == second) {
                    int newDir = -1;
                    if (dir.equals("L")) newDir = (direction > 1) ? direction - 1 : 4;
                    else if (dir.equals("D")) newDir = (direction < 4) ? direction + 1 : 1;
                    move(newDir);
                    direction = newDir;
                }
                else move(-1);
            }
        }
        while(flag)  move(-1);
        System.out.println(time);
    }
    public static void move(int next){
        time++;
        int nr = head[0] + dr[direction];
        int nc = head[1] + dc[direction];
        if(nr < 1 || nc < 1 || nr > N || nc > N || snake[nr][nc] != 0){ // 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
            flag = false;
            return;
        }
        head = new int[]{nr, nc}; // 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
        snake[nr][nc] = (next == -1) ? direction : next;
        if(map[nr][nc] == 0){ // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
            int row = tail[0];
            int col = tail[1];
            tail[0] += dr[snake[row][col]];
            tail[1] += dc[snake[row][col]];
            snake[row][col] = 0;
        }else if(map[nr][nc] == 1){ // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
            map[nr][nc] = 0;
        }
    }
}
