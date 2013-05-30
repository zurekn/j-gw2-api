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

package jGW2API.util;

import jGW2API.jGW2API;
import jGW2API.util.event.*;
import jGW2API.util.item.*;
import jGW2API.util.wvw.*;
import java.io.IOException;

public abstract class HighLevelAPI {
    
    public static enum Languages {
        en,
        de,
        fr,
        es;
    }    
    
    public static MapNames getMapNames(Languages lang) throws IOException {
        return new MapNames(jGW2API.getMapNames(lang.toString()));
    }
    
    public static WorldNames getWorldNames(Languages lang) throws IOException {
        return new WorldNames(jGW2API.getWorldNames(lang.toString()));
    }
    
    public static EventNames getEventNames(Languages lang) throws IOException {
        return new EventNames(jGW2API.getEventNames(lang.toString()));
    }
    
    public static Events getEvent(Integer worldID, String eventID, Integer mapID) throws IOException {
        return new Events(jGW2API.getEvents(worldID.toString(), eventID, mapID.toString()));
    }
    
    public static MatchDetails getWvWMatchDetails(String matchID) throws IOException {
        return new MatchDetails(jGW2API.getWvWMatchDetails(matchID));
    }
    
    public static Matches getWvWMatches() throws IOException {
        return new Matches(jGW2API.getWvWMatches());
    }
    
    public static ObjectiveNames getWvWObjectiveNames(Languages lang) throws IOException {
        return new ObjectiveNames (jGW2API.getWvWObjectiveNames(lang.toString()));
    }
    
    public static ItemList getItems() throws IOException {
        return new ItemList(jGW2API.getItems().getJSONArray("items"));
    }
    
    public static Item getItemDetails(Integer itemID, Languages lang) throws IOException {
        return Item.getItem(jGW2API.getItemDetails(itemID.toString(), lang.toString()));
    }
    
    public static Recipes getRecipes() throws IOException {
        return new Recipes(jGW2API.getRecipes());
    }
    
    public static RecipeDetails getRecipeDetails(Integer recipeID, Languages lang) throws IOException {
        return new RecipeDetails(jGW2API.getRecipeDetails(recipeID.toString(), lang.toString()));
    }
 
}
