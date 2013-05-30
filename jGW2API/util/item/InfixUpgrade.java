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

package jGW2API.util.item;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class InfixUpgrade {
    
    public static class Buff {
        public Integer skillID;
        public String description;
        
        public Buff(JSONObject json) {
            if (json != null) {
                this.skillID = new Integer (json.getString("skill_id"));
                this.description = json.getString("description");
            }
            else {
                this.skillID = null;
                this.description = null;
            }
        }
    };
    
    public static class Attribute {
        
        public static enum Attributes {
            ConditionDamage,
            CritDamage,
            Healing,
            Power,
            Precision,
            Toughness,
            Vitality;
        }
        
        private Attributes attribute;
        private String modifier;
        
        protected Attribute(JSONObject json) {
            this.attribute = Attribute.Attributes.valueOf(json.getString("attribute"));
            this.modifier = json.getString("modifier");
        }
    }
    
    private Buff buff;
    private ArrayList<Attribute> attributes;
    
    public InfixUpgrade(JSONObject json) {
        this.buff = new Buff(json.optJSONObject("buff"));
        JSONArray att = json.getJSONArray("attributes");
        this.attributes = new ArrayList();
        for (int i = 0; i<att.length(); i++) {
            this.attributes.add(new Attribute(att.getJSONObject(i)));
        }
    }

    public Buff getBuff() {
        return buff;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }
    
}
