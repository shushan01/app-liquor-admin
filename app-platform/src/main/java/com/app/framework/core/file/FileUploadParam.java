package com.app.framework.core.file;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FileUploadParam {
    @NotNull
    private Long ownerId;
    @NotNull
    private String type;
}
