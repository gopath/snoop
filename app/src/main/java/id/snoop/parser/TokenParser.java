package id.snoop.parser;

import org.json.JSONException;
import org.json.JSONObject;

import id.snoop.model.TokenBean;

public class TokenParser {
    public TokenBean parse(JSONObject jObj){
        TokenBean token = new TokenBean();

        try {
            token.setScope(jObj.getString("scope"));
            token.setToken_type(jObj.getString("token_type"));
            token.setExpires_in(jObj.getInt("expires_in"));
            token.setAccess_token(jObj.getString("access_token"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return token;
    }
}
