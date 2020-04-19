package org.pb.rumination.model;

public class Craft {

    public String id;
    public int job;
    public int rlvl;
    public int durability;
    public int quality;
    public int progress;
    public int lvl;
    public int suggestedCraftsmanship;
    public int suggestedControl;
    public int stars;
    public boolean hq;
    public boolean quickSynth;
    public int controlReq;
    public int craftsmanshipReq;
    public int unlockId;
    public Ingredient[] ingredients;
    public int itemYield;
    public boolean expert;

    public Craft() {}

    public Craft(String id, int job, int rlvl, int durability, int quality, int progress, int lvl, int suggestedCraftsmanship, int suggestedControl, int stars, boolean hq, boolean quickSynth, int controlReq, int craftsmanshipReq, int unlockId, Ingredient[] ingredients, int yield, boolean expert) {
        this.id = id;
        this.job = job;
        this.rlvl = rlvl;
        this.durability = durability;
        this.quality = quality;
        this.progress = progress;
        this.lvl = lvl;
        this.suggestedCraftsmanship = suggestedCraftsmanship;
        this.suggestedControl = suggestedControl;
        this.stars = stars;
        this.hq = hq;
        this.quickSynth = quickSynth;
        this.controlReq = controlReq;
        this.craftsmanshipReq = craftsmanshipReq;
        this.unlockId = unlockId;
        this.ingredients = ingredients;
        this.itemYield = yield;
        this.expert = expert;
    }
}
