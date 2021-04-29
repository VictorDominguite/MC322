package mc322.lab05;
public class Peao{
  private static Tabuleiro tab;

  private boolean isPreto;

  //X É COLUNA | Y É LINHA
  private int x, y;

  public Peao(boolean isPreto, int x, int y){
    this.isPreto = isPreto;
    this.x = x;
    this.y = y;
  }

  public boolean mover(int xNew, int yNew){
    // Checa se esta dentro do tabuleiro
    if(xNew < 0 || xNew > 7 || yNew < 0 || yNew > 7) return false;

    if(isPreto != tab.turnoDasPretas()) return false;

    // Checa se tem peca no destino
    if(tab.temPeca(xNew, yNew)) return false;

    System.out.printf("%d %d %d %d\n", x, y, xNew, yNew);

    //mover branco
    if(!isPreto && ((yNew == y+1 && xNew == x+1) || (yNew == y+1 && xNew == x-1)))
      return true;

    //mover preto
    if(isPreto && ((yNew == y-1 && xNew == x+1) || (yNew == y-1 && xNew == x+1))) 
      return true;

    //comer
    if(yNew == y+2 && xNew == x+2){
      if(tab.temPecaDaCor(x+1, y+1, !isPreto))
        return tab.comerPeca(x+1, y+1);
    }
    if(yNew == y+2 && xNew == x-2){
      if(tab.temPecaDaCor(x-1, y+1, !isPreto))
        return tab.comerPeca(x-1, y+1);
    }
    if(yNew == y-2 && xNew == x-2){
      if(tab.temPecaDaCor(x-1, y-1, !isPreto))
        return tab.comerPeca(x-1, y-1);
    }
    if(yNew == y-2 && xNew == x+2){
      if(tab.temPecaDaCor(x+1, y-1, !isPreto))
        return tab.comerPeca(x+1, y-1);
    }

    return false;
  }

  public static void setTabuleiro(Tabuleiro t){
    Peao.tab = t;
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

  public char repr(){
    return isPreto ? 'p' : 'b';
  }
}