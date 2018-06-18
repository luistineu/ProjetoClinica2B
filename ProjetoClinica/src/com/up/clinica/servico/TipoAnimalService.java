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
import com.up.clinica.model.dal.TipoAnimalDAO;

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
				TipoAnimalDAO dao = new TipoAnimalDAO();
				
				tipoAnimais = dao.listar();
				
				TipoAnimalJsonConverter converter = new TipoAnimalJsonConverter();
				String output = converter.convertToJson(tipoAnimais, "tipoAnimais");
				
				out.print(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
