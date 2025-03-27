package DesignPattern._02_FactoryMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class test {
    /**
     * 尝试使用反射实现工厂模式
     * @param args
     */
    public static void main(String[] args) {
        BlocksFactory factory = new BlocksFactoryImpl();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String str = sc.next();// 积木Blocks类全称DesignPattern._02_FactoryMode.CircleBlocks
            int val = sc.nextInt();
            try {
                Class<Blocks> cls = (Class<Blocks>) Class.forName(str);
                Blocks block = factory.createBlocks(cls, str, val);
                block.blockPrint(str, val);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}

// 抽象工厂类
abstract class BlocksFactory {
    public abstract Blocks createBlocks(Class<Blocks> c, String str, int num);
}

class BlocksFactoryImpl extends BlocksFactory {

    @Override
    public Blocks createBlocks(Class<Blocks> c, String str, int num) {
        /**
         * 此方法构造对象
         */
        try {
            // 反射带有两个参数的c类的构造函数
            Constructor<Blocks> con = c.getConstructor(String.class, int.class);
            return con.newInstance(str, num);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// 定义积木接口
interface Blocks {
    void blockPrint(String str, int num);
}

// 圆形积木
class CircleBlocks implements Blocks {
    @SuppressWarnings("unused")
    private String str;
    private int num;

    public CircleBlocks(String str, int num) {
        this.str = str;
        this.num = num;
    }

    public void blockPrint(String str, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("生产一个 " + str + " Block");
        }
    }
}

// 方形积木
class SquareBlocks implements Blocks {

    private String str;
    private int num;

    public SquareBlocks(String str, int num) {
        this.str = str;
        this.num = num;
    }

    public void blockPrint(String str, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("生产一个 " + str + " Block");
        }
    }
}