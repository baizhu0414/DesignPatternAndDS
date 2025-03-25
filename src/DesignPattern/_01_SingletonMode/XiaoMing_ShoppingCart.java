package DesignPattern._01_SingletonMode;

import java.util.ArrayList;
import java.util.List;

// 【设计模式专题之单例模式】1.小明的购物车
//  https://kamacoder.com/problempage.php?pid=1074
class Obj {
    String str;
    int val;

    Obj(String str, int val) {
        this.str = str;
        this.val = val;
    }

    @Override
    public String toString() {
        return str + " " + val;
    }

}

public class XiaoMing_ShoppingCart {
    private static XiaoMing_ShoppingCart instance;
    List<Obj> cart_list;

    // 懒汉模式+双重检验锁
    public static synchronized XiaoMing_ShoppingCart getInstance() {
        if (instance == null) {
            synchronized(XiaoMing_ShoppingCart.class) {
                if (instance == null) {
                    instance = new XiaoMing_ShoppingCart();
                }
            }
        }
        return instance;
    }

    // 私有构造函数
    private XiaoMing_ShoppingCart() {
    }

    public boolean add(Obj obj) {
        if (cart_list == null) {
            cart_list = new ArrayList<>();
        }
        return cart_list.add(obj);
    }

    public void PrintCartList() {
        for (int i = 0; i < cart_list.size(); i++) {
            System.out.println(cart_list.get(i).toString());
        }
    }

}

