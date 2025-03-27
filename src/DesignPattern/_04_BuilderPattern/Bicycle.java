package DesignPattern._04_BuilderPattern;

import java.util.Scanner;

// 自行车产品
class Bike {
    private String frame;
    private String tires;

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setTires(String tires) {
        this.tires = tires;
    }

    @Override
    public String toString() {
        return frame + " " + tires;
    }
}

// 自行车建造者接口
interface BikeBuilder {
    BikeBuilder buildFrame();
    BikeBuilder buildTires();
    Bike getResult();
}

// 山地自行车建造者
class MountainBikeBuilder implements BikeBuilder {
    private Bike bike;

    public MountainBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public BikeBuilder buildFrame() {
        bike.setFrame("Aluminum Frame");
        return this;
    }

    @Override
    public BikeBuilder buildTires() {
        bike.setTires("Knobby Tires");
        return this;
    }

    @Override
    public Bike getResult() {
        return bike;
    }
}

// 公路自行车建造者
class RoadBikeBuilder implements BikeBuilder {
    private Bike bike;

    public RoadBikeBuilder() {
        this.bike = new Bike();
    }

    @Override
    public BikeBuilder buildFrame() {
        bike.setFrame("Carbon Frame");
        return this;
    }

    @Override
    public BikeBuilder buildTires() {
        bike.setTires("Slim Tires");
        return this;
    }

    @Override
    public Bike getResult() {
        return bike;
    }
}

// 自行车Director，负责构建自行车
class BikeDirector {
    public Bike construct(BikeBuilder builder) {
        Bike bike = builder.buildFrame().buildTires().getResult();
        return bike;
    }
}

public class Bicycle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();  // 订单数量
        scanner.nextLine();

        BikeDirector director = new BikeDirector();

        for (int i = 0; i < N; i++) {
            String bikeType = scanner.nextLine();

            BikeBuilder builder;
            // 根据输入类别，创建不同类型的具体建造者
            if (bikeType.equals("mountain")) {
                builder = new MountainBikeBuilder();
            } else {
                builder = new RoadBikeBuilder();
            }
			// Director负责指导生产产品
            Bike bike = director.construct(builder);
            System.out.println(bike);
        }
        scanner.close();
    }
}