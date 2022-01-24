package omega.instant.support.kotlin.framework;
import omega.instant.support.java.assist.CodeTokenizer;

import java.util.LinkedList;
public class KotlinImportFramework {
	public static LinkedList<String> fastListContainedClasses(String code){
		LinkedList<String> clazzez = new LinkedList<>();
		LinkedList<String> lines = CodeTokenizer.tokenize(code, '\n');
		for(String line : lines){
			if(!line.startsWith("import "))
				continue;
			code = line.substring(line.lastIndexOf('.') + 1).trim();
			if(!code.isBlank() && Character.isLetter(code.charAt(0)))
				clazzez.add(code);
		}
		return clazzez;
	}
}
