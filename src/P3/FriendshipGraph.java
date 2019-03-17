package P3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
/*
 * �������ͼ�ĵ�Դ���·��
*/

import org.junit.Test;
public class FriendshipGraph {
	public interface Algorithm {
	    /**
	     * ִ���㷨
	     */
	    void perform(Graph g, String source);
	    /**
	     * �õ�·��
	     */
	    Map<String, String> getPath();
	}
	public class Graph {
	    // ͼ�����
	    private String firstVertex;
	    // �ڽӱ�
	    private Map<String, List<String>> adj = new HashMap<>();
	    // �����㷨
	    private Algorithm algorithm;
	    public Graph(Algorithm algorithm) { //����ģʽ����Graph���㷨����
	        this.algorithm = algorithm;
	    }

	    public void setFirstVertex(String firstVertex) {
	        this.firstVertex = firstVertex;
	    }

	    /**
	     * ִ���㷨
	     */
	    public void done() {
	        algorithm.perform(this, firstVertex);
	    }
	    /**
	     * �õ�����㵽{@code vertex}������·��
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
	     * ���һ����
	     */
	    public void addEdge(String name, String fname) {
	        if (firstVertex == null) {
	            firstVertex = name;
	        }
	        adj.get(name).add(fname);
	        adj.get(fname).add(name);
	    }
	    /**
	     * ���һ������
	     */
	    public void addVertex(String name) {
	        adj.put(name, new ArrayList<String>());
	    }

	    public Map<String, List<String>> getAdj() {
	        return adj;
	    }
	}
	public class BroadFristSearchAlgorithm implements Algorithm{
	    // �����Ѿ����ʹ��ĵص�
	    private List<String> visitedVertex;
	    // �������·��
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
	        //������
	        visitedVertex.add(sourceVertex);
	        // �������
	        queue.add(sourceVertex);
	        while(!queue.isEmpty()){
	            String ver = queue.poll();  //���ض���ͷ������null���������Ϊ��
	            List<String> toBeVisitedVertex = g.getAdj().get(ver);
	            for (String v : toBeVisitedVertex) {
	                if (!visitedVertex.contains(v)) {
	                    visitedVertex.add(v);
	                    path.put(v, ver);
	                    queue.add(v);   //����ӵ�Ҫ��ǰ������о���ΪcurrDist�Ķ��㶼������֮��ű�����
	                }
	            }
	        }
	    }
	}


}