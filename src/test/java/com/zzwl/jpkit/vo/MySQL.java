package com.zzwl.jpkit.vo;

import com.zzwl.jpkit.anno.JPConfig;
import com.zzwl.jpkit.anno.JParse;
import com.zzwl.jpkit.anno.JRename;
import com.zzwl.jpkit.core.JSON;
import com.zzwl.jpkit.plug.MySQLPlug;
import com.zzwl.jpkit.plugs.BasePlug;
import com.zzwl.jpkit.plugs.BigDecimalPlug;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@JPConfig(plugs = {BigDecimalPlug.class, MySQLPlug.class})
public class MySQL {
    @JRename("server")
    private String serverName;
    private String version;
    @JParse(method = BasePlug.GET_OBJECT)
    private BigDecimal bigDecimal;
    @JParse(method = BasePlug.GET_ARR)
    private BigDecimal[] bigs;
    @JParse(method = BasePlug.GET_LIST)
    private List<BigDecimal> bigDecimals;
    @JParse(method = BasePlug.GET_MAP)
    private Map<String, BigDecimal> map;
    @JParse(method = BasePlug.GET_LIST, pos = 1)
    private List<MySQL> mySQLList;
    @JParse(method = BasePlug.GET_ARR, pos = 1)
    private Type[] types;

    public MySQL() {
    }

    public MySQL(String serverName, String version, BigDecimal bigDecimal) {
        this.serverName = serverName;
        this.version = version;
        this.bigDecimal = bigDecimal;
    }

    public MySQL(String serverName, String version, BigDecimal bigDecimal, BigDecimal[] bigs) {
        this.serverName = serverName;
        this.version = version;
        this.bigDecimal = bigDecimal;
        this.bigs = bigs;
    }

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types) {
        this.types = types;
    }

    public List<BigDecimal> getBigDecimals() {
        return bigDecimals;
    }

    public List<MySQL> getMySQLList() {
        return mySQLList;
    }

    public Map<String, BigDecimal> getMap() {
        return map;
    }

    public void setMap(Map<String, BigDecimal> map) {
        this.map = map;
    }

    public BigDecimal[] getBigs() {
        return bigs;
    }

    public void setBigs(BigDecimal[] bigs) {
        this.bigs = bigs;
    }

    public void setMySQLList(List<MySQL> mySQLList) {
        this.mySQLList = mySQLList;
    }

    public void setBigDecimals(List<BigDecimal> bigDecimals) {
        this.bigDecimals = bigDecimals;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return JSON.stringify(this).pretty();
    }
}
