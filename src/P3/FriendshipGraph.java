package P3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
/*
 * 求解无向图的单源最短路径
*/

import org.junit.Test;
public class FriendshipGraph {
	public interface Algorithm {
	    /**
	     * 执行算法
	     */
	    void perform(Graph g, String source);
	    /**
	     * 得到路径
	     */
	    Map<String, String> getPath();
	}
	public class Graph {
	    // 图的起点
	    private String firstVertex;
	    // 邻接表
	    private Map<String, List<String>> adj = new HashMap<>();
	    // 遍历算法
	    private Algorithm algorithm;
	    public Graph(Algorithm algorithm) { //策略模式，将Graph与算法分离
	        this.algorithm = algorithm;
	    }

	    public void setFirstVertex(String firstVertex) {
	        this.firstVertex = firstVertex;
	    }

	    /**
	     * 执行算法
	     */
	    public void done() {
	        algorithm.perform(this, firstVertex);
	    }
	    /**
	     * 得到从起点到{@code vertex}点的最短路径
	     * @param vertex
	     * @return
	     */
	    public Stack<String> findPathTo(String name) {
	        Stack<String> stack = new Stack<>();
	        stack.add(name);
	        Map<String, String> path = algorithm.getPath();
	        for (String location = path.get(name) ; !location.equals(firstVertex) ; location = path.get(location)) {
	            stack.push(location);
	        }
	        stack.push(firstVertex);
	        return stack;
	    }
	    /**
	     * 添加一条边
	     */
	    public void addEdge(String name, String fname) {
	        if (firstVertex == null) {
	            firstVertex = name;
	        }
	        adj.get(name).add(fname);
	        adj.get(fname).add(name);
	    }
	    /**
	     * 添加一个顶点
	     */
	    public void addVertex(String name) {
	        adj.put(name, new ArrayList<String>());
	    }

	    public Map<String, List<String>> getAdj() {
	        return adj;
	    }
	}
	public class BroadFristSearchAlgorithm implements Algorithm{
	    // 保存已经访问过的地点
	    private List<String> visitedVertex;
	    // 保存最短路径
	    private Map<String, String> path;

	    @Override
	    public void perform(Graph g, String sourceVertex) {
	        if (null == visitedVertex) {
	            visitedVertex = new ArrayList<>();
	        }
	        if (null == path) {
	            path = new HashMap<>();
	        }
	        BFS(g, sourceVertex);
	    }

	    @Override
	    public Map<String, String> getPath() {
	        return path;
	    }

	    private void BFS(Graph g, String sourceVertex){
	        Queue<String> queue = new LinkedList<>();
	        //标记起点
	        visitedVertex.add(sourceVertex);
	        // 起点入列
	        queue.add(sourceVertex);
	        while(!queue.isEmpty()){
	            String ver = queue.poll();  //返回队列头部，或null，如果队列为空
	            List<String> toBeVisitedVertex = g.getAdj().get(ver);
	            for (String v : toBeVisitedVertex) {
	                if (!visitedVertex.contains(v)) {
	                    visitedVertex.add(v);
	                    path.put(v, ver);
	                    queue.add(v);   //后添加的要等前面的所有距离为currDist的顶点都被处理之后才被处理
	                }
	            }
	        }
	    }
	}


}