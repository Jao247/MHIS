package csis.ie.ul.mhis.objects;


/**
 * @author adam
 */
public class BossObj
{
    private int    _id;
    private String _name;
    private String _type;
    private String _weakness;
    private String _element;
    private String _url;

    /**
     *
     * @param _id           id is part of the csv file we needed it to reference each monster
     * @param _name         This is the name of the monster
     * @param _type         This is the type of monster
     * @param _weakness     This is what element the monster is weakest to e.g Thunder
     * @param _element      This is the element that the monster has e.g Paralysis
     * @param _url          This is used to get the image of the monster off the wiki.
     */
    public BossObj(int _id, String _name, String _type, String _weakness, String _element, String _url)
    {
        this._id = _id;
        this._name = _name;
        this._type = _type;
        this._weakness = _weakness;
        this._element = _element;
        this._url = _url;
    }

    /**
     * @return This will return the monster's Id
     */
    public int get_id() { return _id; }

    /**
     * @return This will return the name of the monster of the list to display
     */
    public String get_name() { return _name; }

    /**
     * @return This will return the type of the monster
     */
    public String get_type() { return _type; }

    /**
     * @return This will return the Weakness of the monster
     */
    public String get_weakness() { return _weakness; }

    /**
     * @return This will return the element of the monster
     */
    public String get_element() { return _element; }

    /**
     * @return This will return URL monster's picture on the Wiki
     */
    public String get_url() {return _url;}
}
