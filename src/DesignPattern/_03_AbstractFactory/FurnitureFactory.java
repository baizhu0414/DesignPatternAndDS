package DesignPattern._03_AbstractFactory;

/**
 * 应用：不同类，并且每个类有多个系列对象实现.
 * 
 * 如：每个工厂能生产一种类型（Modern或Classic）的多种类家具。
 * 使用抽象工厂模式来创建与不同数据库的连接对象。
 */
public class FurnitureFactory {
    public static void main(String[] args) {
        Factory modernFac = new ModernFactory();
        Chair modernChair = modernFac.produceChair();
        Sofa modernSofa = modernFac.produceSofa();
        modernChair.print();
        modernSofa.print();

        Factory classicFac = new ClassicFactory();
        Chair classicChair = classicFac.produceChair();
        classicChair.print();
    }
}

interface Factory {
    public Chair produceChair();

    public Sofa produceSofa();
}

class ModernFactory implements Factory {
    public Chair produceChair() {
        return new ChairModern();
    }

    public Sofa produceSofa() {
        return new SofaModern();
    }
}

class ClassicFactory implements Factory {
    public Chair produceChair() {
        return new ChairClassic();
    }

    public Sofa produceSofa() {
        return new SofaClassic();
    }
}

interface Chair {
    public void print();
}

class ChairModern implements Chair {
    public void print() {
        System.out.println("Modern Factory生产：Modern-Chair");
    }
}

class ChairClassic implements Chair {
    public void print() {
        System.out.println("Classic Factory生产：Classic-Chair");
    }
}

interface Sofa {
    public void print();
}

class SofaModern implements Sofa {
    public void print() {
        System.out.println("Modern Factory生产： Modern-Sofa");
    }
}

class SofaClassic implements Sofa {
    public void print() {
        System.out.println("Classic Factory生产：Classic-Sofa");
    }
}
