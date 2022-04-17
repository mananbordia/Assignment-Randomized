#include <bits/stdc++.h>
using namespace std;
const int N = 1e6;

/**
 * Need A structure that would take a graph and return max connected component.
 * Find 1000 values for p;
 * ! N is fixed 
 * @return int 
 */

struct Graph {
    int n;
    vector<vector<int>> adj;
    vector<int> vis;
    Graph(int _n, vector<vector<int>> &_adj) : n(_n), adj(_adj), vis(n) {};
    
    int getSize(int i) {
        queue<int> q;
        vis[i] = 1;
        q.push(i);
        int size = 1;
        while(!q.empty()) {
            int u = q.front();
            q.pop();
            for(int v : adj[u]) {
                if(vis[v] == 0) {
                    q.push(v);
                    vis[v] = 1;
                    size++;
                }
            }
        }
        return size;
    }

    int getMaxComponent() {
        int answer = 0;
        for(int i = 0; i < n; ++i) {
            if(vis[i] == 0) {
                int size = getSize(i);
                answer = max(answer, size);
            }
        }
        return answer; } 
};

int getNumber(int a, int b) {
    
    mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
    int num = uniform_int_distribution<int>(a, b - 1) (rng);
    return num;
}

struct GraphBuilder {
    int n;
    int expectedNumberOfEdges;

    GraphBuilder(int _p) : expectedNumberOfEdges(_p), n(N) {};

    vector<pair<int,int>> getEdges() {
        vector<pair<int,int>> edges;
        while(edges.size() < expectedNumberOfEdges) {
            int a = getNumber(0, n);
            int b = getNumber(0, n);
            if (a == b)
                continue;
            pair<int,int> p = minmax(a, b);
            edges.emplace_back(p);
        }
        return edges;
    }
    Graph getGraph() {
        vector<vector<int>> adj(n);
        vector<pair<int,int>> edges = getEdges();
        for(auto &[u, v] : edges) {
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
        return Graph(n, adj);
    }
};

int main() {
    int dataSize = 50;
    for(int i = 1; i <= dataSize; ++i) {
        int expectedEdges = i * (N / dataSize);
        Graph G = GraphBuilder(expectedEdges).getGraph();
        cout << setprecision(14) << fixed << 1.0 * 2 * expectedEdges / (1LL * N * N) << "," << G.getMaxComponent() << "\n";
    }
    return 0; 
}