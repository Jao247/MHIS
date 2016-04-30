package csis.ie.ul.mhis.objects;

public class SwordsObj
{
    private int id, atk, aff;
    private String name, special, type, sharp;

    public SwordsObj(int id, String name, String type, int atk, String special, String sharp, int aff)
    {
        // Integers
        this.id  =  id;
        this.atk = atk;
        this.aff = aff;

        // String Data
        this.name    =    name;
        this.type    =    type;
        this.special = special;
        this.sharp   =   sharp;
    }

    // Get integer Data
    public int getId()  { return  id; }
    public int getAtk() { return atk; }
    public int getAff() { return aff; }
    // Get String Data
    public String getName()   { return    name; }
    public String getSpecial(){ return special; }
    public String getSharp()  { return   sharp; }
    public String getType()   { return    type; }

    // Set Integer Data
    public void setAtk(int atk){ this.atk = atk; }
    public void setId (int  id){ this.id  =  id; }
    public void setAff(int aff){ this.aff = aff; }
    // Set String Data
    public void setName   (String    name) { this.name    =    name; }
    public void setSpecial(String special) { this.special = special; }
    public void setType   (String    type) { this.type    =    type; }
    public void setSharp  (String   sharp) { this.sharp   =   sharp; }
}