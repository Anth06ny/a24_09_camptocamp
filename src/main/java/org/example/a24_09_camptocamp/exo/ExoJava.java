package org.example.a24_09_camptocamp.exo;

import java.util.function.*;

public class ExoJava {


    public static void main(String[] args) {

        // Prend un texte et l’affiche en minuscule
        Consumer<String> lower = s-> System.out.println(s.toLowerCase());
        lower.accept("HelloWorld");

        // Prenant un nombre de minutes et retournant le nombre d’heures équivalentes
        //V1
        Function<Integer, Integer> hour = minutes -> minutes / 60;
        //V2
        //UnaryOperator<Integer> hour = minutes -> minutes / 60;
        System.out.println(hour.apply(90) + " heures");

        // Prenant 2 entiers et retournant le plus grand
        //V1
        //BiFunction<Integer, Integer, Integer> max = (a, b) -> Math.max(a,b);
        //V2
        //BinaryOperator<Integer> max = (a, b) -> Math.max(a,b);
        //V3
        IntBinaryOperator max = (a, b) -> Math.max(a,b);
        System.out.println("Le maximum est " + max.applyAsInt(5, 10));

        TriFunction<Integer, Integer, Double, Double> volume = (a, b, c) ->  a * b * c;

        double res = volume.apply(1,2,3.0);
        System.out.println(res);
    }

    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u , V v);
    }
}
