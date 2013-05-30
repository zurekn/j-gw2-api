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

public class UpgradeComponent extends Item {
    
    public static enum UpgradeType {
        Default,
        Gem,
        Rune,
        Sigil;
    }
    
    public static enum UpgradeFlags {
        HeavyArmor,
        LightArmor,
        MediumArmor,
        Trinket,
        Axe,
        LongBow,
        ShortBow,
        Dagger,
        Focus,
        Greatsword,
        Hammer,
        Harpoon,
        Mace,
        Pistol,
        Rifle,
        Scepter,
        Shield,
        Speargun,
        Staff,
        Sword,
        Torch,
        Trident,
        Warhorn;
    }
    
    public static enum InfusionUpgradeFlags {
        Defense,
        Offense,
        Utility;
    }
    
    private UpgradeType upgradeType;
    private ArrayList<UpgradeFlags> upgradeFlags;
    private ArrayList<InfusionUpgradeFlags> infusionUpgradeFlags;
    private InfixUpgrade infixUpgrade;
    private String suffix;
    
    public UpgradeComponent(JSONObject json) {
        super(json);
        JSONObject up = json.getJSONObject("upgrade_component");
        this.upgradeType = UpgradeComponent.UpgradeType.valueOf(up.getString("type"));
        this.suffix = up.getString("suffix");
        this.infixUpgrade = new InfixUpgrade(up.getJSONObject("infix_upgrade"));
        JSONArray tmp;
        tmp = up.getJSONArray("flags");
        this.upgradeFlags = new ArrayList();
        for (int i = 0; i<tmp.length();i++) {
            this.upgradeFlags.add(UpgradeComponent.UpgradeFlags.valueOf(tmp.getString(i)));
        }
        tmp = up.getJSONArray("infusion_upgrade_flags");
        this.infusionUpgradeFlags = new ArrayList();
        for (int i = 0; i<tmp.length();i++) {
            this.infusionUpgradeFlags.add(UpgradeComponent.InfusionUpgradeFlags.valueOf(tmp.getString(i)));
        }
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    public ArrayList<UpgradeFlags> getUpgradeFlags() {
        return upgradeFlags;
    }

    public ArrayList<InfusionUpgradeFlags> getInfusionUpgradeFlags() {
        return infusionUpgradeFlags;
    }

    public InfixUpgrade getInfixUpgrade() {
        return infixUpgrade;
    }

    public String getSuffix() {
        return suffix;
    }
    
}
