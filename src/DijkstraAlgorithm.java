import java.io.File;
import java.util.PriorityQueue;
import java.util.Scanner;

class GraphNode
{
    public
    int vertex;
    int time;
    GraphNode next;
    GraphNode(int vertex,int time)
    {
        this.vertex=vertex;
        this.next=null;
        this.time=time;
    }
    int getVertex() { return vertex;}
    int getTime() { return time;}
}
class DijkstraAlgorithm
{
    static final int maxSize = 9;
    GraphNode[] head=new GraphNode[maxSize];
    void getList()
    {
        int vertex1,vertex2,time;
        try
        {
            File fin=new File("graphdata\\numPath");
            if(!fin.exists())
            {
                System.out.print("\nfile doesn't exit");
                System.exit(0);
            }

            Scanner scanner = new Scanner(fin);
            while(scanner.hasNextInt())
            {
                vertex1=scanner.nextInt();
                vertex2=scanner.nextInt();
                time=scanner.nextInt();
                insert(vertex1,vertex2,time);
            }
        }
        catch (Exception e)
        {
            System.out.print("\nerror occurred: ");
            e.printStackTrace();
        }
    }


    void insert(int vertex1,int vertex2,int time)
    {
        GraphNode temp,current;

        temp=new GraphNode(vertex1,time);
        if(head[vertex2]==null)
        {
            head[vertex2]=temp;
        }
        else
        {
            current=head[vertex2];
            while (current.next!=null)
                current=current.next;
            current.next=temp;
        }

        temp=new GraphNode(vertex2,time);
        if(head[vertex1]==null)
        {
            head[vertex1]=temp;
        }
        else
        {
            current=head[vertex1];
            while (current.next!=null)
                current=current.next;
            current.next=temp;
        }
    }


    void display()
    {
        GraphNode current;
        for(int i=0;i<maxSize;i++)
        {
            current=head[i];
            System.out.print("\n"+i);
            while (current!=null)
            {
                System.out.print("->"+current.vertex+"("+current.time+")");
                current=current.next;
            }
        }
    }


    void dijkstra(int source)
    {

        GraphNode temp;
        int[] distance=new int[maxSize];
        for(int i=0;i<maxSize;i++)
            distance[i]=Integer.MAX_VALUE;

        distance[source]=0;

        PriorityQueue<GraphNode> pq=new PriorityQueue<>((v1, v2)-> v1.getTime()-v2.getTime());

        pq.add(new GraphNode(source,0));

        while (pq.size()>0)
        {
            GraphNode current=pq.poll();
            temp=head[current.vertex];
            while(temp!=null)
            {
                if( distance[current.vertex]+temp.time < distance[temp.vertex] )
                {
                    distance[temp.vertex]=distance[current.vertex]+temp.time;
                    pq.add(new GraphNode(temp.vertex,distance[temp.vertex]));
                }
                temp=temp.next;
            }

        }


        System.out.print("\n\ndistance to all vertex from "+source);
        System.out.print("\nvertex\t\tdistance");
        for(int i=0;i<maxSize;i++)
        {
            System.out.print("\n"+i+"\t\t"+distance[i]);
        }
    }



    public static void main(String [] args)
    {
        DijkstraAlgorithm d=new DijkstraAlgorithm();
        d.getList();
        d.display();
        d.dijkstra(0);
    }
}
