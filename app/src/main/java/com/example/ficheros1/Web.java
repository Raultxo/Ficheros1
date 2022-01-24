package com.example.ficheros1;

public class Web
{
    private String nomWeb;
    private String urlWeb;
    private String logoWeb;
    private String idWeb;

    public Web(String nomWeb,String urlWeb,String logoWeb,String idWeb)
    {
        this.nomWeb = nomWeb;
        this.urlWeb = urlWeb;
        this.logoWeb = logoWeb;
        this.idWeb = idWeb;
    }

    public String getUrlWeb()
    {
        return urlWeb;
    }

    public String getLogoWeb()
    {
        return logoWeb;
    }

    public String getIdWeb()
    {
        return idWeb;
    }

    public String toString()
    {
        return nomWeb;
    }
}
