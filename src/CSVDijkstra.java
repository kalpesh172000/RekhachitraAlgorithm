import java.io.File;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Scanner;
import com.opencsv.CSVReader;


class Node
{
    public
    int vertex;
    int time;
    Node next;
    Node(int vertex, int time)
    {
        this.vertex=vertex;
        this.next=null;
        this.time=time;
    }
    int getVertex() { return vertex;}
    int getTime() { return time;}
}


public class CSVDijkstra
{
    static final int maxSize = 9;
    Node[] head=new Node[maxSize];
    void getList()
    {
        int vertex1,vertex2,time;
        CSVReader reader = null;
        try
        {
            reader = new CSVReader(new FileReader("graphdata\\graphdata.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null)
            {
                vertex1=Integer.parseInt(nextLine[0]);
                vertex2=Integer.parseInt(nextLine[1]);
                time=Integer.parseInt(nextLine[2]);
                System.out.print("\n"+vertex1+" "+vertex2+" "+time);
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
        Node temp,current;

        temp=new Node(vertex1,time);
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

        temp=new Node(vertex2,time);
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
        Node current;
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

        Node temp;
        int[] distance=new int[maxSize];
        for(int i=0;i<maxSize;i++)
            distance[i]=Integer.MAX_VALUE;

        distance[source]=0;

        PriorityQueue<Node> pq=new PriorityQueue<>((v1, v2)-> v1.getTime()-v2.getTime());

        pq.add(new Node(source,0));

        while (pq.size()>0)
        {
            Node current=pq.poll();
            temp=head[current.vertex];
            while(temp!=null)
            {
                if( distance[current.vertex]+temp.time < distance[temp.vertex] )
                {
                    distance[temp.vertex]=distance[current.vertex]+temp.time;
                    pq.add(new Node(temp.vertex,distance[temp.vertex]));
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
        CSVDijkstra d=new CSVDijkstra();
        d.getList();
        d.display();
        d.dijkstra(0);
    }
}