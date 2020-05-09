package com.tangyiner.world.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
public class Countrylanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CountryCode")
    private String CountryCode;
    @TableField("Language")
    private String Language;
    @TableField("IsOfficial")
    private String IsOfficial;
    @TableField("Percentage")
    private Float Percentage;


    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String IsOfficial) {
        this.IsOfficial = IsOfficial;
    }

    public Float getPercentage() {
        return Percentage;
    }

    public void setPercentage(Float Percentage) {
        this.Percentage = Percentage;
    }

    @Override
    public String toString() {
        return "Countrylanguage{" +
        ", CountryCode=" + CountryCode +
        ", Language=" + Language +
        ", IsOfficial=" + IsOfficial +
        ", Percentage=" + Percentage +
        "}";
    }
}
