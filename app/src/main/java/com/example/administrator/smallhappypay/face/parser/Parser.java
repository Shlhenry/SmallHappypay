/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.example.administrator.smallhappypay.face.parser;

import com.example.administrator.smallhappypay.face.exception.FaceError;
import com.example.administrator.smallhappypay.face.exception.FaceError;

/**
 * JSON解析
 * @param <T>
 */
public interface Parser<T> {
    T parse(String json) throws FaceError, FaceError;
}
