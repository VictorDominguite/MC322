package mc322.lab03;

public class AppLab03 {

	public static void main(String[] args) {
		String strAnimacao = "080403MCMVM";
		Animacao aquaLombr = new Animacao(strAnimacao);
		System.out.println(aquaLombr.apresenta());
		aquaLombr.passo();
		System.out.println(aquaLombr.apresenta());
		aquaLombr.passo();
		System.out.println(aquaLombr.apresenta());
		aquaLombr.passo();
		System.out.println(aquaLombr.apresenta());
		aquaLombr.passo();
		System.out.println(aquaLombr.apresenta());
		aquaLombr.passo();
		System.out.println(aquaLombr.apresenta());
	}

}
