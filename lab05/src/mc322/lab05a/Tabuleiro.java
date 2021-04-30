package mc322.lab05;
public class Tabuleiro{
  //(0,0) no topo superior esquerdo
  private Peao tab_peao[][];
  private Dama tab_dama[][];
  
  private int tamanho;

  private boolean vezPretas = false;
  
  /* Cria um tabuleiro de tamanho*tamanho */
  public Tabuleiro(int tamanho) {
    this.tamanho = tamanho;

    tab_peao = new Peao[tamanho][tamanho];
    Peao.setTabuleiro(this);
    tab_dama = new Dama[tamanho][tamanho];
    Dama.setTabuleiro(this);

    for(int i = tamanho-1; i >= 0; i--){
      for(int j = 0; j < tamanho; j++){
        if((i + j) % 2 == 0){
          if(i < 3)  //branco
            setPeao(j, i, new Peao(false, j, i));
          else if (i >= tamanho - 3) //preto
            setPeao(j, i, new Peao(true, j, i));
        }
      }
    }
  }

  /*  Checa se há peça (peão ou dama) da 'cor' na posição (x, y) */
  public boolean temPecaDaCor(int x, int y, boolean cor){
    if(peaoEm(x, y) != null && peaoEm(x, y).cor() == cor) return true;
    if(damaEm(x, y) != null && damaEm(x, y).cor() == cor) return true;

    return false;
  }

  /*  Checa se há peça (peão ou dama) na posição (x, y) */
  public boolean temPeca(int x, int y){
    return !(peaoEm(x, y) == null && damaEm(x, y) == null);
  }

  private Peao peaoEm(int x, int y){
    return tab_peao[tamanho-y-1][x];
  }

  private void setPeao(int x, int y, Peao p){
    tab_peao[tamanho-y-1][x] = p;
  }

  private Dama damaEm(int x, int y){
    return tab_dama[tamanho-y-1][x];
  }

  private void setDama(int x, int y, Dama d){
    tab_dama[tamanho-y-1][x] = d;
  }

  public String obterEstado(){
    String res = "";
    //Lendo o tabuleiro de baixo para cima, da esquerda para direita
    for(int i = tamanho-1; i >= 0; i--){
      for(int j = 0; j < tamanho; j++){
        if(peaoEm(j, i) != null) res += peaoEm(j, i).repr();
        else{
          if(damaEm(j, i) != null) res += damaEm(j, i).repr();
          else res += '-';
        }

      }
      res += '\n';
    }
    
    return res;
  }

  public void apresentar(String estado){
    int linha = tamanho;
    int total = estado.length();
    int atual = 0;
    char ch;

    while (atual < total) {
      System.out.print(linha);
      linha -= 1; 

      do {
        ch = estado.charAt(atual);
        atual += 1;

        System.out.print(" " + ch);
      } while(ch != '\n');
    }
    System.out.println("  a b c d e f g h");
    System.out.println();
  }

  public boolean turnoDasPretas(){
    return vezPretas;
  }

  private void moverPeca(int xIni, int yIni, int xFim, int yFim, boolean ehDama){
      if(ehDama){
        Dama d = damaEm(xIni, yIni);
        setDama(xFim, yFim, d);
        d.setX(xFim);
        d.setY(yFim);
        setDama(xIni, yIni, null); 
      }
      else{
        Peao p = peaoEm(xIni, yIni);
        setPeao(xFim, yFim, p);
        p.setX(xFim);
        p.setY(yFim);
        setPeao(xIni, yIni, null);
      }
  }

  public void processar(String comando){
    /*for(int i = 0; i < tamanho; i++){
      for(int j = 0; j < tamanho; j++){
        if(peaoEm(j, i) != null) System.out.println(peaoEm(j, i).cor());
        else System.out.println("null");
      }
    }*/
    
    String coord_i = comando.substring(0, 2);
    String coord_f = comando.substring(3, 5);

    System.out.println("Source: " + coord_i);
    System.out.println("Target: " + coord_f);

    int xIni, yIni, xFim, yFim;

    xIni = Coords.lpn(coord_i.charAt(0)) - 1;
    yIni = Integer.parseInt("" + coord_i.charAt(1)) - 1;

    xFim = Coords.lpn(coord_f.charAt(0)) - 1;
    yFim = Integer.parseInt("" + coord_f.charAt(1)) - 1;

    if(peaoEm(xIni, yIni) != null){
      //System.out.printf("%d %d %d %d\n", xIni, yIni, xFim, yFim);
      if(peaoEm(xIni, yIni).mover(xFim, yFim))
        moverPeca(xIni, yIni, xFim, yFim, false);
      else
        System.out.println("movimento invalido");
    
    }
    else if(damaEm(xIni, yIni) != null){
      if(damaEm(xIni, yIni).mover(xFim, yFim))
        moverPeca(xIni, yIni, xFim, yFim, true);
       else
        System.out.println("movimento invalido");
    }
    else return;

    vezPretas = !vezPretas;
  }

  public boolean comerPeca(int x, int y) {
    setPeao(x, y, null);
    setDama(x, y, null);

    return true;
  }
  // VERIFICAR SE VIRA DAMA
} 