package practice;

public class Sample {


    int i=9;
//   public Sample()
//   {
////       i=6;
//   }
//
//   public Sample(int i)
//   {
////       this.i=i;
//   }

    public static void main(String[] args)
    {

        Sample sample = new Sample();
        System.out.println(sample.initialise().i);
    }

    //using return we are calling the method
//    public int initialise()
//    {
//       i = 12;
//       return i;
//    }

    //if i want call class instance using initialise method i will do like this
    public Sample initialise()
    {
//       System.out.println("hello");
       return this;
    }


}
