package Struct;

public class Album
{
    public int IDAlbum;
    public String NomAlbum;

    public static void print(Album[] album)
    {
        System.out.println('\n');
        if (album.length == 0) return;
        final String headerIDAlbum = "ID Album";
        final String headerNomAlbum = "Nom Album";
        //get max for all attributs
        int maxIDAlbum = headerIDAlbum.length();
        int maxNomAlbum = headerNomAlbum.length();
        int numLength;
        for (int i=0 ; i < album.length ; i++) {
            numLength = (int)Math.log10(album[i].IDAlbum) + 1;
            if (numLength > maxIDAlbum) maxIDAlbum = numLength;
            if (album[i].NomAlbum.length() > maxNomAlbum) maxNomAlbum = album[i].NomAlbum.length();
        }
        //print header
        System.out.print(String.format("%-" + maxIDAlbum + "s   ", headerIDAlbum));
        System.out.println(String.format("%-" + maxNomAlbum + "s", headerNomAlbum));
        System.out.println("-".repeat(maxIDAlbum) + "   " + "-".repeat(maxNomAlbum));
        //print all table
        for (int i=0 ; i < album.length ; i++) {
            System.out.print(String.format("%-" + maxIDAlbum + "d   ", album[i].IDAlbum));
            System.out.println(String.format("%-" + maxNomAlbum + "s", album[i].NomAlbum));
        }
        System.out.println('\n');
    }
}
