package com.projet.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import classP1.Ville_france;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		main(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		main(request, response);
	}

	public void main(HttpServletRequest request, HttpServletResponse response) {
		Object Ocommande = request.getParameter("commande");
		String commande = "";
		String pageOut = "accueil";
		
		if (Ocommande != null) {
			commande = (String) Ocommande;
		}
		
		switch (commande) {
			case "calcD":
				request.setAttribute("distance", calculeDistance(request));
				break;
			case "accueil":
				pageOut = "accueil";
				break;
			case "listeVilles":
				pageOut = "ville";
				cmdListeVille(request);
				break;
			case "focusVille":
				pageOut = "detailVille";
				Ville_france ville = searchVille(request.getParameter("codeCommune"));
				request.setAttribute("villeDetail", ville);	
				String tempFe = getTemperature(ville.getLatitude(),ville.getLongitude());
				request.setAttribute("temp", fromFtoC(tempFe));
				break;
			case "addVille":
				createVille(request);
				break;
			case "deleteVille":
				deleteVille(request);
				break;
			case "updateVille":
				updateVille(request);
				pageOut = "detailVille";
				Ville_france villeF = searchVille(request.getParameter("codeCommune"));
				request.setAttribute("villeDetail", villeF);
				String tempF = getTemperature(villeF.getLatitude(),villeF.getLongitude());
				request.setAttribute("temp", fromFtoC(tempF));
				break;
			default: 				
				break;
		}
			
		request.setAttribute("ville", getVille());

		try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/"+pageOut+".jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Ville_france[] getVille() {
		String result = "";
		try {

			URL url = new URL("http://127.0.0.1:8181/ville");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
						
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result += output;

			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
		final GsonBuilder gsonBuilder = new GsonBuilder();
		final Gson gson1 = gsonBuilder.create();

		Ville_france[] testCase = gson1.fromJson(result, Ville_france[].class);
		return testCase;
	}
	
	public void createVille(HttpServletRequest request) {
		String codeCommune = request.getParameter("codeCommune");
		String nom = request.getParameter("nomCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelleAcheminement");
		String ligne = request.getParameter("ligne");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		try {

			URL url = new URL("http://127.0.0.1:8181/ville?codeCommune="+codeCommune+"&nom="+nom+"&cp="+codePostal+"&libelle="+libelle+"&ligne="+ligne+"&latitude="+latitude+"&longitude="+longitude);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
						
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			System.out.println("Output from Server .... \n");			

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteVille(HttpServletRequest request) {
		String codeCommune = request.getParameter("codeCommune");
		
		try {

			URL url = new URL("http://127.0.0.1:8181/ville?codeCommune="+codeCommune);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");
						
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			System.out.println("Output from Server .... \n");			

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updateVille(HttpServletRequest request) {
		String codeCommune = request.getParameter("codeCommune");
		String nom = request.getParameter("nomCommune");
		String codePostal = request.getParameter("codePostal");
		String libelle = request.getParameter("libelleAcheminement");
		String ligne = request.getParameter("ligne");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		try {

			URL url = new URL("http://127.0.0.1:8181/ville?codeCommune="+codeCommune+"&nom="+nom+"&cp="+codePostal+"&libelle="+libelle+"&ligne="+ligne+"&latitude="+latitude+"&longitude="+longitude);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Accept", "application/json");
						
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			System.out.println("Output from Server .... \n");			

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int calculeDistance(HttpServletRequest request) {	
		String ville1 = request.getParameter("ville1");
		String ville2 = request.getParameter("ville2");
		
		if(!ville1.equals("choose") && !ville2.equals("choose") && !ville1.equals(ville2)) {
			double[] localisation = { 0.0,0.0,0.0,0.0};
			
	        localisation[0]=Double.parseDouble(searchVille(ville1).getLatitude());
	        localisation[1]=Double.parseDouble(searchVille(ville1).getLongitude());
	  		localisation[2]=Double.parseDouble(searchVille(ville2).getLatitude());
			localisation[3]=Double.parseDouble(searchVille(ville2).getLongitude());
			
			double lat1 = Math.toRadians(localisation[0]);
			double lon1 = Math.toRadians(localisation[1]);
			double lat2 = Math.toRadians(localisation[2]);
			double lon2 = Math.toRadians(localisation[3]);
	
	        double earthRadius = 6371.01;
	        earthRadius = earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
			
	        request.setAttribute("ville1", searchVille(ville1).getNomCommune());
			request.setAttribute("ville2", searchVille(ville2).getNomCommune());
			request.setAttribute("erreur", "");
			return (int) earthRadius;
		}else {
			request.setAttribute("erreur", "erreur : choisir deux villes différentes");
		}
		return 0;
	}
	
	public void cmdListeVille(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		int maxlist = 3450;
		int minlist = 3400;
		
		Object listCmd = request.getParameter("listCmd");
		
		if(listCmd != null) {
			maxlist = (int) session.getAttribute("maxlist");
			minlist= (int) session.getAttribute("minlist");
			if(listCmd.equals("plus") && (minlist+50) < getVille().length) {
				maxlist += 50;
				minlist += 50;
			}
			if(listCmd.equals("moins") && minlist!=0 ) {
				maxlist -= 50;
				minlist -= 50;
			}
		}
		session.setAttribute("maxlist", maxlist);
		session.setAttribute("minlist", minlist);
	}
	
	public Ville_france searchVille(String code) {
		Ville_france[] villes = getVille();
		Ville_france focusVille = null;
		for (int index = 0; index < villes.length; index++) {
			if (code.equals(villes[index].getCodeCommune())) {
				focusVille=villes[index];
			}
		}
		return focusVille;
	}
	
	public static String getTemperature(String lat,String lon) {
		HttpURLConnection conn;
		URL url=null;
		int responsecode=200;
		String inline="";
		String temp="";
		try {
			

			url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=e310b94d8d04f4882bf421daa206d618");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(responsecode!=200) {
			System.out.println("erreur acces url");
		}else {
		    Scanner scanner=null;
			try {
				scanner = new Scanner(url.openStream());
				//Write all the JSON data into a string using a scanner
			    while (scanner.hasNext()) {
			       inline += scanner.nextLine();
			    }
			  //Close the scanner
			    scanner.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
			JSONObject obj = new JSONObject(inline);
			JSONObject pageName = obj.getJSONObject("main");
			temp= pageName.get("temp").toString();
		}
		
		return temp;
	}
	
	public static int fromFtoC(String f) {
		int temp = (int) (Float.parseFloat(f)-273);
		return temp;
	}
}
