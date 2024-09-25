package org.example.a24_09_camptocamp.exo;

public sealed class Shape {

    public static final class Circle extends Shape {
        public double radius;
        public Circle(double radius) {
            this.radius = radius;
        }
    }

    public static final class Rectangle extends Shape {
        public double length, width;
        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
    }
}