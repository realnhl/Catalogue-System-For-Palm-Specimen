package com;
public class recordData {

    private String recordID,commonName, genus, species, stem, leaf, timeSampled, dateSampled, locationSampled;

    public recordData() {

    }


    public recordData(String recordID,String commonName, String genus, String species, String stem, String leaf, String timeSampled, String dateSampled, String locationSampled) {
        this.recordID = recordID;
        this.commonName = commonName;
        this.genus = genus;
        this.species = species;
        this.stem = stem;
        this.leaf = leaf;
        this.timeSampled = timeSampled;
        this.dateSampled = dateSampled;
        this.locationSampled = locationSampled;
    }

    public recordData(String commonName, String genus, String species, String stem, String leaf, String timeSampled, String dateSampled, String locationSampled) {
        this.commonName = commonName;
        this.genus = genus;
        this.species = species;
        this.stem = stem;
        this.leaf = leaf;
        this.timeSampled = timeSampled;
        this.dateSampled = dateSampled;
        this.locationSampled = locationSampled;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getTimeSampled() {
        return timeSampled;
    }

    public void setTimeSampled(String timeSampled) {
        this.timeSampled = timeSampled;
    }

    public String getDateSampled() {
        return dateSampled;
    }

    public void setDateSampled(String dateSampled) {
        this.dateSampled = dateSampled;
    }

    public String getLocationSampled() {
        return locationSampled;
    }

    public void setLocationSampled(String locationSampled) {
        this.locationSampled = locationSampled;
    }
}
