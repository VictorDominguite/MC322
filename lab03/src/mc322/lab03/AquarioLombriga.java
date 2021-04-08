package mc322.lab03;

public class AquarioLombriga {
    int tam_lombriga, tam_aquario, pos_lombriga, lado;
    // lado == 1 indica direita e lado == 0, esquerda
    
    AquarioLombriga(int tam_aquario, int tam_lombriga, int pos_lombriga) {
        if (tam_lombriga > tam_aquario) 
            this.tam_aquario = tam_lombriga;
        else
            this.tam_aquario = tam_aquario;
        
        this.tam_lombriga = tam_lombriga;
        
        // A posicao fornecida é a da ponta da cauda, porem eh 
        // convertida aqui para a posicao da cabeca
        if (pos_lombriga + tam_lombriga - 1 <= tam_aquario)
            // Se a lombriga couber no espaco determinado
            this.pos_lombriga = pos_lombriga + tam_lombriga - 1;
        else
            this.pos_lombriga = tam_lombriga;
        lado = 1;
    }
   
    void crescer() {
        if (lado == 0) {
            if (pos_lombriga + tam_lombriga - 1 < tam_aquario){
                tam_lombriga += 1;
            }
        }
        else {
            if (pos_lombriga - tam_lombriga >= 1){
                tam_lombriga += 1;
            }
        }
    }
    
    void virar() {
        if (lado == 0) {
            lado = 1;
            pos_lombriga = pos_lombriga + tam_lombriga - 1;
        }
        else {
            lado = 0;
            pos_lombriga = pos_lombriga - tam_lombriga + 1;
        }
    }
    
    void mover() {
        if (lado == 0) {
            if (pos_lombriga > 1) {
                pos_lombriga -= 1;
            }
            else {
                virar();
            }
        }
        else {
            if (pos_lombriga < tam_aquario) {
                pos_lombriga += 1;
            }
            else {
                virar();
            }
        }
    }
    String apresenta() {
        String aqua_lombr = "";
        int i = 1;
        if (lado == 0) {
            while (i < pos_lombriga) {
                aqua_lombr += '#';
                i += 1;
            }
            aqua_lombr += 'O';
            for (int j = 0; j < tam_lombriga - 1; j++) 
                aqua_lombr += '@';
            for (int j = i + tam_lombriga - 1; j < tam_aquario; j++) 
                aqua_lombr += '#';
        }
        else {
            for (int j = 0; j < tam_aquario - tam_lombriga - (tam_aquario - pos_lombriga); j++) 
                aqua_lombr += '#';
            for (int j = 0; j < tam_lombriga - 1; j++)
                aqua_lombr += '@';
            aqua_lombr += 'O';
            for (int j = 0; j < tam_aquario - pos_lombriga; j++) 
                aqua_lombr += '#';
        }
        return aqua_lombr;
    }
}
