package main;

public class Main {

    static class TriangleSides {
        double a, b, c;
    }

    static boolean parseArgs(String[] args, TriangleSides triangle) {
        if (args.length != 3) {
            return false;
        }
        try {
            triangle.a = Double.parseDouble(args[0]);
            triangle.b = Double.parseDouble(args[1]);
            triangle.c = Double.parseDouble(args[2]);
        }
        catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    static String getTriangleType(TriangleSides triangle) {
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
        TriangleSides triangle = new TriangleSides();
        if (!parseArgs(args, triangle)) {
            System.out.println("Unknown error");
            System.exit(1);
        }
        System.out.println(getTriangleType(triangle));
    }
}
