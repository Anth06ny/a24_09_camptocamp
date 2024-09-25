package org.example.a24_09_camptocamp.exo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExoThread {
    public static void main(String[] args) throws Exception {

        System.out.println("Start V1");
        long before = System.currentTimeMillis();
        exo4();
        System.out.println("V1 Done in " + (System.currentTimeMillis() - before) + "ms");
//
//        System.out.println("\nStart V2");
//        before = System.currentTimeMillis();
//        exoV2(5);
//        System.out.println("V2 Done in " + (System.currentTimeMillis() - before) + "ms");
    }

    public static void exo4() {

        List<TransactionBean> transactions = generateTransactions(100);

        long startTime = System.currentTimeMillis();

        double total = transactions.parallelStream()
                .unordered()
                .filter(t->"Facture".equals(t.getCategory()) || "Impot".equals(t.getCategory()) )
                .mapToDouble(t->t.getMontant()).sum();

        long endTime = System.currentTimeMillis();
        System.out.println("Somme totale: " + total);
        System.out.println("Temps d'exécution (stream): " + (endTime - startTime) + " ms\n");

        // Répéter avec parallelStream() pour comparer
        startTime = System.currentTimeMillis();

        total = transactions.stream()
                .unordered()
                .filter(t->"Facture".equals(t.getCategory()) || "Impot".equals(t.getCategory()) )
                .mapToDouble(t->t.getMontant()).sum();

        endTime = System.currentTimeMillis();
        System.out.println("Somme totale: " + total);
        System.out.println("Temps d'exécution (parallelStream): " + (endTime - startTime) + " ms");
    }


    public static List<TransactionBean> generateTransactions(int number) {
        Random random = new Random();
        String[] categories = new String[]{"Voyage", "Facture", "Alimentaire", "Impot", "Loisir"};

        //Création de transaction
        return IntStream.range(0, number)
                .mapToObj(i -> new TransactionBean(
                        random.nextDouble() * 10000, // Montant aléatoire
                        categories[random.nextInt(categories.length)]
                )).toList();
    }

    public static void exoV1(int nbCall) {
        BallotBoxBean ballot = new BallotBoxBean();
        ArrayList<Thread> list = new ArrayList<>();

        for (int i = 0; i < nbCall; i++) {
            Thread t = new Thread(ballot::add1VoiceAndWait);
            t.start();
            list.add(t);
        }


        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("number=" + ballot.current());
    }

    public static void exoV2(int nbCall) {
        BallotBoxBean ballot = new BallotBoxBean();
        ArrayList<Thread> list = new ArrayList<>();

        for (int i = 0; i < nbCall; i++) {
            Thread t = new Thread(ballot::add1VoiceAndWait);
            list.add(t);
        }

        list.stream().peek(t->t.start()).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("number=" + ballot.current());
    }

    public static void exoV3(int nbCall) {
        BallotBoxBean ballot = new BallotBoxBean();

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < nbCall; i++) {
            executor.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());
                ballot.add1VoiceAndWait();
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("number=" + ballot.current());
    }
}