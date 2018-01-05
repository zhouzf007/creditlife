package com.entrobus.credit.vo.base;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class BsStaticVo implements Serializable {
    private Long id;
    @NotBlank(message = "codeType 必填")
    private String codeType;
    @NotBlank(message = "codeValue 必填")
    private String codeValue;
    @NotBlank(message = "codeName 必填")
    private String codeName;
    @NotBlank(message = "codeDesc 必填")
    private String codeDesc;
    private Integer sortId;
    private String ext;
    private String param;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
