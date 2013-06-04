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

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class Colors {
    
    public static class Color {
        
        public static class MaterialColor {
            private Integer brightness;
            private Double contrast;
            private Integer hue;
            private Double saturation;
            private Double lightness;
            private Integer[] rgb;
            
            public MaterialColor(JSONObject json) {
                this.brightness = new Integer(json.getInt("brightness"));
                this.contrast = new Double(json.getDouble("contrast"));
                this.hue = new Integer(json.getInt("hue"));
                this.lightness = new Double(json.getDouble("lightness"));
                this.saturation = new Double(json.getDouble("saturation"));
                JSONArray tmp = json.getJSONArray("rgb");
                this.rgb = new Integer[3];
                for (int i = 0; i<3;i++) {
                    this.rgb[i] = new Integer(tmp.getInt(i));
                }
            }

            public Integer getBrightness() {
                return brightness;
            }

            public Double getContrast() {
                return contrast;
            }

            public Integer getHue() {
                return hue;
            }

            public Double getSaturation() {
                return saturation;
            }

            public Double getLightness() {
                return lightness;
            }

            public Integer[] getRgb() {
                return rgb;
            }
            
        }
        
        public static final Integer[] BaseRGBValue = {new Integer(128), new Integer(26), new Integer(26)};
        private String colorName;
        private MaterialColor metal;
        private MaterialColor cloth;
        private MaterialColor leather;
        
        public Color(JSONObject json) {
            this.colorName = json.getString("name");
            this.metal = new MaterialColor(json.getJSONObject("metal"));
            this.cloth = new MaterialColor(json.getJSONObject("cloth"));
            this.leather = new MaterialColor(json.getJSONObject("leather"));
        }

        public String getColorName() {
            return colorName;
        }

        public MaterialColor getMetal() {
            return metal;
        }

        public MaterialColor getCloth() {
            return cloth;
        }

        public MaterialColor getLeather() {
            return leather;
        }
        
    }
    
    private HashMap<Integer,Color> colors;
    
    public Colors(JSONObject json) {
        this.colors = new HashMap();
        Iterator<String> keys = json.keySet().iterator();
        String key;
        while(keys.hasNext()) {
            key = keys.next();
            Integer tmp = new Integer(key);
            this.colors.put(tmp, new Color(json.getJSONObject(key)));
        }
    }

    public HashMap<Integer, Color> getColors() {
        return colors;
    }
    
}
