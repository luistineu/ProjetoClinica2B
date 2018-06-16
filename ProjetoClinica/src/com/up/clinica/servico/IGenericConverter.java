package com.up.clinica.servico;

import java.util.List;

public interface IGenericConverter<T> {
	
	public String convertListToJson(List<T> objetos) throws Exception;
	
	public String convertToJson(T objeto) throws Exception;
}
