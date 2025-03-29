package DS;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int id; // 边的输入序号
    int u, v; // 连接的两个城市
    int weight; // 道路的代价
    int isRequired; // 是否是必选边 (1表示必选)

    public Edge(int id, int u, int v, int weight, int isRequired) {
        this.id = id;
        this.u = u;
        this.v = v;
        this.weight = weight;
        this.isRequired = isRequired;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
}

class UnionFind {
    /**
     * 并查集工具类：用于管理连通性
     */
    private int[] parent;

    public UnionFind(int size) {
        parent = new int[size + 1]; // 城市编号从1开始
        for (int i = 1; i <= size; i++) {
            parent[i] = i; // 初始时每个城市自成一派
        }
    }

    // 查找根节点（带路径压缩）
    public int findRoot(int x) {
        if (parent[x] != x) {
            parent[x] = findRoot(parent[x]);
        }
        return parent[x];
    }

    // 合并两个集合（按秩合并）
    public boolean union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX == rootY)
            return false; // 已经连通
        parent[rootY] = rootX;
        return true;
    }
}

public class Kruskal {
    public static void main(String[] args) throws IOException {
        // ----------------- 输入处理 -----------------
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int cityCount = Integer.parseInt(firstLine[0]);
        int roadPlanCount = Integer.parseInt(firstLine[1]);

        List<Edge> allEdges = new ArrayList<>(); // 存储所有道路
        List<Edge> requiredEdges = new ArrayList<>(); // 必选道路
        List<Edge> optionalEdges = new ArrayList<>(); // 可选道路

        // 读取所有道路信息
        for (int planId = 1; planId <= roadPlanCount; planId++) {
            String[] line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);
            int p = Integer.parseInt(line[3]);

            Edge edge = new Edge(planId, u, v, w, p);
            allEdges.add(edge);

            if (p == 1) {
                requiredEdges.add(edge);
            } else {
                optionalEdges.add(edge);
            }
        }

        // ----------------- 核心逻辑处理 -----------------
        UnionFind uf = new UnionFind(cityCount);
        List<Integer> selectedPlans = new ArrayList<>();

        // 步骤1：处理必选边（必须全部加入且不能形成环）
        for (Edge edge : requiredEdges) {
            if (!uf.union(edge.u, edge.v)) { // 如果必选边中两个城市已经连通
                System.out.println(-1); // u、v已经连通，再加入一条边就形成环了
                return;
            }
            selectedPlans.add(edge.id); // 记录选中的必选边
        }

        // 步骤2：处理可选边（按代价从小到大处理）
        Collections.sort(optionalEdges); // 可选边按权重排序
        for (Edge edge : optionalEdges) {
            if (uf.union(edge.u, edge.v)) { // 成功合并时记录
                selectedPlans.add(edge.id);
            }
        }

        // ----------------- 连通性检查 -----------------
        int root = uf.findRoot(1); // 检查所有城市是否连通
        for (int city = 2; city <= cityCount; city++) {
            if (uf.findRoot(city) != root) {
                System.out.println(-1);
                return;
            }
        }

        // ----------------- 输出结果 -----------------
        // 按输入顺序排序结果（因为处理时已按顺序记录）
        selectedPlans.sort(Comparator.naturalOrder()); // 此处排序是因为处理顺序可能打乱原始顺序
        System.out.println(selectedPlans.size());
        for (int planId : selectedPlans) {
            System.out.print(planId + " ");
        }
    }
}