package com.joker.test;

import edu.princeton.cs.algs4.Digraph;
import org.junit.Assert;

import java.util.Arrays;

public class FindShortestPath
{
    static int minDis = Integer.MAX_VALUE;
    static int[] edgeTo;
    static int[] visited;

    public static int shortestPath(Digraph g)
    {
        int vSize = g.V();
        edgeTo = new int[vSize];
        //-1表示目前到点v的路径不存在
        Arrays.fill(edgeTo, -1);

        visited = new int[vSize];

        return shortestPathHelper(g, 0, 0, minDis);
    }

    public static int shortestPathHelper(Digraph g, int vertex, int parent, int minDis)
    {
        visited[vertex] = 1;
        for(int neighbor : g.adj(vertex))
        {
            //表明找到了环
            int currDis = 1;
            if(visited[neighbor] == 1 && neighbor != parent)
            {
                //回溯统计路径
                int curr = neighbor;
                int preFrom = edgeTo[neighbor];
                edgeTo[neighbor] = vertex;
                while(edgeTo[curr] != neighbor && edgeTo[curr] != -1)
                {
                    currDis++;
                    curr = edgeTo[curr];
                }
                edgeTo[neighbor] = preFrom;
                if(minDis > currDis)
                    minDis = currDis;
            }
            else
            {
                int preFrom = edgeTo[neighbor];
                edgeTo[neighbor] = vertex;
                currDis = shortestPathHelper(g, neighbor, vertex, minDis);
                if(minDis > currDis)
                    minDis = currDis;
                //回退后的操作
                edgeTo[neighbor] = preFrom;
            }
        }
        return minDis;
    }
    public static void main(String[] args)
    {
        Digraph g = new Digraph(8);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,0);
        g.addEdge(4,5);
        g.addEdge(5,3);
        g.addEdge(0,6);
        g.addEdge(6,7);
        g.addEdge(7,4);

        System.out.println(shortestPath(g));
        Assert.assertEquals(3, shortestPath(g));

    }

}
