package com.wbotelhos.model;

/**
* @author Washington Botelho dos Santos
* @artigo http://wbotelhos.com/2010/07/28/upload-e-download-com-vraptor-3
*/

public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -5310561663493623047L;

	private Integer id;
	private String foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}