
/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.administrator.smallhappypay.face.parser;

import android.util.Log;

import com.example.administrator.smallhappypay.face.exception.FaceError;
import com.example.administrator.smallhappypay.face.model.OnlineFaceliveResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OnlineLivenessResultParser implements Parser<OnlineFaceliveResult> {

    @Override
    public OnlineFaceliveResult parse(String json) throws FaceError {

        Log.i("OnlineFaceliveResult", "OnlineFaceliveResult->" + json);
        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.has("error_code")) {
                FaceError error = new FaceError(jsonObject.optInt("error_code"),
                        jsonObject.optString("error_msg"));
                throw error;
            }

            OnlineFaceliveResult livenessResult = new OnlineFaceliveResult();
            livenessResult.setLogId(jsonObject.optLong("log_id"));
            livenessResult.setJsonRes(json);

            JSONArray results = jsonObject.optJSONArray("result");

            if (results != null) {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject livenessJOSNObject = results.optJSONObject(i);
                    if (livenessJOSNObject != null) {
                        double faceliveness = livenessJOSNObject.optDouble("faceliveness");
                        livenessResult.getFacelivenessValue().add(faceliveness);
                    }
                }
            }

            return livenessResult;
        } catch (JSONException e) {
            e.printStackTrace();
            FaceError error = new FaceError(FaceError.ErrorCode.JSON_PARSE_ERROR,
                    "Json parse error:" + json, e);
            throw error;
        }
    }
}
