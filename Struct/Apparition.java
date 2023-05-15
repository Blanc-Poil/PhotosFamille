package Struct;

public class Apparition
{
    public String NomAlbum;
    public int NumPage;

    public static void print(Apparition[] apps)
    {
        System.out.println('\n');
        if (apps.length == 0) return;
        final String headerNomAlbum = "Nom album";
        final String headerNumPage = "Numéro de page";
        //get max for all attributs
        int maxNomAlbum = headerNomAlbum.length();
        int maxNumPage = headerNumPage.length();
        for (int i=0 ; i < apps.length ; i++) {
            if (apps[i].NomAlbum.length() > maxNomAlbum) maxNomAlbum = apps[i].NomAlbum.length();
        }
        //print header
        System.out.print(String.format("%-" + maxNomAlbum + "s   ", headerNomAlbum));
        System.out.println(String.format("%-" + maxNumPage + "s", headerNumPage));
        System.out.println("-".repeat(maxNomAlbum) + "   " + "-".repeat(maxNumPage));
        //print all table
        for (int i=0 ; i < apps.length ; i++) {
            System.out.print(String.format("%-" + maxNomAlbum + "s   ", apps[i].NomAlbum));
            System.out.println(String.format("%-" + maxNumPage + "d", apps[i].NumPage));
        }
        System.out.println('\n');
    }
}
