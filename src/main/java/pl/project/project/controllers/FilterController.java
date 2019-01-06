package pl.project.project.controllers;

import org.springframework.util.StringUtils;

public class FilterController {

    private String phrase;
    private String countryFilter;
  private String directoryFilter;

    public boolean isEmpty()
    {
        return StringUtils.isEmpty(phrase) && StringUtils.isEmpty(countryFilter) && StringUtils.isEmpty(directoryFilter);
    }

    public void clear()
    {
        this.phrase = "";
        this.countryFilter = "";
       this.directoryFilter = "";
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }
    public String getDirectorLIKE(){
        if(StringUtils.isEmpty(directoryFilter)) {
            return null;
        }else{
            return "%"+ directoryFilter +"%";
        }
    }
    public String getCountryLIKE(){
        if(StringUtils.isEmpty(countryFilter)) {
            return null;
        }else{
            return "%"+ countryFilter +"%";
        }
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getCountryFilter() {
        return countryFilter;
    }

    public void setCountryFilter(String countryFilter) {
        this.countryFilter = countryFilter;
    }

    public String getDirectoryFilter() {
        return directoryFilter;
    }

    public void setDirectoryFilter(String directoryFilter) {
        this.directoryFilter = directoryFilter;
    }
}
