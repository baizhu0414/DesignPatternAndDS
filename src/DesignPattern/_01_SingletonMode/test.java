package DesignPattern._01_SingletonMode;

import java.util.Scanner;

public class test {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        XiaoMing_ShoppingCart instance = XiaoMing_ShoppingCart.getInstance();

        for(int i=0; i<3; i++) {
            String str = sc.next();
            int val = sc.nextInt();
            instance.add(new Obj(str, val));
        }
        instance.PrintCartList();

        sc.close();
    }
}
