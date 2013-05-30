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
import org.json.JSONArray;
import org.json.JSONObject;

public class MatchDetails {
    
    public static class WvWMap {
        
        public static enum MapType {
            Center,
            GreenHome,
            RedHome,
            BlueHome;
        }
        
        public static class Objective {
            
            protected static enum Owner {
                Red,
                Blue,
                Green;
            }
            
            protected Integer objectiveID;
            protected Owner objectiveOwner;
            protected String guildID;
            
            protected Objective(JSONObject json) {
                this.objectiveID = new Integer(json.getInt("id"));
                this.objectiveOwner = MatchDetails.WvWMap.Objective.Owner.valueOf(json.getString("owner"));
                this.guildID = json.optString("owner_guild");
            }

            public Integer getObjectiveID() {
                return objectiveID;
            }

            public Owner getObjectiveOwner() {
                return objectiveOwner;
            }

            public String getGuildID() {
                return guildID;
            }            
            
        }
        
        protected ArrayList<Objective> objectives;
        protected MapType mapType;
        protected Integer redScore;
        protected Integer blueScore;
        protected Integer greenScore;
        
        protected WvWMap(JSONObject json) {
            this.objectives = new ArrayList();
            this.mapType = MatchDetails.WvWMap.MapType.valueOf(json.getString("type"));
            JSONArray tmp = json.getJSONArray("scores");
            this.redScore = new Integer(tmp.getInt(0));
            this.blueScore = new Integer(tmp.getInt(1));
            this.greenScore = new Integer(tmp.getInt(2));
            tmp = json.getJSONArray("objectives");
            for (int i = 0; i<tmp.length();i++) {
                this.objectives.add(new Objective(tmp.getJSONObject(i)));
            }
        }

        public ArrayList<Objective> getObjectives() {
            return objectives;
        }

        public MapType getMapType() {
            return mapType;
        }

        public Integer getRedScore() {
            return redScore;
        }

        public Integer getBlueScore() {
            return blueScore;
        }

        public Integer getGreenScore() {
            return greenScore;
        }
        
    }
    
    private String matchID;
    private Integer redScore;
    private Integer blueScore;
    private Integer greenScore;
    private ArrayList<WvWMap> maps;
    
    public MatchDetails(JSONObject json) {
        this.maps = new ArrayList();
        this.matchID = json.getString("match_id");
        JSONArray tmp = json.getJSONArray("scores");
        this.redScore = new Integer(tmp.getInt(0));
        this.blueScore = new Integer(tmp.getInt(1));
        this.greenScore = new Integer(tmp.getInt(2));
        tmp = json.getJSONArray("maps");
        for (int i = 0; i<tmp.length();i++) {
            this.maps.add(new WvWMap(tmp.getJSONObject(i)));
        }
    }

    public String getMatchID() {
        return matchID;
    }

    public Integer getRedScore() {
        return redScore;
    }

    public Integer getBlueScore() {
        return blueScore;
    }

    public Integer getGreenScore() {
        return greenScore;
    }

    public ArrayList<WvWMap> getMaps() {
        return maps;
    }
    
    public WvWMap getRedHome() {
        java.util.Iterator<WvWMap> it = this.maps.iterator();
        WvWMap ret = null;
        while(it.hasNext()) {
            WvWMap tmp = it.next();
            if (tmp.mapType == MatchDetails.WvWMap.MapType.RedHome) {
                ret = tmp;
                break;
            }
        }
        return ret;
    }
    
    public WvWMap getGreenHome() {
        java.util.Iterator<WvWMap> it = this.maps.iterator();
        WvWMap ret = null;
        while(it.hasNext()) {
            WvWMap tmp = it.next();
            if (tmp.mapType == MatchDetails.WvWMap.MapType.GreenHome) {
                ret = tmp;
                break;
            }
        }
        return ret;
    }
    
    public WvWMap getBlueHome() {
        java.util.Iterator<WvWMap> it = this.maps.iterator();
        WvWMap ret = null;
        while(it.hasNext()) {
            WvWMap tmp = it.next();
            if (tmp.mapType == MatchDetails.WvWMap.MapType.BlueHome) {
                ret = tmp;
                break;
            }
        }
        return ret;
    }
    
    public WvWMap getEternalBattleground() {
        java.util.Iterator<WvWMap> it = this.maps.iterator();
        WvWMap ret = null;
        while(it.hasNext()) {
            WvWMap tmp = it.next();
            if (tmp.mapType == MatchDetails.WvWMap.MapType.Center) {
                ret = tmp;
                break;
            }
        }
        return ret;
    }
    
}
