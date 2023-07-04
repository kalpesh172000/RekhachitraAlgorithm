import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MultipathGraph
{
    private static int vertices = 40;
    private static Map<List<String>,Integer> result = new HashMap<>();
    private static ArrayList<String> busStop=new ArrayList<>();

    private static final String CSV_FILE_PATH = "graphdata\\edgess.csv";

    private static Map<String, Map<String, Integer>> createGraphFromCSV() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            //reader.readLine(); // Skip the header line // we don't need to skip header line

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String source = data[0];
                busStop.add(source);
                Map<String, Integer> neighbors = new HashMap<>();

                for (int i = 1; i < data.length - 1; i += 2) {
                    String destination = data[i];
                    int cost = Integer.parseInt(data[i + 1]);
                    neighbors.put(destination, cost);
                }

                graph.put(source, neighbors);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private static void getAllPath(String source,String destination,Map<String, Map<String, Integer>> graph)
    {
        boolean[] isVisited = new boolean[vertices];
        int distance=0;
        ArrayList<String> pathList= new ArrayList<>();
        pathList.add(source);
        getAllPathsUtil(source, destination, isVisited, pathList, distance, graph);
    }

    private static void getAllPathsUtil(String source, String destination,
                                        boolean[] isVisited,
                                        List<String> localpathList,
                                        int distance,
                                        Map<String, Map<String, Integer>> graph)
    {
        if(source.equals(destination))
        {
            System.out.print(localpathList + " : " + distance + "\n");
            result.put(new ArrayList<>(localpathList),distance);
            return;
        }

        isVisited[busStop.indexOf(source)]=true;

        Map<String, Integer> currentNode;
        currentNode=graph.get(source);
        for(Map.Entry<String, Integer> loc: currentNode.entrySet())
        {
            if(!isVisited[busStop.indexOf(loc.getKey())])
            {
                boolean isBranch = graph.get(loc.getKey()).size()>2;
                if(isBranch || Objects.equals(loc.getKey(), destination))
                {
                    localpathList.add(loc.getKey());
                    distance=distance+loc.getValue();
                    getAllPathsUtil(loc.getKey(),destination,isVisited,localpathList,distance,graph);
                    distance=distance-loc.getValue();
                    localpathList.remove(loc.getKey());
                }
                else
                {
                    distance=distance+loc.getValue();
                    getAllPathsUtil(loc.getKey(),destination,isVisited,localpathList,distance,graph);
                    distance=distance-loc.getValue();
                }

            }
        }
        isVisited[busStop.indexOf(source)]=false;
    }

    public static void main(String[] args)
    {
        Map<String, Map<String, Integer>> graph = createGraphFromCSV();
        String source = "Sinnar Phata";
        String destination = "K K Wagh";
        getAllPath(source,destination,graph);
    }
}
