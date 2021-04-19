package mc322.lab04;

public class Tabuleiro{
  private Peca tab[];
  private Coords coordenadas;
  
  //= tamanho^2 (pois o tabuleiro eh quadrado)
  private int num_casas;
  private int tamanho;
  
  /* Cria um tabuleiro de tamanho*tamanho */
  public Tabuleiro(int tamanho) {
    this.tamanho = tamanho;
    num_casas = tamanho*tamanho;

    coordenadas = new Coords(tamanho);

    tab = new Peca[num_casas];

    /* 'branco' eh o numero de linhas (ou colunas) em branco antes ou depois
    do tabuleiro */
    int branco = (tamanho-3)/2;

    for (int i = 0; i < num_casas; i++) {
      // O tabuleiro eh preenchido considerando que a cruz tem largura 3
      if (
        (i + tamanho - branco) % tamanho <= 2 ||  //partes superior e inferior
        ((i + 1 > branco * tamanho) && (i + 1 <= branco * tamanho + 3*tamanho)) //meio
      ){
        tab[i] = new Peca();
      }
    }
    // A peca central comeca eliminada
    tab[(num_casas-1)/2].matar();
  }

  public void apresentar(String estado){
    int linha = tamanho;
    int atual = 0;
    int total = estado.length();
    char ch;

    while (atual < total) {
      System.out.print(linha + " ");
      linha -= 1; 

      do {
        ch = estado.charAt(atual);
        atual += 1;

        System.out.print(" " + ch);
      } while(ch != '\n');
    }
    System.out.println("   a b c d e f g");
    System.out.println();
  }

  public String obterEstado(){
    String res = "";

    for(int i = 0; i < num_casas; i++){
      res += (tab[i] == null) ? ' ' : tab[i].repr();
      if((i+1) % tamanho == 0) res += '\n';
    }
    
    return res;
  }

  public boolean checarVitoria(){
    int vivos = 0;
    for(int i = 0; i < num_casas; i++){
      if(tab[i] != null && tab[i].vivo) vivos += 1;
    }

    return vivos == 1;
  }

  public void processar(String comando){
    String coord_i = comando.substring(0, 2);
    String coord_f = comando.substring(3, 5);

    System.out.println("Source: " + coord_i);
    System.out.println("Target: " + coord_f);

    int idxIni = coordenadas.cpn(coord_i);
    int idxFim = coordenadas.cpn(coord_f);

    int idxMeio = (idxIni + idxFim) / 2;
    
    // Se esta fora do tabuleiro ou se a posicao final ja tem uma peca
    if( tab[idxIni] == null || tab[idxFim] == null || tab[idxMeio] == null ||
        !tab[idxIni].vivo || !tab[idxMeio].vivo || tab[idxFim].vivo ||
        idxFim >= num_casas || idxIni >= num_casas){
      System.out.println("Comando invalido");
      return;
    }

    if 
    (
      // Comeu para a direita
      (idxFim == idxIni + 2 && idxIni % tamanho < tamanho - 2) ||
      // Comeu para a esquerda
      (idxFim == idxIni - 2 && idxIni % tamanho > 2) ||
      // Comeu para baixo
      (idxFim == idxIni + 2*tamanho) ||
      // Comeu para cima
      (idxFim == idxIni - 2*tamanho)
    ){
      tab[idxMeio].matar();

      Peca eax = tab[idxFim];
      tab[idxFim] = tab[idxIni];
      tab[idxIni] = eax;
    }
    
    else
      System.out.println("Comando invalido"); 
  }
  
}