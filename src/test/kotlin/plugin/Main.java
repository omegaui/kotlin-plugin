package test.kotlin.plugin;
import omega.instant.support.kotlin.KotlinPlugin;

import omega.IDE;
public class Main {
	public static void main(String[] args){
		IDE.main(args);
		KotlinPlugin plugin = new KotlinPlugin();
		plugin.init();
		plugin.enable();
	}
}
