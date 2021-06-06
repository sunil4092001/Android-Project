package com.example.asia.country;

public class countryModel {

   // private static String flag;
    //String flag;
    String country_name , country_capital,country_region, flag;
    String country_popul, country_lang;

    public String getCountry_popul() {
        return country_popul;
    }

    public void setCountry_popul(String country_popul) {
        this.country_popul = country_popul;
    }

    public String getCountry_lang() {
        return country_lang;
    }

    public void setCountry_lang(String country_lang) {
        this.country_lang = country_lang;
    }

    public countryModel
            (String country_name, String country_capital,
             String country_region, String flag,
             String country_popul, String country_lang)
    {
        this.country_name = country_name;
        this.country_capital = country_capital;
        this.country_region = country_region;
        this.flag = flag;
        this.country_popul =country_popul;
        this.country_lang =country_lang;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_capital() {
        return country_capital;
    }

    public void setCountry_capital(String country_capital) {
        this.country_capital = country_capital;
    }

    public String getCountry_region() {
        return country_region;
    }

    public void setCountry_region(String country_region) {
        this.country_region = country_region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
/*
    public countryModel(String flag, String country_name, String country_capital, String country_region){

        this.flag =flag;
        this.country_name =country_name;
        this.country_capital = country_capital;
    }

        public static String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_capital() {
        return country_capital;
    }

    public void setCountry_capital(String country_capital) {
        this.country_capital = country_capital;
    }*/
}
