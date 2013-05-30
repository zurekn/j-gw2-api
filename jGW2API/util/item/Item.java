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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.misc.BASE64Encoder;

public abstract class Item {
    public static enum ItemType {
        Armor,
        Back,
        Bag,
        Consumable,
        Container,
        CraftingMaterial,
        Gathering,
        Gizmo,
        Minipet,
        Tool,
        Trinket,
        Trophy,
        UpgradeComponent,
        Weapon;
    }
    
    public static enum Rarity {
        Junk,
        Basic,
        Fine,
        Masterwork,
        Rare,
        Exotic,
        Ascended,
        Legendary;
    }
    
    public static enum GameTypes {
        Activity,
        Dungeon,
        Pve,
        Pvp,
        PvpLobby,
        Wvw;
    }
    
    public static enum Restrictions {
        Asura,
        Charr,
        Human,
        Norn,
        Sylvari;
    }
    
    public static enum ItemFlags {
        AccountBound,
        HideSuffix,
        NoMysticForge,
        NoSalvage,
        NoSell,
        NotUpgradeable,
        NoUnderwater,
        SoulbindOnAcquire,
        SoulbindOnUse,
        Unique;
    }
    
    protected Integer itemID;
    protected String name;
    protected String description;
    protected ItemType type;
    protected Rarity rarity;
    protected ArrayList<ItemFlags> itemFlags;
    protected ArrayList<GameTypes> gametypes;
    protected ArrayList<Restrictions> restrictions;
    
    protected Item(JSONObject json) {
        this.itemID = new Integer(json.getString("item_id"));
        this.name = json.getString("name");
        this.description = json.getString("description");
        this.type = Item.ItemType.valueOf(json.getString("type"));
        this.rarity = Item.Rarity.valueOf(json.getString("rarity"));
        JSONArray g = json.getJSONArray("game_types");
        JSONArray f = json.getJSONArray("flags");
        JSONArray r = json.getJSONArray("restrictions");
        this.gametypes = new ArrayList();
        this.itemFlags = new ArrayList();
        this.restrictions = new ArrayList();
        for (int i = 0; i<g.length();i++) {
            this.gametypes.add(Item.GameTypes.valueOf(g.getString(i)));
        }
        for (int i = 0; i<f.length();i++) {
            this.itemFlags.add(Item.ItemFlags.valueOf(f.getString(i)));
        }
        for (int i = 0; i<r.length();i++) {
            this.restrictions.add(Item.Restrictions.valueOf(r.getString(i)));
        }
    }
    
    public static Item getItem(JSONObject json) {
        switch(json.getString("type")) {
            case "Armor": return new Armor(json);  
            case "Back": return new Back(json);
            case "Bag": return new Bag(json);
            case "Consumable": return new Consumable(json);
            case "Container": return new Container(json);
            case "CraftingMaterial": return new CraftingMaterial(json);
            case "Gathering": return new Gathering(json);
            case "Gizmo": return new Gizmo(json);
            case "MiniPet": return new MiniPet(json);
            case "Tool": return new Tool(json);
            case "Trinket": return new Trinket(json);
            case "Trophy": return new Trophy(json);
            case "UpgradeComponent": return new UpgradeComponent(json);
            case "Weapon": return new Weapon(json);
            default: return null;
        }
    }
    
    public ItemType getItemType() {
        return this.type;
    }
    
    public Integer getItemID() {
        return this.itemID;
    }
    
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public ArrayList<ItemFlags> getItemFlags() {
        return itemFlags;
    }

    public ArrayList<GameTypes> getGametypes() {
        return gametypes;
    }

    public ArrayList<Restrictions> getRestrictions() {
        return restrictions;
    }
    
    public String getChatLink() {
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] id =  ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.itemID.intValue()).array();
        byte[] toEncode = new byte[id.length+2];
        toEncode[0] = 2;
        toEncode[1] = 1;
        for (int i = 2; i<toEncode.length;i++) {
            toEncode[i] = id[i-2];
        }
        return "[&"+encoder.encode(toEncode)+"]";
    }
}
