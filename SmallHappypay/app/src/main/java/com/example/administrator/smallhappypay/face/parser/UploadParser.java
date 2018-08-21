/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.administrator.smallhappypay.face.parser;

import com.example.administrator.smallhappypay.face.exception.FaceError;

import org.json.JSONException;
import org.json.JSONObject;

public class UploadParser implements Parser<Integer> {
    @Override
    public Integer parse(String json) throws FaceError {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int ret = jsonObject.optInt("ret");

            return ret;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
