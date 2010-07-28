package com.wbotelhos.util;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.wbotelhos.model.Usuario;

/**
* @author Washington Botelho dos Santos
* @artigo http://wbotelhos.com/2010/07/28/upload-e-download-com-vraptor-3
*/

@Component
@SessionScoped
public class UserSession implements java.io.Serializable {

	private static final long serialVersionUID = 418578521928412853L;

	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}