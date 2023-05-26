import java.util.Scanner;

public class LinkedList
{
    Node head;
    int count;

    static class Node
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }

    public static void insert_end(LinkedList list, int data)
    {
        Node temp=new Node(data);
        if(list.head==null)
        {
            list.head=temp;
        }
        else
        {
            Node last=list.head;
            while (last.next!=null)
            {
                last=last.next;
            }
            last.next=temp;
        }
        list.count++;
        display(list);
    }


    public static void insert_beg(LinkedList list, int data)
    {
        Node temp=new Node(data);
        if(list.head==null)
        {
            list.head=temp;
        }
        else
        {
            temp.next=list.head;
            list.head=temp;
        }
        list.count++;
        display(list);
    }

    public static void insert_pos(LinkedList list, int data,int pos)
    {
        if(pos < 1 || pos > list.count+1)
        {
            System.out.print("\nposition out of range");
            return;
        }

        Node temp=new Node(data);

        if(pos==1)
        {
            temp.next=list.head;
            list.head=temp;
        }
        else
        {
            Node last,prev=null;
            last=list.head;
            for(int i=1;i<pos;i++)
            {
                prev=last;
                last=last.next;
            }
            prev.next=temp;
            temp.next=last;
        }
        list.count++;
        display(list);
    }


    public static void display(LinkedList list)
    {
        Node temp;
        temp=list.head;
        if(temp==null)
            System.out.print("\nlinked list is empty");
        else
        {
            System.out.print("\ndata is as follows: ");
            while (temp!=null)
            {
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
        }
    }


    public static void delete(LinkedList list, int data)
    {
        Node parent,last;
        if (list.head==null)
        {
            System.out.print("\nlist is empty");
            return;
        }
        list.count--;
        if(list.head.data==data)
        {
            list.head=list.head.next;
            display(list);
            return;
        }
        parent=list.head;
        last=list.head.next;
        while (last!=null)
        {
            if(last.data==data)
            {
                parent.next=last.next;
                display(list);
                return;
            }
            parent=last;
            last=last.next;
        }
        list.count++;
        System.out.print("\ngiven number doesn't exist in the list");
    }


    public static void sort(LinkedList list)
    {
        Node parent,last;
        int data;
        for(int i=0;i< list.count;i++)
        {
            last=list.head;
            for (int j=0;j<list.count-i-1;j++)
            {
                parent=last;
                last=last.next;
                if(parent.data>last.data)
                {
                    data=parent.data;
                    parent.data= last.data;
                    last.data=data;
                }
            }
        }
        display(list);
    }


    public static void search(LinkedList list,int data)
    {
        Node last=list.head;
        if(last==null)
        {
            System.out.print("\nlist is emtpy");
            return;
        }
        while (last!=null)
        {
            if(last.data==data)
            {
                System.out.print("\nnumber exits in the list");
                return;
            }
            last=last.next;
        }
        System.out.print("\nnumber doesn't exit in the list");
    }


    public static void update(LinkedList list, int data, int pos)
    {
        if(pos < 1 || pos > list.count)
        {
            System.out.print("\nposition out of range");
            return;
        }

        if(pos==1)
        {
            list.head.data=data;
        }
        else
        {
            Node last,prev;
            last=list.head;
            for(int i=1;i<pos;i++)
            {
                last=last.next;
            }
            last.data=data;
        }
        display(list);
    }


    public static void reverse(LinkedList list)
    {
        Node last=list.head,parent=null,temp;
        while (last!=null)
        {
            temp=last.next;
            last.next=parent;
            parent=last;
            last=temp;
        }
        list.head=parent;
        display(list);
    }


    public static void main(String[] args)
    {
        LinkedList list=new LinkedList();
        Scanner scan=new Scanner(System.in);

        int ch,data,pos;
        insert_end(list,75);
        insert_end(list,35);
        insert_end(list,65);
        insert_end(list,25);
        insert_end(list,55);
        while (true)
        {
            System.out.println("\n\n-------------------------------------------------------\nMENU");
            System.out.println("1. insert at beginning");
            System.out.println("2. insert at end");
            System.out.println("3. insert at position");
            System.out.println("4. delete");
            System.out.println("5. sort");
            System.out.println("6. search");
            System.out.println("7. update");
            System.out.println("8. reverse");
            System.out.println("9. display");
            System.out.println("10. exit");
            System.out.print("Enter your choice: ");
            ch= scan.nextInt();
            switch (ch) {
                case 1 ->
                {
                    System.out.print("\nenter the number you want to insert: ");
                    data = scan.nextInt();
                    insert_beg(list, data);
                }
                case 2 ->
                {
                    System.out.print("\nenter the number you want to insert: ");
                    data = scan.nextInt();
                    insert_end(list, data);
                }
                case 3 ->
                {
                    System.out.print("\nenter the number you want to insert: ");
                    data = scan.nextInt();
                    System.out.print("\nenter the position at which you want to insert the number: ");
                    pos = scan.nextInt();
                    insert_pos(list, data, pos);
                }
                case 4 ->
                {
                    System.out.print("\nenter the number you want to delete: ");
                    data = scan.nextInt();
                    delete(list, data);
                }
                case 5 -> sort(list);
                case 6 ->
                {
                    System.out.print("\nenter the number you want to search: ");
                    data = scan.nextInt();
                    search(list, data);
                }
                case 7 ->
                {
                    System.out.print("\nenter the position you want to update: ");
                    pos = scan.nextInt();
                    System.out.print("\nenter the new number at position " + pos + ": ");
                    data = scan.nextInt();
                    update(list, data, pos);
                }
                case 8 -> reverse(list);
                case 9 -> display(list);
                case 10 ->
                {
                    return;
                }
                default -> System.out.print("\nenter correct choice !!!");
            }
        }
    }
}
