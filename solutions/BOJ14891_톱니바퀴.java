package solutions;

import java.util.*;
import java.io.*;
public class BOJ14891_톱니바퀴 {
    static String[] wheel; // 톱니바퀴 정보
    static int[] index; // 12 시 방향 index
    static final int MAX = 8;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wheel = new String[5];
        for(int i = 1; i < 5; i++) wheel[i] = br.readLine();
        index = new int[5];
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < K; i++){
            Queue<int[]> que = new LinkedList<>(); // {wheelNum, dir}
            st = new StringTokenizer(br.readLine());
            que.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            boolean[] visited = new boolean[5];
            visited[que.peek()[0]] = true;
            while(!que.isEmpty()){
                int[] now = que.poll();
                int w = now[0];
                int dir = now[1];
                int left = (index[w] - 2 < 0) ? MAX + index[w] - 2 : index[w] - 2;
                int right  = (index[w] + 2) % MAX;
                if(w + 1 < 5 && !visited[w + 1]){ // 우측 톱니와 비교
                    int tmp = (index[w + 1] - 2 < 0) ? MAX + index[w + 1] - 2 : index[w + 1] - 2;
                    visited[w + 1] = true;
                    if(wheel[w + 1].charAt(tmp) != wheel[w].charAt(right)) que.offer(new int[]{w + 1, dir * -1});
                }
                if(w - 1 > 0 && !visited[w - 1]){ // 좌측 톱니와 비교
                    int tmp = (index[w - 1] + 2) % MAX;
                    visited[w - 1] = true;
                    if(wheel[w - 1].charAt(tmp) != wheel[w].charAt(left)) que.offer(new int[]{w - 1, dir * -1});
                }
                index[w] += dir * -1;
                if(index[w] >= MAX) index[w] %= MAX;
                else if(index[w] < 0) index[w] += MAX;
            }
        }
        int score = 0;
        for (int i = 1; i < 5; i++)
            if(wheel[i].charAt(index[i]) == '1')
                score += Math.pow(2, i - 1);
        System.out.println(score);
    }
}
