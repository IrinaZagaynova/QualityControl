package main;

public class Main {

    static class Sides {
        double a, b, c;
    }

    static boolean parseArgs(String[] args, Sides sides) {
        if (args.length != 3) {
            return false;
        }
        try {
            sides.a = Double.parseDouble(args[0]);
            sides.b = Double.parseDouble(args[1]);
            sides.c = Double.parseDouble(args[2]);
        }
        catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    static String getTriangleType(Sides triangle) {
        if (triangle.a <= 0 || triangle.b <= 0 || triangle.c <= 0) {
            return "Not a triangle";
        }
        if (triangle.a == triangle.b && triangle.b == triangle.c) {
            return "Equilateral";
        }
        else if (triangle.a == triangle.b || triangle.b == triangle.c || triangle.c == triangle.a) {
            return "Isosceles";
        }
        else if (triangle.a + triangle.b > triangle.c && triangle.a + triangle.c > triangle.b && triangle.b + triangle.c > triangle.a) {
            return "Ordinary";
        }
        else {
            return "Not a triangle";
        }
    }

    public static void main(String[] args) {
        Sides sides = new Sides();
        if (!parseArgs(args, sides)) {
            System.out.println("Unknown error");
        }
        else {
            System.out.println(getTriangleType(sides));
        }
    }
}
