package mc322.lab03;

public class Animacao {
    AquarioLombriga aquaLombr;
    String acoes;
    int acaoAtual;
    
    Animacao(String lombriga) {
        String num = "";
        num += lombriga.charAt(0);
        num += lombriga.charAt(1);
        int tamAquario = Integer.parseInt(num);
        num = "";
        num += lombriga.charAt(2);
        num += lombriga.charAt(3);
        int tamLombriga = Integer.parseInt(num);
        num = "";
        num += lombriga.charAt(4);
        num += lombriga.charAt(5);
        int posLombriga = Integer.parseInt(num);
        aquaLombr = new AquarioLombriga(tamAquario, tamLombriga, posLombriga);
        acoes = "";
        for (int i = 6; i < (lombriga.length()); i++) {
            acoes += lombriga.charAt(i);
        }
        acaoAtual = 0;
    }
    
    String apresenta() {
        return aquaLombr.apresenta();
    }
    
    void passo() {
        if (acaoAtual < (acoes.length())) {
            if (acoes.charAt(acaoAtual) == 'C') 
                aquaLombr.crescer();
            else if (acoes.charAt(acaoAtual) == 'M')
                aquaLombr.mover();
            else
                aquaLombr.virar();
            acaoAtual += 1;
        }
    }
}
