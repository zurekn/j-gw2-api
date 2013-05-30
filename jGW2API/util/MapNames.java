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

package jGW2API.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class MapNames {
    
    private HashMap<Integer,String> mapNames;
    
    public MapNames(JSONArray json) {
        this.mapNames = new HashMap();
        for(int i = 0; i<json.length();i++) {
            JSONObject tmp = json.getJSONObject(i);
            this.mapNames.put(new Integer(tmp.getString("id")), tmp.getString("name"));
        }     
    }
    
    public HashMap<Integer,String> getMapHashMap() {
        return this.mapNames;
    }
    
    public String getMapNameByID(Integer id) {
        return this.mapNames.get(id);
    }
    
    public Integer getIDByMapName(String name) {
        if (!this.mapNames.containsValue(name)) {
            return null;
        }
        java.util.Iterator<Integer> it = this.mapNames.keySet().iterator();
        while(it.hasNext()) {
            Integer r = it.next();
            if (this.mapNames.get(r).equals(name)) {
                return r;
            }
        }
        return null;
    }
    
    public ArrayList<String> getMapNames() {
        return new ArrayList(this.mapNames.values());        
    }
    
    public ArrayList<Integer> getMapIDs() {
        return new ArrayList(this.mapNames.keySet());
    }
}
