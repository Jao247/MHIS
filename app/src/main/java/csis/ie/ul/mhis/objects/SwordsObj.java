package csis.ie.ul.mhis.objects;

public class SwordsObj
{
    private int id, atk, aff, vigNumb;
    private String name, special, type, sharp, midSec, digits;

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
    public int getId()      { return      id; }
    public int getAtk()     { return     atk; }
    public int getAff()     { return     aff; }
    public int getVigNumb() { return vigNumb; }
    // Get String Data
    public String getName()   { return    name; }
    public String getSpecial(){ return special; }
    public String getSharp()  { return   sharp; }
    public String getType()   { return    type; }
    public String getMidSec() { return  midSec; }
    public String getDigits() { return  digits; }
}