package wanted;

//Importe
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIaccess {
	// Liest Text aus einem Zeicheneingabestrom und puffert die Zeichen, um ein
	// effizientes Lesen von Zeichen, Arrays und Zeilen zu ermöglichen.
	// Variablen für das Empfangen der Daten
	private static HttpURLConnection connection;
	BufferedReader reader;
	String line;
	StringBuffer fbiContent = new StringBuffer();

	// Variablen für die Verarbeitung der empfangenen Daten der API
	String page;
	JSONObject data, item;
	JSONArray arrayItem, arrayImage;

	public APIaccess() {
		// zufällige Auswahl der Seite
		Random random = new Random();
		int randomValue = random.nextInt(48);
		page = "?page=" + randomValue;

		try {
			URL url = new URL("https://api.fbi.gov/wanted/v1/list" + page);
			// Konvertierung (HttpURLConnection), gibt eine Instanz der Verbindung zurück.
			connection = (HttpURLConnection) url.openConnection();
			// Setze requestProperty auf JSON
			connection.setRequestProperty("Accept", "application/json");
			// Setze request GET
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			// Gibt den Status der Verbindung zurück
			int status = connection.getResponseCode();

			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					fbiContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					fbiContent.append(line);

					// Der String content wird zu einem JSONObject umgewandelt
					data = new JSONObject(fbiContent.toString());
					// Das JSONObject gibt ein JSONArray zurück
					arrayItem = data.getJSONArray("items");

					// zufällige Auswahl eines Kriminellen
					Random randomGetCr = new Random();
					int randomCriminal = randomGetCr.nextInt(arrayItem.length() + 1);

					item = arrayItem.getJSONObject(randomCriminal);
					// Das JSONArray für den Musghot
					arrayImage = item.getJSONArray("images");
				}
				reader.close();
			}
		} catch (MalformedURLException excMal) {
			excMal.printStackTrace();
		} catch (IOException excIO) {
			excIO.printStackTrace();
		} catch (JSONException JSONobject) {
			JSONobject.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}

	protected String getCriminal() {
		String criminal = null;

		// Auswahl des Kriminellen
		try {
			criminal = item.getString("title");
		} catch (JSONException eCriminal) {
			eCriminal.printStackTrace();
		}

		// Gibt den Namen des Kriminellen zurück
		return (criminal);
	}

	protected String getMugshotUrl() {
		final int index = 0;
		JSONObject image;
		String mugshotUrl = null;

		// Auswahl des mugshots
		try {
			image = arrayImage.getJSONObject(index);
			mugshotUrl = image.getString("original");
		} catch (JSONException eMugshot) {
			eMugshot.printStackTrace();
		}

		// Gibt die URL für den mugshot zurück
		return (mugshotUrl);
	}

}
