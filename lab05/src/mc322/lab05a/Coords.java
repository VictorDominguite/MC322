package mc322.lab05;
public class Coords{
  public Coords(){}
  
  /* Letra para Número */
  public static int lpn(char letra){
    return ((int) letra) - ((int) 'a') + 1;
  }
}