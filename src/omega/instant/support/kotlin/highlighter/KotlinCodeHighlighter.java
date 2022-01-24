package omega.instant.support.kotlin.highlighter;
import omega.instant.support.kotlin.framework.KotlinImportFramework;

import java.util.LinkedList;

import java.awt.Color;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;

import omega.instant.support.AbstractCodeHighlighter;

import static org.fife.ui.rsyntaxtextarea.Token.*;
import static omega.instant.support.java.highlighter.BasicCodeHighlighter.*;
import static omega.io.UIManager.*;

public class KotlinCodeHighlighter extends AbstractCodeHighlighter {
	
	public static Color keywordColor;
	
	public static LinkedList<String> lastClassList;
	public static String lastText;

	static {
		keywordColor = isDarkMode() ? Color.decode("#CC7832") : Color.decode("#0033B5");
	}
	
	@Override
	public boolean canComputeForeground(RSyntaxTextArea rSyntaxTextArea, Token token) {
		return rSyntaxTextArea.getSyntaxEditingStyle().equals("text/kotlin")
		&& switch(token.getType()){
			case FUNCTION:
			case IDENTIFIER:
				yield true;
			default:
				yield false;
		};
	}

	@Override
	public Color computeForegroundColor(RSyntaxTextArea rSyntaxTextArea, Token token) {
		try{
			String text = token.getLexeme();
			if(text.equals("package"))
				return keywordColor;
			if(isReturnKeyword(text))
				return returnKeyForeColor;
			if(isValueKeyword(text))
				return valueKeyForeColor;
			if(isKotlinClass(rSyntaxTextArea, text))
				return classColor;
			if(isJavaConstant(text))
				return javaConstantForeColor;
			if(isKotlinNormalFuctionDeclaration(rSyntaxTextArea, token))
				return methColor;
		}
		catch(Exception e){
			
		}
		return null;
	}

	public static synchronized boolean isKotlinNormalFuctionDeclaration(RSyntaxTextArea textArea, Token t){
		if(!t.isIdentifier())
			return false;
		String text = t.getLexeme();
		if(!Character.isLowerCase(text.charAt(0)))
			return false;
		String code = textArea.getText();
		int endOffset = t.getEndOffset();
		int startOffset = t.getOffset() - 1;
		if(code.length() - 1 <= endOffset && startOffset >= 0)
			return false;
		return code.charAt(startOffset) == ' ' && code.charAt(endOffset) == '(' && code.charAt(code.indexOf('\n', endOffset) - 1) == '{';
	}

	public static synchronized boolean isKotlinClass(RSyntaxTextArea textArea, String text){
		LinkedList<String> clazzez = null;
		if(lastText != null && lastText.equals(textArea.getText()))
			clazzez = lastClassList;
		else
			clazzez = lastClassList = KotlinImportFramework.fastListContainedClasses(lastText = textArea.getText());
		for(String name : clazzez){
			if(name.equals(text))
				return true;
		}
		return false;
	}
}
