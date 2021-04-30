package mc322.lab05;
public class AppDamas {
  /* Executa, imprime e retorna todos os estados do jogo dado pelo arquivo csv em 'path'
  */
  public static String[] executaJogo(String path){
    CSVReader reader = new CSVReader();

    reader.setDataSource(path);
    String[] commands = reader.requestCommands();

    String[] estados = new String[commands.length + 1];

    Tabuleiro tab = new Tabuleiro(8);

    System.out.println("Tabuleiro inicial:");
    estados[0] = tab.obterEstado();
    tab.apresentar(estados[0]);

    for(int i = 0; i < commands.length; i++){
      tab.processar(commands[i]);

      estados[i+1] = tab.obterEstado();
      tab.apresentar(estados[i+1]);
    }
    
    return estados;
  } 

  public static void main(String[] args) {
    AppDamas.executaJogo("src/mc322/lab05/teste1.csv");
  }
}