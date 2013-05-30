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

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeDetails {
    
    public static enum RecipeType {
        Axe,
        Dagger,
        Hammer,
        Greatsword,
        Mace,
        Shield,
        Sword,
        Harpoon,
        LongBow,
        Pistol,
        Rifle,
        ShortBow,
        Speargun,
        Torch,
        Warhorn,
        Amulet,
        Focus,
        Potion,
        Scepter,
        Staff,
        Trident,
        Dessert,
        Dye,
        Feast,
        IngredientCooking,
        Meal,
        Snack,
        Soup,
        Seasoning,
        Earring,
        Ring,
        Boots,
        Coat,
        Gloves,
        Helm,
        Insignia,
        Leggings,
        Shoulders,
        Bag,
        Inscription,
        Component,
        Consumable,
        Refinement,
        UpgradeComponent,
        Bulk;
    }
    
    private Integer recipeID;
    private Integer outputItemID;
    private Integer outputItemCount;
    private Integer minRating;
    private Integer timeToCraftMs;
    private RecipeType recipteType; 
    private HashMap<Integer,Integer> ingredients;
    
    public RecipeDetails(JSONObject json) {
        this.ingredients = new HashMap();
        this.recipeID = new Integer(json.getString("recipe_id"));
        this.outputItemID = new Integer(json.getString("output_item_id"));
        this.minRating = new Integer(json.getString("min_rating"));
        this.outputItemCount = new Integer(json.getString("output_item_count"));
        this.timeToCraftMs = new Integer(json.getString("time_to_craft_ms"));
        this.recipteType = RecipeDetails.RecipeType.valueOf(json.getString("type"));
        JSONArray ing = json.getJSONArray("ingredients");
        for (int i = 0; i<ing.length(); i++) {
            JSONObject tmp = ing.getJSONObject(i);
            this.ingredients.put(new Integer(tmp.getString("item_id")), new Integer(tmp.getString("count")));
        }
    }

    public Integer getRecipeID() {
        return recipeID;
    }

    public Integer getOutputItemID() {
        return outputItemID;
    }

    public Integer getOutputItemCount() {
        return outputItemCount;
    }

    public Integer getMinRating() {
        return minRating;
    }

    public Integer getTimeToCraftMs() {
        return timeToCraftMs;
    }

    public RecipeType getRecipteType() {
        return recipteType;
    }

    public HashMap<Integer, Integer> getIngredients() {
        return ingredients;
    }
    
}
