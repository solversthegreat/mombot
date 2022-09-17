package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message implements Serializable {

	private static final long serialVersionUID = 7068200343266307019L;
	private String _id;
	private String rid;
	private String msg;
	private Date ts;
	private U u;
	private Date _updatedAt;
	private String t;
	private boolean groupable;
	private ArrayList<Object> urls;
	private ArrayList<U> mentions;
	private ArrayList<Object> channels;
	private ArrayList<Md> md;
	private String drid;
	private ArrayList<Object> attachments;
	private int dcount;
	private Date dlm;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public U getU() {
		return u;
	}
	public void setU(U u) {
		this.u = u;
	}
	public Date get_updatedAt() {
		return _updatedAt;
	}
	public void set_updatedAt(Date _updatedAt) {
		this._updatedAt = _updatedAt;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public boolean isGroupable() {
		return groupable;
	}
	public void setGroupable(boolean groupable) {
		this.groupable = groupable;
	}
	public ArrayList<Object> getUrls() {
		return urls;
	}
	public void setUrls(ArrayList<Object> urls) {
		this.urls = urls;
	}
	public ArrayList<U> getMentions() {
		return mentions;
	}
	public void setMentions(ArrayList<U> mentions) {
		this.mentions = mentions;
	}
	public ArrayList<Object> getChannels() {
		return channels;
	}
	public void setChannels(ArrayList<Object> channels) {
		this.channels = channels;
	}
	public ArrayList<Md> getMd() {
		return md;
	}
	public void setMd(ArrayList<Md> md) {
		this.md = md;
	}
	public String getDrid() {
		return drid;
	}
	public void setDrid(String drid) {
		this.drid = drid;
	}
	public ArrayList<Object> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<Object> attachments) {
		this.attachments = attachments;
	}
	public int getDcount() {
		return dcount;
	}
	public void setDcount(int dcount) {
		this.dcount = dcount;
	}
	public Date getDlm() {
		return dlm;
	}
	public void setDlm(Date dlm) {
		this.dlm = dlm;
	}
	
}
