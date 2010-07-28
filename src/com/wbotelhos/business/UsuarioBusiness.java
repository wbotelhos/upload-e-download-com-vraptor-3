package com.wbotelhos.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;

import com.wbotelhos.model.Usuario;
import com.wbotelhos.util.UserSession;

/**
* @author Washington Botelho dos Santos
* @artigo http://wbotelhos.com/2010/07/28/upload-e-download-com-vraptor-3
*/

@Component
public class UsuarioBusiness {

	private UserSession userSession;
	private static final String PATH_FOTO = System.getProperty("user.home") + "/img";

	UsuarioBusiness(UserSession userSession) {
		this.userSession = userSession;
	}

	public void salvarFoto(UploadedFile imagem) throws IOException {
		Usuario user = userSession.getUser();

		if (imagem != null) {
			String fileName = imagem.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : ".jpg";

			user.setFoto(user.getId() + extensao);

			try {
				IOUtils.copy(imagem.getFile(), new FileOutputStream(new File(PATH_FOTO + "/" + user.getFoto())));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new FileNotFoundException("Arquivo não encontrado!");
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException("Não foi possível enviar o arquivo!");
			}
		}

		this.atualizarFoto(user);
		userSession.setUser(user);
	}

	public File downloadFoto() {
		File file = new File(PATH_FOTO + "/" + userSession.getUser().getFoto());
		return (file.exists()) ? file : new File(PATH_FOTO + "/default.jpg");
	}

	public void removerFoto() {
		Usuario user = userSession.getUser();

		if (user.getFoto() != null && !user.getFoto().isEmpty()	&& !user.getFoto().equalsIgnoreCase("default.jpg")) {
			File file = new File(PATH_FOTO + "/" + user.getFoto());
			
			if (file.exists()) {
				file.delete();
			}
		}

		user.setFoto("default.jpg");

		this.atualizarFoto(user);
		userSession.setUser(user);
	}

	public void atualizarFoto(Usuario user) {
		// update Usuario ...
	}

}