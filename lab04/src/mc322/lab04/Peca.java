package mc322.lab04;

public class Peca{
  public boolean vivo;

  public Peca() {
    vivo = true;
  }

  public void matar() {
    vivo = false;
  }

  public void reviver() {
    vivo = true;
  }

  public char repr() {
    return vivo ? 'P' : '-';
  }
}