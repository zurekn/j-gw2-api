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

import org.json.JSONObject;

public class GuildNotFoundException extends Exception {
    private Integer error;
    private Integer product;
    private Integer module;
    private Integer line;
    private String text;
    
    public GuildNotFoundException(JSONObject json) {
        this.error = new Integer(json.getInt("error"));
        this.product = new Integer(json.getInt("product"));
        this.module = new Integer(json.getInt("module"));
        this.line = new Integer(json.getInt("line"));
        this.text = json.optString("text");
    }
    
    @Override
    public String getMessage() {
        return "Guild not found.";
    }

    public Integer getError() {
        return error;
    }

    public Integer getProduct() {
        return product;
    }

    public Integer getModule() {
        return module;
    }

    public Integer getLine() {
        return line;
    }

    public String getText() {
        return text;
    }
    
}
