package com.up.clinica.servico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.up.clinica.model.TipoAnimal;

@WebServlet(name = "GetTipoAnimais", urlPatterns = { "/GetTipoAnimais" })

public class TipoAnimalService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				response.setContentType("application/json;charset=UTF-8");
				ServletOutputStream out = response.getOutputStream();
				
				List<TipoAnimal> tipoAnimais = new ArrayList<>();
				TipoAnimal ta1 = new TipoAnimal();
				ta1.setAcronimo("CAO");
				ta1.setNome("Cachorro");
				ta1.setDescricao("É o melhor amigo do homem");
				
				TipoAnimal ta2 = new TipoAnimal();
				ta2.setAcronimo("GAT");
				ta2.setNome("Gato");
				ta2.setDescricao("Tem medo de agua");
				
				tipoAnimais.add(ta1);
				tipoAnimais.add(ta2);
				
				TipoAnimalJsonConverter converter = new TipoAnimalJsonConverter();
				String output = converter.convertToJson(tipoAnimais, "tipoAnimais");
				
				out.print(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
