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


public class Armor extends Item {
    
    public enum ArmorType {
        Boots,
        Coat,
        Gloves,
        Helm,
        HelmAquatic,
        Leggings,
        Shoulders;
    }
    
    public enum WeightClass {
        Heavy,
        Medium,
        Light;
    }
    
    private Integer defense;
    private String suffixItemID;
    private ArrayList<InfusionSlots> infusionSlots;
    private InfixUpgrade infixUpgrade;
    private ArmorType armorType;
    private WeightClass weight;
    
    public Armor(JSONObject json) {
        super(json);        
        JSONObject armor = json.getJSONObject("armor");
        this.defense = new Integer(armor.getString("defense"));
        this.suffixItemID = armor.getString("suffix_item_id");
        this.armorType = Armor.ArmorType.valueOf(armor.getString("type"));
        this.weight = Armor.WeightClass.valueOf(armor.getString("weight_class"));
        this.infixUpgrade = new InfixUpgrade(armor.getJSONObject("infix_upgrade"));
        JSONArray infSlots = armor.getJSONArray("infusion_slots");
        this.infusionSlots = new ArrayList();
        for (int i = 0; i<infSlots.length();i++) {
            this.infusionSlots.add(new InfusionSlots(infSlots.getJSONObject(i)));
        }
    }

    public Integer getDefense() {
        return defense;
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

    public ArmorType getArmorType() {
        return armorType;
    }

    public WeightClass getWeight() {
        return weight;
    }

}
