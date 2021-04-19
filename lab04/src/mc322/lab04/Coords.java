package mc322.lab04;

public class Coords{
  public int tamanho;

  public Coords(int tamanho){
    this.tamanho = tamanho;
  }

  /* Letra para numero */
  public static int lpn(char letra){
    return ((int) letra) - ((int) 'a') + 1;
  }

  /* Coordenadas para numero */
  public int cpn(String coords){
    int col = lpn(coords.charAt(0));
    int lin = Integer.parseInt(coords.substring(1));

    return (tamanho - lin) * tamanho + col - 1;
  }
}