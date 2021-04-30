package mc322.lab05;
public class Dama {
  private static Tabuleiro tab;

  private boolean isPreto;

  private int x, y;

  public Dama(boolean isPreto, int x, int y) {
    this.isPreto = isPreto;
    this.x = x;
    this.y = y;
  }

  // Devolve 0 se tiver peca na diagonal do movimento. Caso contrario,
  // devolve um inteiro que representa a direcao do movimento
  private int temPecaNaDiagonal(int xNew, int yNew) {
    if (yNew > y) {
      if (xNew > x) {
        for (int n = 1; n < xNew - x - 1; n++)
          if (tab.temPeca(x + n, y + n))
            return 0;

        return 1;
      }

      if (xNew < x) {
        for (int n = 1; n < x - xNew - 1; n++)
          if (tab.temPeca(x + n, y + n))
            return 0;

        return 2;
      }
    }

    if (yNew < y) {
      if (xNew > x) {
        for (int n = -1; n > x - xNew + 1; n--)
          if (tab.temPeca(x + n, y + n))
            return 0;

        return 3;
      }

      if (xNew < x) {
        for (int n = -1; n > xNew - x + 1; n--)
          if (tab.temPeca(x + n, y + n))
            return 0;

        return 4;
      }
    }
    
    return -1;
  }

  public boolean mover(int xNew, int yNew){
    // Checa se esta dentro do tabuleiro
    if(xNew < 0 || xNew > 7 || yNew < 0 || yNew > 7) return false;

    if(isPreto != tab.turnoDasPretas()) return false;

    // Checa se tem peca no destino
    if(tab.temPeca(xNew, yNew)) return false;

    // Verifica se o movimento foi na diagonal e se ha uma peca no meio do trajeto
    if (Math.abs(x-xNew) == Math.abs(y-yNew)){
      switch(temPecaNaDiagonal(xNew, yNew)){
        case 1:
          if(tab.temPeca(xNew-1, yNew-1)) tab.comerPeca(xNew-1, yNew-1);
          return true;
      
        case 2:
          if(tab.temPeca(xNew+1, yNew-1)) tab.comerPeca(xNew+1, yNew-1);
          return true;

        case 3:
          if(tab.temPeca(xNew+1, yNew+1)) tab.comerPeca(xNew+1, yNew+1);
          return true;

        case 4:
          if(xNew < x && tab.temPeca(xNew-1, yNew+1)) tab.comerPeca(xNew-1, yNew+1);
          return true;

        default:
          return false;
      
      }
    }

    return false;
  }

  public static void setTabuleiro(Tabuleiro t){
    Dama.tab = t;
  }

  public void setX(int x){
    this.x = x;
  }

  public void setY(int y){
    this.y = y;
  }

  public boolean cor(){
    return isPreto;
  }

  public char repr() {
    return isPreto ? 'P' : 'B';
  }
}