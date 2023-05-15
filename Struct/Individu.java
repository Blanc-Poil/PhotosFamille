package Struct;

import javax.swing.plaf.metal.MetalTreeUI;

public class Individu {
    public int IDInd;
    public String NomInd;
    public String PrenomInd;
    public String NomPere;
    public String NomMere;

    public static void print(Individu[] inds)
    {
        System.out.println('\n');
        if (inds.length == 0) return;
        final String headerID = "ID individu";
        final String headerNom = "Nom";
        final String headerPrenom = "Prenom";
        final String headerPere = "Père";
        final String headerMere = "Mère";
        //get max for all attributs
        int maxID = headerID.length();
        int maxNom = headerNom.length();
        int maxPrenom = headerPrenom.length();
        int maxPere = headerPere.length();
        int maxMere = headerMere.length();
        int numLength;
        for (int i=0 ; i < inds.length ; i++) {
            numLength = (int)Math.log10(inds[i].IDInd) + 1;
            if (numLength > maxID) maxID = numLength;
            if (inds[i].NomInd.length() > maxNom) maxNom = inds[i].NomInd.length();
            if (inds[i].PrenomInd.length() > maxPrenom) maxPrenom = inds[i].PrenomInd.length();
            if (inds[i].NomPere.length() > maxPere) maxPere = inds[i].NomPere.length();
            if (inds[i].NomMere.length() > maxMere) maxMere = inds[i].NomMere.length();
        }
        //print header
        System.out.print(String.format("%-" + maxID + "s   ", headerID));
        System.out.print(String.format("%-" + maxNom + "s   ", headerNom));
        System.out.print(String.format("%-" + maxPrenom + "s   ", headerPrenom));
        System.out.print(String.format("%-" + maxPere + "s   ", headerPere));
        System.out.println(String.format("%-" + maxMere + "s", headerMere));
        System.out.print("-".repeat(maxID) + "   " + "-".repeat(maxNom) + "   " + "-".repeat(maxPrenom) + "   ");
        System.out.println("-".repeat(maxPere) + "   " + "-".repeat(maxMere));
        //print all table
        for (int i=0 ; i < inds.length ; i++) {
            System.out.print(String.format("%-" + maxID + "d   ", inds[i].IDInd));
            System.out.print(String.format("%-" + maxNom + "s   ", inds[i].NomInd));
            System.out.print(String.format("%-" + maxPrenom + "s   ", inds[i].PrenomInd));
            System.out.print(String.format("%-" + maxPere + "s   ", inds[i].NomPere));
            System.out.println(String.format("%-" + maxMere + "s", inds[i].NomMere));
        }
        System.out.println('\n');
    }
}
