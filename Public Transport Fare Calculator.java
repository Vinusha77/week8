import java.util.*;

abstract class Transport {
    double distance;

    Transport(double distance) {
        this.distance = distance;
    }

    abstract double calculateFare();

    abstract String getType();
}

class Bus extends Transport {
    Bus(double distance) {
        super(distance);
    }

    double calculateFare() {
        return Math.min(2 + 0.10 * distance, 10);
    }

    String getType() {
        return "BUS";
    }
}

class Train extends Transport {
    Train(double distance) {
        super(distance);
    }

    double calculateFare() {
        return 3 + 0.15 * distance;
    }

    String getType() {
        return "TRAIN";
    }
}

class Metro extends Transport {
    double peakHourFactor;

    Metro(double distance, double factor) {
        super(distance);
        peakHourFactor = factor;
    }

    double calculateFare() {
        return (1.50 + 0.20 * distance) * peakHourFactor;
    }

    String getType() {
        return "METRO";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next().toUpperCase();
            double distance = sc.nextDouble();

            Transport transport;

            switch (type) {
                case "BUS":
                    transport = new Bus(distance);
                    break;

                case "TRAIN":
                    transport = new Train(distance);
                    break;

                case "METRO":
                    double factor = sc.nextDouble();
                    transport = new Metro(distance, factor);
                    break;

                default:
                    continue;
            }

            double fare = transport.calculateFare();

            System.out.printf("%s: %.2f%n",
                    transport.getType(), fare);
            total += fare;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
