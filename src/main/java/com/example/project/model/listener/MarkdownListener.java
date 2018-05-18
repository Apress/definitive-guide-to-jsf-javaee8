package com.example.project.model.listener;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import com.example.project.model.Markdown;

public class MarkdownListener {

	private static final Parser PARSER = Parser.builder().build();
	private static final HtmlRenderer RENDERER = HtmlRenderer.builder().escapeHtml(true).build();
	private static final String VERSION = getCommonMarkVersion();

	@PrePersist
	public void parseMarkdown(Markdown markdown) {
        String html = RENDERER.render(PARSER.parse(markdown.getText()));
        markdown.setHtml(html);
        markdown.setVersion(VERSION);
	}

	@PreUpdate
	public void parseMarkdownIfNecessary(Markdown markdown) {
		if (markdown.getVersion() == null) {
			parseMarkdown(markdown);
		}
	}

	@PostLoad
	public void updateMarkdownIfNecessary(Markdown markdown) {
		if (!VERSION.equals(markdown.getVersion())) {
			parseMarkdown(markdown);
		}
	}

	private static String getCommonMarkVersion() {
		try {
			Properties properties = new Properties();
			properties.load(Parser.class.getResourceAsStream("/META-INF/maven/com.atlassian.commonmark/commonmark/pom.properties"));
			return properties.getProperty("version");
		}
		catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
