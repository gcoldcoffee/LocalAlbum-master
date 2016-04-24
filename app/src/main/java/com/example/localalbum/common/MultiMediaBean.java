package com.example.localalbum.common;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MultiMediaBean implements Serializable{

    private static final long serialVersionUID = 4499363854946478874L;

    @JSONField(name="multiMediaType")
    public String getMultiMediaType() {
        return _multiMediaType;
    }

    @JSONField(name="multiMediaType")
    public void setMultiMediaType(final String multiMediaType) {
        this._multiMediaType = multiMediaType;
    }

    @JSONField(name="multiMediaUrl")
    public String getMultiMediaUrl() {
        return _multiMediaUrl;
    }

    @JSONField(name="multiMediaUrl")
    public void setMultiMediaUrl(final String multiMediaUrl) {
        this._multiMediaUrl = multiMediaUrl;
    }

    @JSONField(name="multiMediaName")
    public String getMultiMediaName() {
        return _multiMediaName;
    }

    @JSONField(name="multiMediaName")
    public void setMultiMediaName(final String multiMediaName) {
        this._multiMediaName = multiMediaName;
    }

    @JSONField(name="multiMediaId")
    public String getMultiMediaId() {
        return _multiMediaId;
    }

    @JSONField(name="multiMediaId")
    public void setMultiMediaId(final String multiMediaId) {
        this._multiMediaId = multiMediaId;
    }

    @JSONField(name="frontCoverUrl")
    public String getFrontCoverUrl() {
        return _frontCoverUrl;
    }

    @JSONField(name="frontCoverUrl")
    public void setFrontCoverUrl(final String frontCoverUrl) {
        this._frontCoverUrl = frontCoverUrl;
    }

    @JSONField(deserialize = false,serialize = false)
    public String getRelationId() {
        return _relationId;
    }

    @JSONField(deserialize = false,serialize = false)
    public void setRelationId(final String relationId) {
        this._relationId = relationId;
    }

    @JSONField(deserialize = false,serialize = false)
    public String getRelationTpye() {
        return _relationTpye;
    }

    @JSONField(deserialize = false,serialize = false)
    public void setRelationTpye(final String relationTpye) {
        this._relationTpye = relationTpye;
    }

    private String _multiMediaId;//多媒体Id

    private String _multiMediaType;//多媒体类型

    private String _multiMediaUrl;//多媒体链接

    private String _multiMediaName;//多媒体名称

    private String _frontCoverUrl;// 封面

    private String _relationId;// 关联id

    private String _relationTpye;// 关联类型 备用  当 关联id已用  但关联还不够时  用该字段

    @Override
    public String toString() {
        return "MultiMediaBean [_multiMediaId=" + _multiMediaId
                + ", _multiMediaType=" + _multiMediaType + ", _multiMediaUrl="
                + _multiMediaUrl + ", _multiMediaName=" + _multiMediaName
                + ", _frontCoverUrl=" + _frontCoverUrl + ", _relationId="
                + _relationId + ", _relationTpye=" + _relationTpye + "]";
    }
}
