package Struct;

public class Photo {
    public String NomAlbum;
    public int NumPage;
    public String LibelleEvenement;

    public static void print(Photo[] photos)
    {
        System.out.println('\n');
        if (photos.length == 0) return;
        final String headerNom = "Nom album";
        final String headerPage = "Numéro de page";
        final String headerLabel = "Libellé de l'événement";
        //get max for all attributs
        int maxNom = headerNom.length();
        int maxPage = headerPage.length();
        int maxLabel = headerLabel.length();
        int numLength;
        for (int i=0 ; i < photos.length ; i++) {
            if (photos[i].NomAlbum.length() > maxNom) maxNom = photos[i].NomAlbum.length();
            numLength = (int)Math.log10(photos[i].NumPage) + 1;
            if (numLength > maxPage) maxPage = numLength;
            if (photos[i].LibelleEvenement.length() > maxLabel) maxLabel = photos[i].LibelleEvenement.length();
        }
        //print header
        System.out.print(String.format("%-" + maxNom + "s   ", headerNom));
        System.out.print(String.format("%-" + maxPage + "s   " , headerPage));
        System.out.println(String.format("%-" + maxLabel + "s", headerLabel));
        System.out.println("-".repeat(maxNom) + "   " + "-".repeat(maxPage) + "   " + "-".repeat(maxLabel));
        //print all table
        for (int i=0 ; i < photos.length ; i++) {
            System.out.print(String.format("%-" + maxNom + "s   ", photos[i].NomAlbum));
            System.out.print(String.format("%-" + maxPage + "d   ", photos[i].NumPage));
            System.out.println(String.format("%-" + maxLabel + "s", photos[i].LibelleEvenement));
        }
        System.out.println('\n');
    }
}
