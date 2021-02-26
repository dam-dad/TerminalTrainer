package dad.javafx.terminaltrainer.ui.app;

import dad.javafx.terminaltrainer.config.Config;

public class Desabilitar {
	public static void main(String[] args) {
		Config.CONFIG.disable();
		System.out.println(Config.CONFIG.isEnabled());
	}
}
