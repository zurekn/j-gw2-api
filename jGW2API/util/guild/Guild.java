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

package jGW2API.util.guild;

import java.io.IOException;
import java.util.HashMap;
import org.json.JSONObject;

public class Guild {
    
    static private HashMap<String,Guild> guildMap = new HashMap();
    
    public static class Emblem {
        private Integer backgroundID;
        private Integer foregroundID;
        //TODO flags
        private Integer backgroundColorID;
        private Integer foregroundPrimaryColorID;
        private Integer foregroundSecondaryColorID;
        
        public Emblem(JSONObject json) {
            this.backgroundID = new Integer(json.getInt("background_id"));
            this.foregroundID = new Integer(json.getInt("foreground_id"));
            this.backgroundColorID = new Integer(json.getInt("background_color_id"));
            this.foregroundPrimaryColorID = new Integer(json.getInt("foreground_primary_color_id"));
            this.foregroundSecondaryColorID = new Integer(json.getInt("foreground_secondary_color_id"));
        }

        public Integer getBackgroundID() {
            return backgroundID;
        }

        public Integer getForegroundID() {
            return foregroundID;
        }

        public Integer getBackgroundColorID() {
            return backgroundColorID;
        }

        public Integer getForegroundPrimaryColorID() {
            return foregroundPrimaryColorID;
        }

        public Integer getForegroundSecondaryColorID() {
            return foregroundSecondaryColorID;
        }        
        
    }
    
    private String guildID;
    private String guildName;
    private String guildTag;
    private Emblem guildEmblem;    
    
    public Guild(JSONObject json) throws GuildNotFoundException{
        if (!json.has("error")) {            
            this.guildID = json.getString("guild_id");
            this.guildName = json.getString("guild_name");
            this.guildTag = json.getString("tag");
            this.guildEmblem = new Emblem(json.getJSONObject("emblem"));
            Guild.guildMap.put(guildID, this);
        }
        else {
            throw new GuildNotFoundException(json);
        }
    }

    public String getGuildID() {
        return guildID;
    }

    public String getGuildName() {
        return guildName;
    }

    public String getGuildTag() {
        return guildTag;
    }

    public Emblem getGuildEmblem() {
        return guildEmblem;
    }
    
    public static Guild getGuildByID(String guildID) throws IOException, GuildNotFoundException {
        if(!Guild.guildMap.containsKey(guildID)) {
            return new Guild(jGW2API.jGW2API.getGuildDetails(guildID, null));            
        }
        return Guild.guildMap.get(guildID);
    }
}
