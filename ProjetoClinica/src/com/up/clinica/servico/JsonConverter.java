package com.up.clinica.servico;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.up.clinica.model.Animal;

public abstract class JsonConverter<T> implements IGenericConverter<T> {

	private final Gson gson;

 	public JsonConverter() {
 		gson = new GsonBuilder().create();
 	}
 	
	public String  convertToJson(List<T> objetos, String tipo) {
		 JsonArray jarray = gson.toJsonTree(objetos).getAsJsonArray();
		 JsonObject jsonObject = new JsonObject();
		 jsonObject.add(tipo, jarray);
		 
		 return jsonObject.toString();
	}
	
	public String convertToJson(T objeto, String tipo) {
		 JsonObject jsonObject = new JsonObject();
		 jsonObject.add(tipo, gson.toJsonTree(objeto));
		 return jsonObject.toString();
	}
	
	
}
