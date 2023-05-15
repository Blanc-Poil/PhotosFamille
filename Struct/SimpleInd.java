package Struct;

public class SimpleInd
{
    public int IDInd;
    public String NomInd;
    public String PrenomInd;

    public static void print(SimpleInd[] inds)
    {
        System.out.println('\n');
        if (inds.length == 0) return;
        final String headerID = "ID individu";
        final String headerNom = "Nom";
        final String headerPrenom = "Prenom";
        //get max for all attributs
        int maxID = headerID.length();
        int maxNom = headerNom.length();
        int maxPrenom = headerPrenom.length();
        int numLength;
        for (int i=0 ; i < inds.length ; i++) {
            numLength = (int)Math.log10(inds[i].IDInd) + 1;
            if (numLength > maxID) maxID = numLength;
            if (inds[i].NomInd.length() > maxNom) maxNom = inds[i].NomInd.length();
            if (inds[i].PrenomInd.length() > maxPrenom) maxPrenom = inds[i].PrenomInd.length();
        }
        //print header
        System.out.print(String.format("%-" + maxID + "s   ", headerID));
        System.out.print(String.format("%-" + maxNom + "s   ", headerNom));
        System.out.println(String.format("%-" + maxPrenom + "s", headerPrenom));
        System.out.println("-".repeat(maxID) + "   " + "-".repeat(maxNom) + "   " + "-".repeat(maxPrenom));
        //print all table
        for (int i=0 ; i < inds.length ; i++) {
            System.out.print(String.format("%-" + maxID + "d   ", inds[i].IDInd));
            System.out.print(String.format("%-" + maxNom + "s   ", inds[i].NomInd));
            System.out.println(String.format("%-" + maxPrenom + "s", inds[i].PrenomInd));
        }
        System.out.println('\n');
    }
}
