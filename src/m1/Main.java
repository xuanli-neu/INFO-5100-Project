package m1;
import dataproto.*;
import m1.team14.MainPage;
import m1.team3.Dealers;

public class Main {
  public static void main(String args[]){
    Dealer t1 = new Dealer();
    t1.setId("u1");
    t1.setPassword("p1");
    t1.setPhone("123456789");
    t1.setEmailId("u1@gmail.com");
    Dealer t2 = new Dealer();
    t2.setId("u2");
    t2.setPassword("p2");
    t2.setPhone("123456789");
    t2.setEmailId("u1@gmail.com");
    Dealer t3 = new Dealer();
    t3.setId("u3");
    t3.setPassword("p3");
    t3.setPhone("123456789");
    t3.setEmailId("u1@gmail.com");
    Dealer t4 = new Dealer();
    t4.setId("u4");
    t4.setPassword("p4");
    t4.setPhone("123456789");
    t4.setEmailId("u1@gmail.com");
    Dealer t5 = new Dealer();
    t5.setId("u5");
    t5.setPassword("p5");
    t5.setPhone("123456789");
    t5.setEmailId("u1@gmail.com");
    Dealer t6 = new Dealer();
    t6.setId("u6");
    t6.setPassword("p6");
    t6.setPhone("123456789");
    t6.setEmailId("u1@gmail.com");
    Dealer t7 = new Dealer();
    t7.setId("u7");
    t7.setPassword("p7");
    t7.setPhone("123456789");
    t7.setEmailId("u1@gmail.com");
    Dealer t8 = new Dealer();
    t8.setId("u8");
    t8.setPassword("p8");
    t8.setPhone("123456789");
    t8.setEmailId("u1@gmail.com");
    Dealer t0 = new Dealer();
    t0.setId("ch");
    t0.setPassword("ch");
    t0.setPhone("123456789");
    t0.setEmailId("u1@gmail.com");

    Dealers.getInstance().add(t1);
    Dealers.getInstance().add(t2);
    Dealers.getInstance().add(t3);
    Dealers.getInstance().add(t4);
    Dealers.getInstance().add(t5);
    Dealers.getInstance().add(t6);
    Dealers.getInstance().add(t7);
    Dealers.getInstance().add(t8);
    Dealers.getInstance().add(t0);
    new MainPage();
  }
}
