package com.code.solvers.model;

import java.io.Serializable;

public class RocketChatPostAttachment implements Serializable {

	private static final long serialVersionUID = -4212099281676882762L;
	
	private String title;
    private String title_link;
    private String text;
    private String image_rl;
    private String color;
    
	public RocketChatPostAttachment() {   }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_link() {
		return title_link;
	}

	public void setTitle_link(String title_link) {
		this.title_link = title_link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage_rl() {
		return image_rl;
	}

	public void setImage_rl(String image_rl) {
		this.image_rl = image_rl;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}

