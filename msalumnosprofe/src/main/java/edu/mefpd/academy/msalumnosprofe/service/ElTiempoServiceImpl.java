package edu.mefpd.academy.msalumnosprofe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mefpd.academy.msalumnosprofe.controller.AlumnosController;
import edu.mefpd.academy.msalumnosprofe.model.InfoTiempoBasica;

@Service
public class ElTiempoServiceImpl implements ElTiempoService {
	
	@Value("${apikeyaemet}")
	String apikeyaemet;
	
	Logger log = LoggerFactory.getLogger(ElTiempoServiceImpl.class);
	
	private static final String URL_TIEMPO = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/horaria/";

	@Override
	public String obtenerPrediccionEspecificaPorMunicipio(String municipio) {
		String infoTiempo = null;
		RestTemplate restTemplate = null;
		HttpHeaders headers = null;
		ResponseEntity<InfoTiempoBasica> reinfo = null;
		InfoTiempoBasica infoTiempoBasica = null;
		
			headers = new HttpHeaders();
			headers.add("Authorization", "Bearer " + this.apikeyaemet);
			restTemplate = new RestTemplate();
			reinfo = restTemplate.exchange(URL_TIEMPO+municipio, HttpMethod.GET, new HttpEntity<>("parameters", headers), InfoTiempoBasica.class);
			infoTiempoBasica = reinfo.getBody();
			//TODO, hacer un nuevo GET sobre infoTiempoBasica.datos() 
			log.debug("INFO TIEMPO RX " + infoTiempoBasica);
			//infoTiempo = infoTiempoBasica.toString();
			
			infoTiempo  = restTemplate.getForObject(infoTiempoBasica.datos(), String.class);
		
		return infoTiempo;
	}

}
