/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.administrator.smallhappypay.face.model;

import java.io.File;
import java.util.Map;

public interface RequestParams {

    Map<String, File> getFileParams();
    Map<String, String> getStringParams();
    String getJsonParams();
}
