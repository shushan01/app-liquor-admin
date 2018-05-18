package com.app.framework.core.utils;

import java.util.UUID;

public final class CodeUtils {
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
}
