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

import com.up.clinica.model.Animal;
import com.up.clinica.model.dal.AnimalDAO;

@WebServlet(name = "GetAnimais", urlPatterns = { "/GetAnimais" })


public class AnimalService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				response.setContentType("application/json;charset=UTF-8");
				ServletOutputStream out = response.getOutputStream();
				
				AnimalDAO dao = new AnimalDAO();
				
				List<Animal> animais = new ArrayList<>();
				animais = dao.listar();
				
				AnimalJsonConverter converter = new AnimalJsonConverter();
				String output = converter.convertToJson(animais, "animais");
				
				out.print(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				response.setContentType("application/json;charset=UTF-8");
				ServletOutputStream out = response.getOutputStream();
				
				AnimalDAO dao = new AnimalDAO();
				
				Animal animal = new Animal();
				animal.setNome("Nome");
				
				dao.persistir(animal);
				
				AnimalJsonConverter converter = new AnimalJsonConverter();
				String output = converter.convertToJson(animal, "animais");
				
				out.print(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
