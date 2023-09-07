package practice;

public class ControlStructures {


    enum myChar{
        A,
        B
    }
    public void ifElseCheck()
    {
        boolean flag=true;
        String str="Hello";
        if (flag & str.equalsIgnoreCase("Hello"))
        {
            System.out.println("am right");
        }else {
            System.out.println("am wrong");
        }
    }

    public void forLoopCheck()
    {
        for (int i=0;i<5;i++)
        {
            System.out.println(("i is :"+i));
            if(i==2)
            {
                break;
            }
        }
    }

    public void forEachLoopCheck()
    {
        String[] s={"Hi","hello","bye","cya"};
        for(String str : s)
        {
          System.out.print(str+" ");
        }
    }

    public void whileCheck()
    {
        int i=5;
        while (i>0)
        {
            System.out.println("\nGood");
            i--;
            if(i==2)
            {
                break;
            }
        }
    }

    public void switchCheck()
    {

        myChar nowChar = myChar.A;
        switch (nowChar)
        {
            case A:System.out.println(nowChar);
            break;
            case B:System.out.println(nowChar);
            break;
        }
    }

    public static void main(String[] args)
    {

        ControlStructures control=new ControlStructures();
        control.ifElseCheck();
        control.forLoopCheck();
        control.forEachLoopCheck();
        control.whileCheck();
        control.switchCheck();
    }
}
