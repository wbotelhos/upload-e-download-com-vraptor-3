package com.wbotelhos.controller;

import java.io.File;
import java.io.IOException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

import com.wbotelhos.business.UsuarioBusiness;
import com.wbotelhos.model.Usuario;
import com.wbotelhos.util.UserSession;

/**
* @author Washington Botelho dos Santos
* @artigo http://wbotelhos.com/2010/07/28/upload-e-download-com-vraptor-3
*/

@Resource
public class UsuarioController {

	private Result result;
	private UserSession userSession;
	private UsuarioBusiness business;

	UsuarioController(Result result, UserSession userSession, UsuarioBusiness business) {
		this.result = result;
		this.userSession = userSession;
		this.business = business;
	}

	@Get
	@Path("/")
	public void upload() {
		if (userSession.getUser() == null) {
			Usuario user = new Usuario();
			user.setId(1);
			user.setFoto("default.jpg");
			userSession.setUser(user);
		}
	}

	@Post
	@Path("/usuario/foto")
	public void atualizarFoto(UploadedFile imagem) {
		try {
			business.salvarFoto(imagem);
		} catch (IOException e) {
			result.include("error", e.getMessage()).forwardTo(this).upload();
		}
		result.redirectTo(this).upload();
	}

	@Get
	@Path("/usuario/foto")
	public File downloadFoto() {
		return business.downloadFoto();
	}

	@Get
	@Path("/usuario/foto/remover")
	public void removerFoto() {
		business.removerFoto();
		result.redirectTo(this).upload();
	}

}