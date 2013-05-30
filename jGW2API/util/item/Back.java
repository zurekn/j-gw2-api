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

public class Back extends Item {
    
    private String suffixItemID;
    private ArrayList<InfusionSlots> infusionSlots;
    private InfixUpgrade infixUpgrade;
    
    public Back(JSONObject json) {
        super(json);
        JSONObject back = json.getJSONObject("back");
        this.suffixItemID = back.getString("suffix_item_id");
        this.infixUpgrade = new InfixUpgrade(back.getJSONObject("infix_upgrade"));
        JSONArray infSlots = back.getJSONArray("infusion_slots");
        this.infusionSlots = new ArrayList();
        for (int i = 0; i<infSlots.length();i++) {
            this.infusionSlots.add(new InfusionSlots(infSlots.getJSONObject(i)));
        }
    }

    public String getSuffixItemID() {
        return suffixItemID;
    }

    public ArrayList<InfusionSlots> getInfusionSlots() {
        return infusionSlots;
    }

    public InfixUpgrade getInfixUpgrade() {
        return infixUpgrade;
    }
       
}
