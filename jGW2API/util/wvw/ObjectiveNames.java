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

package jGW2API.util.wvw;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ObjectiveNames {
    
    private HashMap<Integer,String> objectiveNames;
    
    public ObjectiveNames(JSONArray json) {
        this.objectiveNames = new HashMap();
        JSONObject tmp;
        for (int i=0; i<json.length(); i++) {
            tmp = json.getJSONObject(i);
            this.objectiveNames.put(new Integer(tmp.getString("id")), tmp.getString("name"));
        }
    }
        
    public HashMap<Integer,String> getObjectiveHashMap() {
        return this.objectiveNames;
    }
    
    public String getObjectiveNameByID(Integer id) {
        return this.objectiveNames.get(id);
    }
    
    public ArrayList<String> getObjectiveNames() {
        return new ArrayList(this.objectiveNames.values());        
    }
    
    public ArrayList<Integer> getObjectiveIDs() {
        return new ArrayList(this.objectiveNames.keySet());
    }
    
}
