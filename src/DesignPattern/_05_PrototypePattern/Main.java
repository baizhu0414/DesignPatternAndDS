package DesignPattern._05_PrototypePattern;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        // 创建原型对象
        ProtoType original = new ConcretePrototype("Original Data");

        // 克隆原型对象
        ProtoType cloned = original.clone();
        cloned.setData(data);

        // 输出克隆对象的数据
        System.out.println("Clone Data: " + ((ConcretePrototype) cloned).getData());
    }
}

abstract class ProtoType implements Cloneable {
    public abstract ProtoType clone();
    public abstract void setData(String data);
}

// 2. 创建具体原型类
class ConcretePrototype extends ProtoType {
    private String data;

    public ConcretePrototype(String data) {
        this.data = data;
    }

    @Override
    public ProtoType clone() {
        return new ConcretePrototype(this.data);
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}

