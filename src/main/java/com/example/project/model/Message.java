package com.example.project.model;

import static java.util.Collections.emptyList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.example.project.model.listener.MarkdownListener;

@Entity
@EntityListeners(MarkdownListener.class)
public class Message extends BaseEntity implements Markdown {

	private static final long serialVersionUID = 1L;

	public static final int VERSION_MAXLENGTH = 8;

	@Lob
	@Column(nullable = false)
	private @NotNull String text;

	@Lob
	@Column(nullable = false)
	private @NotNull String html;

	@Column(nullable = false, length = VERSION_MAXLENGTH)
	private @NotNull String version;

	@ManyToOne
	private Message replyTo;

	@OneToMany(mappedBy="replyTo")
	private List<Message> replies = emptyList();

	public static Message create(String text, Message replyTo) {
		Message message = new Message();
		message.text = text;
		message.replyTo = replyTo;
		return message;
	}

	@Override
	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (!text.equals(this.text)) {
			this.text = text;
			setVersion(null);
		}
	}

	public String getHtml() {
		return html;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public Message getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Message replyTo) {
		this.replyTo = replyTo;
	}

	public List<Message> getReplies() {
		return replies;
	}

	public void setReplies(List<Message> replies) {
		this.replies = replies;
	}

}
