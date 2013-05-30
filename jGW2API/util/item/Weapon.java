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

public class Weapon extends Item {
    
    public static enum WeaponType {
        Axe,
        Dagger,
        Focus,
        Greatsword,
        Hammer,
        Harpoon,
        LongBow,
        Mace,
        Pistol,
        Rifle,
        Scepter,
        Shield,
        ShortBow,
        Speargun,
        Staff,
        Sword,
        Torch,
        Toy,
        Trident,
        TwoHandedToy,
        Warhorn;
    }
    
    public static enum DamageType {
        Fire,
        Ice,
        Lightning,
        Physical;
    }
    
    private Integer minPower;
    private Integer maxPower;
    private Integer defense;
    private ArrayList<InfusionSlots> infusionSlots;
    private InfixUpgrade infixUpgrade;
    private String suffixItemID;
    
    public Weapon(JSONObject json) {
        super(json);
        this.infusionSlots = new ArrayList();
        JSONObject weapon = json.getJSONObject("weapon");
        this.minPower = new Integer(weapon.getString("min_power"));
        this.maxPower = new Integer(weapon.getString("max_power"));
        this.defense = new Integer(weapon.getString("defense"));
        this.infixUpgrade = new InfixUpgrade(weapon.getJSONObject("infix_upgrade"));
        this.suffixItemID = weapon.getString("suffix_item_id");
        JSONArray inf = weapon.getJSONArray("infusion_slots");
        for (int i = 0; i<inf.length();i++) {
            this.infusionSlots.add(new InfusionSlots(inf.getJSONObject(i)));
        }
    }
}
