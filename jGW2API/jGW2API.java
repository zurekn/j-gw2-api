/*The MIT License (MIT)

Copyright (c) <2013> <Keven "Varonth" Schulz>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package jGW2API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.*;

public class jGW2API {
    
    /*
     * Static fields
     */
    
    public static final String Standard_URL = "https://api.guildwars2.com";
    public static String API_Version = "/v1/";
    
    private static SSLSocketFactory socketFactory = null;
    
    /*
     * Member fields
     */
    
    private HttpsURLConnection httpsConnection;
    
    /*
     * Constructors
     */
    
    public jGW2API(URL url) throws IOException {
        jGW2API.init();
        this.httpsConnection = (HttpsURLConnection) url.openConnection();
        this.httpsConnection.setSSLSocketFactory(jGW2API.socketFactory);
    }
    
    /*
     * Methods
     */
    
    public JSONObject getJSONObject() throws IOException {      
        BufferedReader buf = new BufferedReader(new InputStreamReader(this.httpsConnection.getInputStream()));
        String jsonString = "";
        String read;
        while ((read = buf.readLine()) != null) {
            jsonString += read;
        }
        return new JSONObject(jsonString);        
    }
    
    public HttpsURLConnection getHttpsURLConnection() {
        return this.httpsConnection;
    }
    
    public JSONArray getJSONArray() throws IOException {          
        BufferedReader buf = new BufferedReader(new InputStreamReader(this.httpsConnection.getInputStream()));
        String jsonString = "";
        String read;
        while ((read = buf.readLine()) != null) {
            jsonString += read;
        }
        return new JSONArray(jsonString);
    }
    
    /*
     * Static methods
     */
    
    public static void init() {
        if (jGW2API.socketFactory == null) {
            jGW2API.socketFactory = StartComSSLSocketFactory.getFactory();
        }
    }
    
    static public JSONObject getItems() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"items.json")).getJSONObject();
    }
    
    static public JSONObject getItemDetails(String id) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"item_details.json?item_id="+id)).getJSONObject();
    }
    
    static public JSONObject getItemDetails(String id, String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"item_details.json?item_id="+id+"&lang="+lang)).getJSONObject();
    }
    
    static public JSONObject getRecipes() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"recipes.json")).getJSONObject();
    }
    
    static public JSONObject getRecipeDetails(String id) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"recipe_details.json?recipe_id="+id)).getJSONObject();
    }
    
    static public JSONObject getRecipeDetails(String id, String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"recipe_details.json?recipe_id="+id+"&lang="+lang)).getJSONObject();
    }
    
    static public JSONObject getWvWMatches() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"wvw/matches.json")).getJSONObject();
    }
    
    static public JSONObject getWvWMatchDetails(String id) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"wvw/match_details.json?match_id="+id)).getJSONObject();
    }
    
    static public JSONObject getWvWObjectiveNames() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"wvw/objective_names.json")).getJSONObject();
    }
    
    static public JSONObject getWvWObjectiveNames(String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"wvw/objective_names.json?lang="+lang)).getJSONObject();
    }
    
    static public JSONObject getEvents(String w_id, String e_id, String m_id) 
     throws IOException, MalformedURLException {
        String args = "";
        if (w_id != null) {
            args += "?world_id="+w_id;
        }
        if (w_id != null && e_id != null) {
            args += "&event_id="+e_id;
        }
        else if (e_id != null) {
            args += "?event_id="+e_id;
        }
        if (w_id != null && e_id != null && m_id != null 
                || w_id != null && e_id == null && m_id != null
                || w_id == null && e_id != null && m_id != null ) {
            args += "&map_id="+m_id;
        }
        else if (w_id == null && e_id == null && m_id != null) {
            args += "?m_id="+m_id;
        }
        return new jGW2API(new URL(jGW2API.Standard_URL + jGW2API.API_Version+"events.json"+args)).getJSONObject();
    }
    
    static public JSONArray getEventNames() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"event_names.json")).getJSONArray();
    }
    
    static public JSONArray getWorldNames() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"world_names.json")).getJSONArray();
    }
    
    static public JSONArray getMapNames() throws IOException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"map_names.json")).getJSONArray();
    }
    
    static public JSONArray getEventNames(String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"event_names.json?lang="+lang)).getJSONArray();
    }
    
    static public JSONArray getWorldNames(String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"world_names.json?lang="+lang)).getJSONArray();
    }
    
    static public JSONArray getMapNames(String lang) throws IOException, MalformedURLException {
        return new jGW2API(new URL(jGW2API.Standard_URL+jGW2API.API_Version+"map_names.json?lang="+lang)).getJSONArray();
    }
}
