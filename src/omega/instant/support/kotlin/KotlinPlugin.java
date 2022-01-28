package omega.instant.support.kotlin;
import omega.instant.support.kotlin.highlighter.KotlinCodeHighlighter;

import omega.instant.support.CodeHighlighters;

import java.net.URL;

import omega.plugin.Plugin;
import omega.plugin.PluginCategory;

public class KotlinPlugin implements Plugin{
	private KotlinCodeHighlighter kotlinCodeHighlighter;
	
	@Override
	public boolean init() {
		kotlinCodeHighlighter = new KotlinCodeHighlighter();
		return true;
	}
	@Override
	public boolean enable() {
		CodeHighlighters.add(kotlinCodeHighlighter);
		return true;
	}
	@Override
	public boolean disable() {
		CodeHighlighters.remove(kotlinCodeHighlighter);
		return true;
	}
	@Override
	public String getName() {
		return "Kotlin";
	}
	@Override
	public String getVersion() {
		return "v2.2";
	}
	@Override
	public URL getImage() {
		try{
			return getClass().getResource("/fluent-icons/icons8-kotlin-48.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean needsRestart() {
		return false;
	}
	@Override
	public String getPluginCategory() {
		return PluginCategory.LANGUAGE_SUPPORT;
	}
	@Override
	public String getDescription() {
		return "Kotlin plugin for Omega IDE, currenlty supports Basic Code Highlights.";
	}
	@Override
	public String getAuthor() {
		return "Omega UI";
	}
	@Override
	public String getLicense() {
		return "GNU GPL v3";
	}
	@Override
	public String getSizeInMegaBytes() {
		return "0.061 MB";
	}
	
}
