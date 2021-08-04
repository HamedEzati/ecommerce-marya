package com.marya.controller.dto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class MessagesView {

	public static void saveCategoryMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful created Category.", ""));
	}

	public static void uploadFileMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "file uploaded."));
	}
	public static void deleteMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Deleted Student"));
	}
	public static void updateMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful Uploaded", ""));
	}

	public static void saveNotMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Student Registered Previously!"));
	}
}
