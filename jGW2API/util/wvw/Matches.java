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

public class Matches {
    
    public static class Match {
        
        protected Integer redWorldID;
        protected Integer blueWorldID;
        protected Integer greenWorldID;
        
        protected Match(JSONObject json) {
            this.redWorldID = new Integer(json.getInt("red_world_id"));
            this.blueWorldID = new Integer(json.getInt("blue_world_id"));
            this.greenWorldID = new Integer(json.getInt("green_world_id"));
        }

        public Integer getRedWorldID() {
            return redWorldID;
        }

        public Integer getBlueWorldID() {
            return blueWorldID;
        }

        public Integer getGreenWorldID() {
            return greenWorldID;
        }
        
    }
    
    private HashMap<String,Match> matches;
    
    public Matches(JSONObject json) {
        this.matches = new HashMap();
        JSONArray jsonMatches = json.getJSONArray("wvw_matches");
        for (int i = 0; i<jsonMatches.length();i++) {
            JSONObject tmp = jsonMatches.getJSONObject(i);
            this.matches.put(tmp.getString("wvw_match_id"), new Match(tmp));
        }
    }
    
    public ArrayList<String> getMatchIDs() {
        return new ArrayList(this.matches.keySet());
    }
    
    public Match getMatchByID(String id) {
        return this.matches.get(id);
    }
    
    public HashMap<String,Match> getMatchHashMap() {
        return this.matches;
    }
}
