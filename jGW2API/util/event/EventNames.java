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

package jGW2API.util.event;

import java.util.HashMap;
import java.util.Set;
import org.json.JSONArray;

public class EventNames {
    
    private HashMap<String,String> eventNames;
    
    public EventNames(JSONArray json) {
        this.eventNames = new HashMap();
        for (int i = 0; i<json.length(); i++) {
            this.eventNames.put(json.getJSONObject(i).getString("id"), json.getJSONObject(i).getString("name"));
        }
    }
    
    public HashMap<String,String> getHashMap() {
        return this.eventNames;
    }
    
    public Set<String> getIDs() {
        return this.eventNames.keySet();
    }
    
    public String getName(String id) {
        return this.eventNames.get(id);
    }
    
    public String getIDByEventName(String name) {
        if (!this.eventNames.containsValue(name)) {
            return null;
        }
        java.util.Iterator<String> it = this.eventNames.keySet().iterator();
        while(it.hasNext()) {
            String r = it.next();
            if (this.eventNames.get(r).equals(name)) {
                return r;
            }
        }
        return null;
    }
}
