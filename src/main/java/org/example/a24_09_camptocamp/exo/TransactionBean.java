package org.example.a24_09_camptocamp.exo;

public class TransactionBean {
    private double montant;
    private String category;

    public TransactionBean(double montant, String category) {
        this.montant = montant;
        this.category = category;
    }

    public double getMontant() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return montant;
    }

    public String getCategory() {
        return category;
    }
}