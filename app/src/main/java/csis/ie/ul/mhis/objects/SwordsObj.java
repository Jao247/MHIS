package csis.ie.ul.mhis.objects;

/**
 * A class that is meant to objectified.
 * This is used to store the data from our CSV file.
 * @author Jason Fahy
 */
public class SwordsObj
{
    private int id, atk, aff, vigNumb;
    private String name, special, type, sharp, midSec, digits;

    /**
     * The constructor for the sword objects
     * @param id The ID of the sword.
     * @param name The Name of the sword.
     * @param type The Blade that is the base of the crafting tree for the sword.
     * @param atk The damage the blade deals.
     * @param special The elemental/effect damage the blade deals.
     * @param sharp The sharpness values that the blade can have.
     * @param aff The affinity percentage for critical hits.
     * @param vigNumb This is used for pulling images from the wiki.
     * @param midSec This is the second part of pulling images from the wiki.
     * @param digits This is the third and final part of the image pulling.
     */
    public SwordsObj(int id, String name, String type, int atk, String special, String sharp, int aff, int vigNumb, String midSec, String digits)
    {
        // Integers
        this.id      =      id;
        this.atk     =     atk;
        this.aff     =     aff;
        this.vigNumb = vigNumb;

        // String Data
        this.name    =    name;
        this.type    =    type;
        this.special = special;
        this.sharp   =   sharp;
        this.midSec  =  midSec;
        this.digits  =  digits;
    }

    // Get integer Data

    /**@return the id of the sword.*/
    public int getId()      { return      id; }
    /**@return the attack damage of the sword*/
    public int getAtk()     { return     atk; }
    /**@return the affinity percentage of the sword*/
    public int getAff()     { return     aff; }
    /**@return the vignette number (used in getting the images)*/
    public int getVigNumb() { return vigNumb; }
    // Get String Data
    /**@return the sword's name*/
    public String getName()   { return    name; }
    /**@return the elemental or effect damage of the sword.*/
    public String getSpecial(){ return special; }
    /**@return the sharpness statistics of the sword*/
    public String getSharp()  { return   sharp; }
    /**@return the base sword for the crafting tree*/
    public String getType()   { return    type; }
    /**@return the middle part of the image link*/
    public String getMidSec() { return  midSec; }
    /**@return the image number before the png.*/
    public String getDigits() { return  digits; }
}